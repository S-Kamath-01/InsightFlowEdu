package com.example.insightflowbackend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Service for user authentication and authorization.
 * Handles login and session management.
 */
@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final JdbcTemplate jdbc;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        ensureCredentialSchema();
    }

    private void ensureCredentialSchema() {
        ensureStudentAuthTable();
        ensureFacultyPasswordStorage();
    }

    private void ensureStudentAuthTable() {
        try {
            jdbc.execute("CREATE TABLE student_auth (" +
                    "student_id NUMBER PRIMARY KEY, " +
                    "username VARCHAR2(100) UNIQUE NOT NULL, " +
                    "password_hash VARCHAR2(200), " +
                    "last_reset_on TIMESTAMP, " +
                    "created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "last_login TIMESTAMP, " +
                    "FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE) ");
        } catch (Exception ignored) {}
        try {
            jdbc.execute("CREATE UNIQUE INDEX idx_student_auth_username ON student_auth(username)");
        } catch (Exception ignored) {}
        try { jdbc.execute("ALTER TABLE student_auth ADD (password_hash VARCHAR2(200))"); } catch (Exception ignored) {}
        try { jdbc.execute("ALTER TABLE student_auth ADD (last_reset_on TIMESTAMP)"); } catch (Exception ignored) {}
        try { jdbc.execute("ALTER TABLE student_auth ADD (last_login TIMESTAMP)"); } catch (Exception ignored) {}
        // Drop legacy plaintext password columns if they exist (idempotent - ignored if already gone)
        try { jdbc.execute("ALTER TABLE student_auth DROP COLUMN password"); } catch (Exception e) {
            log.debug("student_auth.password column already absent or could not be dropped: {}", e.getMessage());
        }
        try { jdbc.execute("ALTER TABLE student_auth DROP COLUMN last_plaintext_password"); } catch (Exception e) {
            log.debug("student_auth.last_plaintext_password column already absent or could not be dropped: {}", e.getMessage());
        }
    }

    private void ensureFacultyPasswordStorage() {
        try { jdbc.execute("ALTER TABLE faculty ADD (password_hash VARCHAR2(200))"); } catch (Exception ignored) {}
        // Drop legacy plaintext password column if it exists (idempotent - ignored if already gone)
        try { jdbc.execute("ALTER TABLE faculty DROP COLUMN password"); } catch (Exception e) {
            log.debug("faculty.password column already absent or could not be dropped: {}", e.getMessage());
        }
    }

    /**
     * Authenticates a user by username and password.
     * 
     * @param username User's username
     * @param password User's password
     * @return Map containing token, role, and user details, or null if authentication fails
     */
    public Map<String, Object> authenticate(String username, String password) {
        try {
            // Query faculty table for user credentials
            String sql = "SELECT faculty_id, name, email, role, password_hash " +
                        "FROM faculty " +
                        "WHERE username = ?";
            
            Map<String, Object> user = jdbc.queryForMap(sql, username);
            
            // Verify BCrypt password hash
            String storedHash = (String) user.get("PASSWORD_HASH");
            boolean ok = storedHash != null && passwordEncoder.matches(password, storedHash);
            if (ok) {
                // Generate mock JWT token
                String token = "Bearer-" + UUID.randomUUID().toString();
                
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("role", user.get("ROLE"));
                
                Map<String, Object> userData = new HashMap<>();
                userData.put("id", user.get("FACULTY_ID"));
                userData.put("name", user.get("NAME"));
                userData.put("email", user.get("EMAIL"));
                userData.put("username", username);
                
                response.put("user", userData);
                
                return response;
            }
            
            return null;
        } catch (Exception e) {
            // Fall through to student auth when faculty not found or error
        }

        // Try student authentication
        try {
            String sql = "SELECT sa.student_id, sa.password_hash, s.name, s.email FROM student_auth sa " +
                         "JOIN students s ON s.student_id = sa.student_id WHERE sa.username = ?";
            Map<String, Object> row = jdbc.queryForMap(sql, username);
            String storedHash = (String) row.get("PASSWORD_HASH");
            boolean ok = storedHash != null && passwordEncoder.matches(password, storedHash);
            if (ok) {
                String token = "Bearer-" + UUID.randomUUID().toString();
                try { jdbc.update("UPDATE student_auth SET last_login = SYSTIMESTAMP WHERE username = ?", username); } catch (Exception ignored) {}
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("role", "student");
                Map<String, Object> userData = new HashMap<>();
                userData.put("id", row.get("STUDENT_ID"));
                userData.put("name", row.get("NAME"));
                userData.put("email", row.get("EMAIL"));
                userData.put("username", username);
                response.put("user", userData);
                return response;
            }
        } catch (Exception ignored) {}
        // User not found or bad password
        return null;
    }

    /**
     * Validates a token and returns associated user info.
     * In production, this would verify a real JWT token.
     * 
     * @param token Authentication token
     * @return User info if valid, null otherwise
     */
    public Map<String, Object> validateToken(String token) {
        // Normalize header if prefixed with standard "Bearer " scheme
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        // Mock implementation - in production, decode and verify JWT
        if (token != null && token.startsWith("Bearer-")) {
            // For demo purposes, return a mock user
            // In production, extract user info from JWT claims
            return new HashMap<>();
        }
        return null;
    }

    /**
     * Checks if a user has a specific role.
     * 
     * @param username Username to check
     * @param requiredRole Required role
     * @return true if user has the role
     */
    public boolean hasRole(String username, String requiredRole) {
        try {
            String sql = "SELECT role FROM faculty WHERE username = ?";
            String role = jdbc.queryForObject(sql, String.class, username);
            return requiredRole.equalsIgnoreCase(role);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns the faculty_id for a given username, or null if not a faculty member.
     */
    public Integer getFacultyIdByUsername(String username) {
        try {
            return jdbc.queryForObject("SELECT faculty_id FROM faculty WHERE username = ?", Integer.class, username);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Retrieves the role for a username (faculty roles only).
     * Returns null if the user is not found among faculty.
     */
    public String getRole(String username) {
        try {
            String role = jdbc.queryForObject("SELECT role FROM faculty WHERE username = ?", String.class, username);
            return role != null ? role.toLowerCase() : null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Gets the student_id for a given student username, or null if not a student.
     */
    public Integer getStudentIdByUsername(String username) {
        try {
            return jdbc.queryForObject("SELECT student_id FROM student_auth WHERE username = ?", Integer.class, username);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Changes password after verifying the current password.
     */
    public Map<String, Object> changePassword(String username, String currentPassword, String newPassword) {
        try {
            String sql = "SELECT password_hash FROM faculty WHERE username = ?";
            Map<String,Object> row = jdbc.queryForMap(sql, username);
            String storedHash = (String) row.get("PASSWORD_HASH");
            Map<String, Object> result = new HashMap<>();
            boolean ok = storedHash != null && passwordEncoder.matches(currentPassword, storedHash);
            if (ok) {
                String newHash = passwordEncoder.encode(newPassword);
                int updated = jdbc.update("UPDATE faculty SET password_hash = ? WHERE username = ?", newHash, username);
                result.put("updated", updated);
            } else {
                result.put("updated", 0);
            }
            return result;
        } catch (Exception e) {
            // Try student_auth as fallback
            try {
                Map<String,Object> row = jdbc.queryForMap("SELECT password_hash FROM student_auth WHERE username = ?", username);
                String storedHash = (String) row.get("PASSWORD_HASH");
                Map<String, Object> result = new HashMap<>();
                boolean ok = storedHash != null && passwordEncoder.matches(currentPassword, storedHash);
                if (ok) {
                    String newHash = passwordEncoder.encode(newPassword);
                    int updated = jdbc.update("UPDATE student_auth SET password_hash = ? WHERE username = ?", newHash, username);
                    result.put("updated", updated);
                } else {
                    result.put("updated", 0);
                }
                return result;
            } catch (Exception ex) {
                Map<String, Object> result = new HashMap<>();
                result.put("updated", 0);
                return result;
            }
        }
    }
}
