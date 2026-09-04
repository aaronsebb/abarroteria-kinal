/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.javatesting.kinalproyect.config;

/**
 *
 * @author informatica
 */
public class Credentials {
    
    public static final String URL_DB = System.getenv("varibleEntorno");
    public static final String  USER_DB = System.getenv("varibleEntorno");
    public static final String  PASS_DB = System.getenv("varibleEntorno");

    private Credentials(){
        
    }
    
    
}
