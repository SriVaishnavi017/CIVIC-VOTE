package com.civicvote.voting.security;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@Service
public class HashChainService {

    public String computeHash(String previousHash, Long electionId, Long candidateId, Long voterId, String ballotReference) {
        String base = previousHash + "|" + electionId + "|" + candidateId + "|" + voterId + "|" + ballotReference;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }

    public String getInitialPreviousHash() {
        return "GENESIS";
    }
}
