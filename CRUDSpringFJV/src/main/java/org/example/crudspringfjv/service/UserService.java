package org.example.crudspringfjv.service;

import org.example.crudspringfjv.dao.Users;
import org.example.crudspringfjv.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final Users users;

    public UserService() {
        this.users = new Users();
    }

    public List<User> getAllUsers() {
        return users.getUsers();
    }
    public User obtenerUsuarioEspecial(String nombre, String password) {
        return users.obtenerUsuarioEspecial(nombre,password);
    }

    public User getUserById(int id) {
        Optional<User> user = users.obtenerUsuarios().stream()
                .filter(u -> u.getId() == id)
                .findFirst();
        return user.orElse(null);
    }

    public void addUser(User user) {
        users.agregarUsuario(user);
    }

    public void deleteUser(int id) {
        users.eliminarUsuario(id);
    }

    public void updateUser(User user) {
        users.update(user);
    }

    public boolean verifyUser(int id) {
        User user = getUserById(id);
        if (user != null && !user.getVerificado()) {
            user.setVerificado(true);
            updateUser(user);
            return true;
        }
        return false;
    }
}
