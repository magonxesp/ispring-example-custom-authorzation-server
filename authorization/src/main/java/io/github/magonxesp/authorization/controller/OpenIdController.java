package io.github.magonxesp.authorization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@RestController
public class OpenIdController {

    @GetMapping(value = "/.well-known/openid-configuration", produces = "application/json")
    public String configuration() throws IOException {
        BufferedInputStream is = (BufferedInputStream) this.getClass().getResource("/openid-configuration.json").getContent();
        return new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
    }

}
