package io.github.magonxesp.authorization.utils;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import static io.github.magonxesp.authorization.JwkConstants.keyId;

public class JwkUtils {
    public static String getPublicJwk(RSAPublicKey publicKey) {
        JWK jwk = new RSAKey.Builder(publicKey)
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(Algorithm.parse("RS256"))
                .keyID(keyId)
                .issueTime(Date.from(Instant.now()))
                .build();

        return jwk.toJSONString();
    }

    public static RSAPrivateKey readPrivateKey(String path) throws Exception {
        String keyContent = CertUtils.trimKey(Files.readString(Paths.get(path)));
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(keyContent));
        return (RSAPrivateKey) factory.generatePrivate(keySpecPKCS8);
    }

    public static RSAPublicKey readPublicKey(String path) throws Exception {
        String keyContent = CertUtils.trimKey(Files.readString(Paths.get(path)));
        KeyFactory factory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(keyContent));
        return (RSAPublicKey) factory.generatePublic(keySpecX509);
    }
}
