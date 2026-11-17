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
public class Producto {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 private String nombre;
 private Double precio;
 private Integer stock;

 @ManyToOne(fetch=FetchType.LAZY, optional=false)
 private Categoria categoria;

}
