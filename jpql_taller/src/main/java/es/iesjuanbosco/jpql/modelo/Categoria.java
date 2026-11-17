package es.iesjuanbosco.jpql.modelo;
import jakarta.persistence.*;
import java.util.*;
@Entity
public class Categoria {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 private String nombre;
 @OneToMany(mappedBy="categoria", fetch=FetchType.LAZY)
 private List<Producto> productos=new ArrayList<>();

 protected Categoria() {}
    public Categoria(String nombre){this.nombre=nombre;}

    public Long getId(){return id;}
    public String getNombre(){return nombre;}
    public List<Producto> getProductos(){return productos;}

    @Override
    public String toString(){
     return "Categoria{"+id+", "+nombre+"}";}
}
