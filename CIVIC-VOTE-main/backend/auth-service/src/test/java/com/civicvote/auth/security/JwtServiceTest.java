package com.civicvote.auth.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class JwtServiceTest {

    @Test
    void shouldGenerateAndValidateToken() {
        JwtService jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret", "test-secret-key-for-civicvote-auth-service-1234567890");
        ReflectionTestUtils.setField(jwtService, "expirationMs", 86400000L);

        String token = jwtService.generateToken("7", "alice", "ROLE_VOTER");

        assertNotNull(token);
        assertTrue(jwtService.isTokenValid(token));
        assertEquals("alice", jwtService.extractUsername(token));
        assertEquals("ROLE_VOTER", jwtService.extractRole(token));
    }
}
