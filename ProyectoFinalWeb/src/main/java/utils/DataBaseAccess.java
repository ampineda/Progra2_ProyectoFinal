package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseAccess {

    public static Connection conn;

    static {
        try{

            if(conn == null){
                // Class.forName("com.mysql.jdbc.Driver");

                
                /*     
                // conexion para una base de datos local
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/videojuegos",
                                                  "root","admin");
                */

                // conexion para una base de datos local
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://sql3.freesqldatabase.com:3306/sql3448583",
                                                  "sql3448583","SM3gnD5yUW");

                
                /*                                        
                // conexion para una base de datos en un host
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql:node5122-env-9093184.sp.skdrive.net/videojuegos",
                        "root","eNYt7bR5km");

                */
                
                /*
                // conexion para una base de datos en un host
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://node5173-gametopstore.sp.skdrive.net/videojuegos",
                        "root","BzHdyVsWpi");
                */        
                

                // https://node35316-database.jelastic.com

                // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurante","root","admin");

                System.out.println("se conecto al base de datos de videojuegps");

                /*
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-161-58-21.compute-1.amazonaws.com/d3icpgjetmg86s","wgelxjooeiwwxn","815648e8fc3d1574d8ac42d4d925b64a3c655cf386bd85c4a45c08e7c51c6adf");
                conn = DriverManager.getConnection("jdbc:postgresql://HOST/DATABASE","USER","PASSWORD");
                */

            }

        }catch (Exception e){
            // System.out.println("error: "+ e.getMessage());
            e.printStackTrace();
        }
    }
    

}
