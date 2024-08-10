
package modelo;

import java.sql.*;
import java.util.ArrayList;
        
public class conection {
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    
    public conection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bd","root","");
            System.out.print("Conectado BD");
        } catch (Exception e) {
            System.out.print("Error al conectar");
        }
    }
    
    public ArrayList<LoginGetSet> login(String user, String pass){
        ArrayList<LoginGetSet> res=new ArrayList<> ();
        try {
            ps=cn.prepareStatement(" select * from login where id=? and pass=?");
            ps.setString(1, user);
            ps.setString(1, pass);
            rs=ps.executeQuery();
            while (rs.next()){
                LoginGetSet lo=new LoginGetSet();
                lo.setId(rs.getString("id"));
                lo.setNombre(rs.getString("nombre"));
                lo.setApellido(rs.getString("apellido"));
                lo.setUser(rs.getString("user"));
                lo.setPass(rs.getString("pass"));
                res.add(lo);
            }
            if (res.isEmpty()) {
                System.err.println("Acceso denegado");
            } else {
                System.err.println("Login exitoso");
            }
        } catch (Exception e) {
        }
        return res;
    }
}
