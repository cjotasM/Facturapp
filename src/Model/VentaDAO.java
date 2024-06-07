package Model;

import java.sql.*;

public class VentaDAO {
    Connection con;
    PreparedStatement ps;
    Conexion cn = new Conexion();
    int r;
    public int registrarVenta(Venta v){
       String sql =  "INSERT INTO ventas (cliente,vendedor,total) VALUES(?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getCliente());
            ps.setString(2, v.getVendedor());
            ps.setDouble(3, v.getTotal());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());                
            }
        }
        return r;
    }
}