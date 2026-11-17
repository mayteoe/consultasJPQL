package es.iesjuanbosco.jpql.modelo;
import jakarta.persistence.*;
@Entity
public class Producto {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 private String nombre;
 private Double precio;
 private Integer stock;
 @ManyToOne(fetch=FetchType.LAZY, optional=false)
 private Categoria categoria;

 protected Producto() {}public Producto(String nombre,Double precio,Integer stock,Categoria categoria){this.nombre=nombre;this.precio=precio;this.stock=stock;this.categoria=categoria;}

    public Long getId(){return id;}
    public String getNombre(){return nombre;}
    public Double getPrecio(){return precio;}
    public Integer getStock(){return stock;}
    public Categoria getCategoria(){return categoria;}


     @Override public String toString(){

     return "Producto{"+id+", "+nombre+", "+precio+", stock="+stock+"}";
     }
}
