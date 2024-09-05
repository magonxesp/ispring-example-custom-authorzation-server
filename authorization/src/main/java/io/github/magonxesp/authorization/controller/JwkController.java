package io.github.magonxesp.authorization.controller;

import io.github.magonxesp.authorization.utils.JwkUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwkController {
    @Value("${jwt.public-key-path}")
    private String publicKeyPath;

    @GetMapping(value = "/.well-known/jwks.json", produces = "application/json")
    public String jwks() throws Exception {
        return String.format(
                "{\"keys\":[%s]}",
                JwkUtils.getPublicJwk(JwkUtils.readPublicKey(publicKeyPath))
        );
    }
}
