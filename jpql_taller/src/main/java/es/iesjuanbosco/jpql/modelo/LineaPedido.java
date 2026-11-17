package es.iesjuanbosco.jpql.modelo;
import jakarta.persistence.*;
@Entity
public class LineaPedido {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false)
 private Pedido pedido;
 @ManyToOne(fetch=FetchType.LAZY,optional=false)
 private Producto producto;
 private Integer cantidad;
 private Double precioUnitario;
 protected LineaPedido() {}
    public LineaPedido(Pedido pedido,Producto producto,Integer cantidad,Double precioUnitario){
     this.pedido=pedido;
     this.producto=producto;
     this.cantidad=cantidad;
     this.precioUnitario=precioUnitario;
 }
    public Long getId(){return id;}
    public Pedido getPedido(){return pedido;}
    public Producto getProducto(){return producto;}
    public Integer getCantidad(){return cantidad;}
    public Double getPrecioUnitario(){return precioUnitario;}
}
