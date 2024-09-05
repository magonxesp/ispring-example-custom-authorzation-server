package io.github.magonxesp.authorization.controller;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import io.github.magonxesp.authorization.model.TokenDto;
import io.github.magonxesp.authorization.utils.JwkUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static io.github.magonxesp.authorization.JwkConstants.keyId;

@RestController
public class OAuthController {

    @Value("${jwt.private-key-path}")
    private String privateKeyPath;

    @PostMapping("/oauth/token")
    public TokenDto token() throws Exception {
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plus(5, ChronoUnit.MINUTES);

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .keyID(keyId)
                .type(JOSEObjectType.JWT)
                .build();

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .issuer("http://localhost:8080")
                .subject("user")
                .expirationTime(Date.from(expiresAt))
                .issueTime(Date.from(issuedAt))
                .claim("email", "sanjay@example.com")
                .claim("name", "Sanjay Patel")
                .build();

        JWSObject jwsObject = new JWSObject(header, new Payload(claims.toJSONObject()));

        jwsObject.sign(new RSASSASigner(JwkUtils.readPrivateKey(privateKeyPath)));

        return new TokenDto(
                jwsObject.serialize(),
                "Bearer",
                expiresAt.getEpochSecond() - issuedAt.getEpochSecond()
        );
    }

}
