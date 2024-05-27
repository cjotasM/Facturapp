package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarProveedor(Proveedor pr) {
        String sql = "INSERT INTO proveedores (nit, nombre, telefono, direccion, razon) VALUES (?, ?, ?, ?, ?)";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getNit());
            ps.setString(2, pr.getNombre());
            ps.setLong(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public List listarProveedor() {
        List<Proveedor> listaPr = new ArrayList();
        String sql = "SELECT * FROM PROVEEDORES";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Proveedor pr = new Proveedor();
                pr.setId(rs.getInt("id"));
                pr.setNit(rs.getInt("nit"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getLong("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setRazon(rs.getString("razon"));
                listaPr.add(pr);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaPr;
    }
    
    public boolean eliminarProveedor(int id) {
        
        String sql = "DELETE FROM proveedores WHERE id = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
}
