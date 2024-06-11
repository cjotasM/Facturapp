package Model;

import java.sql.*;

public class VentaDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    int r;
    
    public int idVenta(){
       int id = 0;
       String sql = "SELECT MAX(id) FROM ventas";
       
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.toString()); 
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());                
            }
        }
        return id;
    }
    
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
    
    public int registrarDetalle(Detalle dv) {
        String sql = "INSERT INTO detalle (cod_pro, cantidad, precio, id_venta) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, dv.getCod_pro());
            ps.setInt(2, dv.getCantidad());
            ps.setDouble(3, dv.getPrecio());
            ps.setInt(4, dv.getIdVenta());
            
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
    
    public boolean actualizarStock(int cant, String cod){
        String sql = "UPDATE productos SET stock = ? WHERE codigo = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setString(2, cod);
            ps.execute();
            
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString()); 
            
            return false;
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());                
            }
        }
        
    }
}