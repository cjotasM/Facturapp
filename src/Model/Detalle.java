package Model;

public class Detalle {
    private int id, cantidad, idVenta;
    private String cod_pro;
    private double precio;

    public Detalle() {
    }

    public Detalle(int id, int cantidad, int idVenta, String cod_pro, double precio) {
        this.id = id;
        this.cantidad = cantidad;
        this.idVenta = idVenta;
        this.cod_pro = cod_pro;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(String cod_pro) {
        this.cod_pro = cod_pro;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
