package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            rs = ps.executeQuery();
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
       String sql =  "INSERT INTO ventas (cliente,vendedor,total,fecha) VALUES(?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getCliente());
            ps.setString(2, v.getVendedor());
            ps.setDouble(3, v.getTotal());
            ps.setString(4, v.getFecha());
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
        String sql = "INSERT INTO detalle (cod_pro, cantidad, precio, idVenta) VALUES (?,?,?,?)";
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
    
    public List listarVentas() {
        List<Venta> listaVenta = new ArrayList();
        String sql = "SELECT * FROM ventas";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Venta vent = new Venta();
                vent.setId(rs.getInt("id"));
                vent.setCliente(rs.getString("cliente"));
                vent.setVendedor(rs.getString("vendedor"));
                vent.setTotal(rs.getDouble("total"));
                listaVenta.add(vent);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
        return listaVenta;
    }
}