package org.example.crudspringfjv.ui.controllers;


import org.example.crudspringfjv.domain.User;
import org.example.crudspringfjv.service.UserService;
import org.example.crudspringfjv.ui.components.MailComponent;
import org.example.crudspringfjv.utils.Constantes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserService userService;
    private final MailComponent mailComponent;
    public LoginController(UserService userService, MailComponent mailComponent) {
        this.userService = userService;
        this.mailComponent = mailComponent;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String Login(
            @RequestParam("action") String action,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String nombreReg,
            @RequestParam(required = false) String passwordReg,
            @RequestParam(required = false) String password,
            Model model) {

        if ("login".equals(action)) {
            if (nombre == null || password == null) {
                model.addAttribute("error", "Por favor, ingrese usuario y contraseña.");
                return "error";
            }

            User user = userService.obtenerUsuarioEspecial(nombre, password);

            if (user == null) {
                model.addAttribute("error", "Usuario o contraseña incorrectos.");
                return "error";
            }

            if ("admin".equals(nombre) && "admin".equals(password)) {
                if (user.getVerificado()) {
                    model.addAttribute(Constantes.USER, nombre);
                    return "playlist";
                } else {
                    model.addAttribute("error", "Usuario no verificado por mail. Revisalo.");
                    return "error";
                }
            } else {
                model.addAttribute("error", "Usuario o contraseña incorrectos");
                return "error";
            }

        } else if ("register".equals(action)) {
            if (nombreReg == null || passwordReg == null) {
                model.addAttribute("error", "Debe completar todos los campos.");
                return "error";
            }
            userService.addUser(new User(nombreReg, passwordReg));
//            mailComponent.sendMail();
            return "login";
        }

        model.addAttribute("error", "Acción no válida.");
        return "error";
    }

}