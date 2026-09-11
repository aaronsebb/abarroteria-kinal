/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.javatesting.kinalproyect.service.usuario;

import main.java.com.javatesting.kinalproyect.repository.usuario.AuthRepository;

/**
 *
 * @author informatica
 */
public class AuthService {
 
    private final AuthRepository authRepository;
    
    public AuthService(AuthRepository authRepository){
    
    this.authRepository = authRepository;
    
    }
    
    
    
    
}
