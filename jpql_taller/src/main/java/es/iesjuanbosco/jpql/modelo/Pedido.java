package es.iesjuanbosco.jpql.modelo;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;
@Entity
public class Pedido {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 private LocalDate fecha;
 private Double total;
 @Enumerated(EnumType.STRING) private EstadoPedido estado;
 @ManyToOne(fetch=FetchType.LAZY, optional=false)
 private Cliente cliente;
 @OneToMany(mappedBy="pedido", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
 private List<LineaPedido> lineas=new ArrayList<>();

 protected Pedido() {}
    public Pedido(LocalDate fecha,Double total,EstadoPedido estado,Cliente cliente){this.fecha=fecha;this.total=total;this.estado=estado;this.cliente=cliente;}
    public void agregarLinea(Producto producto,int cantidad){
     lineas.add(new LineaPedido(this,producto,cantidad,producto.getPrecio()));}
    public Long getId(){return id;} public LocalDate getFecha(){return fecha;} public Double getTotal(){return total;} public EstadoPedido getEstado(){return estado;} public Cliente getCliente(){return cliente;} public List<LineaPedido> getLineas(){return lineas;}
 @Override public String toString(){
     return "Pedido{"+id+", "+fecha+", "+total+", "+estado+"}";}
}
