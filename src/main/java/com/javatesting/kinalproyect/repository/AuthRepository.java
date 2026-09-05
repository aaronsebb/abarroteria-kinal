/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.javatesting.kinalproyect.repository;

import main.java.com.javatesting.kinalproyect.config.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import main.java.com.javatesting.kinalproyect.model.Usuario;
import java.sql.ResultSet;

public class AuthRepository {
    public static Usuario findUserByEmail(String email) throws SQLException{
        String sql = "Select * from Users where email = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement prst = conn.prepareStatement(sql)){
            ResultSet rs = prst.executeQuery();
            if(rs.next()){
                Usuario user = new Usuario(rs.getString("name"));
                return user;
            }else{
                
                return null;
                
                
            }}
        }
        
}
