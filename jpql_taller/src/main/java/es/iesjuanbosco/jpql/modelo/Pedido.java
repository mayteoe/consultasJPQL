package es.iesjuanbosco.jpql.modelo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoPedido estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaPedido> lineas = new ArrayList<>();

    //Métodos helper para mantener la relación bidireccional
    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
        linea.setPedido(this);
    }

    public void eliminarLinea(LineaPedido linea) {
        lineas.remove(linea);
        linea.setPedido(null);
    }
}

