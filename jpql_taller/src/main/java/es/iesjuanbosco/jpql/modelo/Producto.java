package es.iesjuanbosco.jpql.modelo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="productos")
@NamedQuery(name="Producto.porNombre",
        query="SELECT p FROM Producto p WHERE p.nombre LIKE :nombre")
@NamedQuery(
        name = "Producto.caros",
        query = "SELECT p FROM Producto p WHERE p.precio > :minimo"
)

public class Producto {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 @Column(nullable = false, length = 120)
 private String nombre;
 @Column(nullable = false)
 private Double precio;
 @Column(nullable = false)
 private Integer stock;

 @ManyToOne(fetch=FetchType.LAZY, optional=false)
 @JoinColumn(name = "categoria_id", nullable = false)
 private Categoria categoria;

}
