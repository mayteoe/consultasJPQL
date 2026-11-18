package es.iesjuanbosco.jpql.modelo;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Categoria {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 @Column(nullable = false, length = 80, unique = true)
 private String nombre;
 @OneToMany(mappedBy="categoria", fetch=FetchType.LAZY)
 @Builder.Default
 @ToString.Exclude
 private List<Producto> productos=new ArrayList<>();

 //Métodos helper para mantener la relación bidireccional
    public void agregarProducto(Producto producto){
        productos.add(producto);
        producto.setCategoria(this);
    }

    public void eliminarProducto(Producto producto){
        productos.remove(producto);
        producto.setCategoria(null);
    }
}
