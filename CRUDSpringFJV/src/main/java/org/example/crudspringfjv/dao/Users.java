package org.example.crudspringfjv.dao;

import lombok.Data;
import org.example.crudspringfjv.domain.User;

import java.util.ArrayList;
import java.util.List;

@Data
public class Users {
    private List<User> users;
    private int contador;

    public Users() {
        users = new ArrayList<>();
        users.add(new User(1, "Admin", "admin123", true));
        users.add(new User(2, "JohnDoe", "password", false));
        users.add(new User(3, "JaneDoe", "123456", false));
        users.add(new User(4, "Oscar", "clave", false));
        users.add(new User(5, "Maria", "securepass", false));
        contador = users.size() + 1;
    }

    public List<User> obtenerUsuarios() {
        return users;
    }
    public User obtenerUsuarioEspecial(String nombre, String password){
        User user = users.stream().filter(userr -> userr.getNombre().equals(nombre)&&userr.getPassword().equals(password)).findFirst().get();
        return user;
    }

    public void agregarUsuario(User nuevoUsuario) {
        nuevoUsuario.setId(contador);
        contador++;
        users.add(nuevoUsuario);
    }

    public void eliminarUsuario(int id) {
        users.removeIf(user -> user.getId() == id);
    }

    public void update(User userUpdated) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userUpdated.getId()) {
                users.set(i, userUpdated);
            }
        }
    }
}
