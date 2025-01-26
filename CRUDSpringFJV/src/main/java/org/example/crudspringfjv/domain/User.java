package org.example.crudspringfjv.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private  int id;
    private  String nombre;
    private  String password;
    private Boolean verificado;

    public User(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }
}
