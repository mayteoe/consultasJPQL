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
public class LineaPedido {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false)
 @JoinColumn(name = "pedido_id", nullable = false)
 private Pedido pedido;

 @ManyToOne(fetch=FetchType.LAZY,optional=false)
 @JoinColumn(name = "producto_id", nullable = false)
 private Producto producto;

 @Column(nullable = false)
 private Integer cantidad;

 @Column(nullable = false)
 private Double precioUnitario;

}
