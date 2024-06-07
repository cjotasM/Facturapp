package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean registrarCliente(Cliente cl) {
        String sql = "INSERT INTO clientes (cc, nombre, telefono, direccion, razon) VALUES (?, ?, ?, ?, ?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getCc());
            ps.setString(2, cl.getNombre());
            ps.setLong(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getRazon());

            ps.execute();

            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List listarCliente() {
        List<Cliente> listaCl = new ArrayList();
        String sql = "SELECT * FROM clientes";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();

                cl.setId(rs.getInt("id"));
                cl.setCc(rs.getInt("cc"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getLong("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazon(rs.getString("razon"));

                listaCl.add(cl);
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
        return listaCl;
    }

    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());

            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public boolean modificarCliente(Cliente cl) {
        String sql = "UPDATE clientes SET cc = ?, nombre = ?, telefono = ?, direccion = ?, razon = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getCc());
            ps.setString(2, cl.getNombre());
            ps.setLong(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getRazon());
            ps.setInt(6, cl.getId());

            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());                
            }
        }
    }
    
    public Cliente buscarCliente(int cc){
       Cliente cl = new Cliente();
       
       String sql = "SELECT * FROM clientes WHERE cc = ?";
       
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cc);
            rs = ps.executeQuery();
            if (rs.next()) {
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getLong("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazon(rs.getString("razon"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
                
            }
        }
        return cl;
    }
}
