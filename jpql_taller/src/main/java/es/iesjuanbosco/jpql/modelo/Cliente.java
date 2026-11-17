package es.iesjuanbosco.jpql.modelo;
import jakarta.persistence.*;
import java.util.*;
@Entity
public class Cliente {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 private String nombre;
 private String ciudad;
 private String email;
 @OneToMany(mappedBy="cliente", fetch=FetchType.LAZY)
 private List<Pedido> pedidos=new ArrayList<>();
 protected Cliente() {}
 public Cliente(String nombre,String ciudad,String email){
     this.nombre=nombre;
     this.ciudad=ciudad;
     this.email=email;
 }
    public Long getId(){return id;}
    public String getNombre(){return nombre;}
    public String getCiudad(){return ciudad;}
    public String getEmail(){return email;}
    public List<Pedido> getPedidos(){return pedidos;}
 @Override public String toString(){
     return "Cliente{"+id+", "+nombre+", "+ciudad+"}";}
}
