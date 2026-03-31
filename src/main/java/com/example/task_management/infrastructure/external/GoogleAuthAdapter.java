package com.example.task_management.infrastructure.external;

import com.example.task_management.interfaces.dto.response.auth.GoogleUserInfo;
import com.example.task_management.application.repositories.OAuth2Repository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class GoogleAuthAdapter implements OAuth2Repository {

    private final GoogleIdTokenVerifier verifier;

    public GoogleAuthAdapter(@Value("${google.client-id}") String clientId) {
        this.verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(clientId))
                .build();
    }

    @Override
    public GoogleUserInfo verifyGoogleIdToken(String idTokenString) {
        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                return GoogleUserInfo.builder()
                        .email(payload.getEmail())
                        .name((String) payload.get("name"))
                        .pictureUrl((String) payload.get("picture"))
                        .emailVerified(Boolean.TRUE.equals(payload.getEmailVerified()))
                        .build();
            } else {
                throw new IllegalArgumentException("Invalid ID token.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to verify Google Token.", e);
        }
    }
}
