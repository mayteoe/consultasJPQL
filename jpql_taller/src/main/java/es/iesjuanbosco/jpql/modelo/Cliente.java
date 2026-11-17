package es.iesjuanbosco.jpql.modelo;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Cliente {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 private String nombre;
 private String direccion;
 private String email;

 @OneToMany(mappedBy="cliente", fetch=FetchType.LAZY)
 @Builder.Default
 @ToString.Exclude
 private List<Pedido> pedidos=new ArrayList<>();

 //Métodos helper para mantener la relación bidireccional
    public void agregarPedido(Pedido pedido){
        pedidos.add(pedido);
        pedido.setCliente(this);
    }

    public void eliminarPedido(Pedido pedido){
        pedidos.remove(pedido);
        pedido.setCliente(null);
    }
}

