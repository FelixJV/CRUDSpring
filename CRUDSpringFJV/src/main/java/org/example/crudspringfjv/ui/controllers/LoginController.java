package org.example.crudspringfjv.ui.controllers;


import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.bcel.Const;
import org.example.crudspringfjv.domain.User;
import org.example.crudspringfjv.service.UserService;
import org.example.crudspringfjv.components.MailComponent;
import org.example.crudspringfjv.utils.Constantes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserService userService;
    public LoginController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginRegister(
            @RequestParam("action") String action,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String nombreReg,
            @RequestParam(required = false) String passwordReg,
            @RequestParam(required = false) String email,
            Model model,
            HttpSession session) {

//        if (Constantes.LOGIN_VIEW.equals(action)) {
//            return loginUser(nombre, password,session, model);
//        } else if (Constantes.REGISTER.equals(action)) {
//            return registerUser(nombreReg, passwordReg, email, model);
//        }

        model.addAttribute(Constantes.ERROR, "Acción no válida.");
        return Constantes.ERROR;
    }

    private String loginUser(String username, String password, HttpSession session, Model model) {
        if (username == null || password == null) {
            model.addAttribute(Constantes.ERROR, "Por favor, ingrese usuario y contraseña.");
            return Constantes.LOGIN;
        }

        if (userService.login(username, password)) {
            session.setAttribute("USER", username);
            return "redirect:" +Constantes.PLAYLIST_VIEW;
        } else {
            model.addAttribute(Constantes.ERROR, Constantes.LOGIN_ERROR);
            return Constantes.LOGIN;
        }
    }

//    private String registerUser(String nombreReg, String passwordReg, String email, Model model) {
//        if (nombreReg == null || passwordReg == null || email == null) {
//            model.addAttribute(Constantes.ERROR, "Debe completar todos los campos.");
//            return Constantes.ERROR;
//        }
//         if (userService.register(new User(
//                    nombreReg,
//                    passwordReg,
//                    false,
//                    email,
//                    null))) {
//                return Constantes.LOGIN;
//            } else {
//                model.addAttribute(Constantes.ERROR, Constantes.ERROR_EN_REGISTRO);
//                return Constantes.LOGIN;
//            }
//    }

//    @GetMapping("/verificar/{codigo}")
//    public String activateAccount(@PathVariable String codigo, Model model) {
//        userService.verificar(codigo);
//        model.addAttribute(Constantes.MESSAGE_ATR, Constantes.CUENTA_VERIFICADA);
//        return "redirect:/" + Constantes.LOGIN;
//    }
}