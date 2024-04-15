package com.example.xerogroup.services;

import com.example.xerogroup.models.User;
import com.example.xerogroup.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    UserRepository userRepository;
    public UserService(){
        userRepository = new UserRepository();
    }
    public void saveVend(User vendedor){
        userRepository.saveUser(vendedor);
    }
    public Optional<User> getVend(String cedula){
        return userRepository.getUser(cedula);
    }
    public Optional<User> getByUsername(String corre, String password){
        return userRepository.getByUsername(corre, password);
    }
    public List<User> vend(){
        return userRepository.users();
    }
    public void deleteVend(String cedula){
        userRepository.deleteUser(cedula);
    }
    public void editVend(User vendedor){
        userRepository.editUser(vendedor);
    }
    public Optional<User> getByCorreo(String correo){ return  userRepository.getByCorreo(correo); }
    public User vendedor(String cedula){ return userRepository.getVendedor(cedula); }
}
