package org.example.crudspringfjv.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private  String nombre;
    private  String password;
    private Boolean verificado;
    private String email;
    private String codigo;

    public User(String nombre, String password,String email) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
    }
}
