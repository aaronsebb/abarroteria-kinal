package main.java.com.javatesting.kinalproyect.config;

public class Credentials {
    
    /*private static final String URL_DB = "";
    private static final String USER_DB = "";
    private static final String PASS_DB = "";
    */
    
    public static final String URL_DB = System.getenv("varibleEntorno");
    public static final String  USER_DB = System.getenv("varibleEntorno");
    public static final String  PASS_DB = System.getenv("varibleEntorno");
    
    private Credentials() {
    }
}