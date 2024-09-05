package io.github.magonxesp.authorization.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    public static class LoginRequest {
        String name;

        public LoginRequest(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @PostMapping("/login")
    public ModelAndView doLogin(HttpServletRequest request, LoginRequest loginRequest) {
        request.getSession().setAttribute("name", loginRequest.name);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login-success");
        mv.addObject("name", loginRequest.name);
        return mv;
    }

}
