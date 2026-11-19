package es.iesjuanbosco.jpql;
import es.iesjuanbosco.jpql.consultas.MisConsultas;
import es.iesjuanbosco.jpql.modelo.EstadoPedido;
import es.iesjuanbosco.jpql.modelo.Pedido;
import jakarta.persistence.*;
import es.iesjuanbosco.jpql.datos.DatosIniciales;
import es.iesjuanbosco.jpql.consultas.ConsultasEjemplo;
import es.iesjuanbosco.jpql.modelo.Cliente;
import es.iesjuanbosco.jpql.modelo.Producto;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("tallerJPQL")) {
            try (EntityManager em = emf.createEntityManager()) {
                DatosIniciales.cargarSiVacia(em);
                ConsultasEjemplo consultas = new ConsultasEjemplo(em);
                MisConsultas miConsultas= new MisConsultas(em);
                System.out.println("\n---PRODUCTOS CUYO PRECIO > 100 ---");
                for (Producto producto : consultas.productosCaros(100)) {
                    System.out.println(producto);
                }

                System.out.println("\n---CLIENTES CON PEDIDOS ---");
                for (Cliente cliente : consultas.clientesConPedidos()) {
                    System.out.println(cliente);
                }
                // A5. Productos con stock inferior a 10.

                System.out.println("\n ---A5. PRODUCTOS CON STOCK < 10 ---");
                for (Producto producto: miConsultas.a5ProductosStockInferior("10")){
                    System.out.println(producto);
                }


                //A6. Pedidos en estado PENDIENTE.
                System.out.println("\n --- PEDIDOS EN ESTADO PENDIENTE ---");
                for (Pedido pedido:miConsultas.a6PedidosPendientes(EstadoPedido.PENDIENTE)){
                    System.out.println(pedido);
                }

                System.out.println( "\n--- A4.CLIENTES RESIDENTES EN MADRID ---");
                for(Cliente cliente: miConsultas.a4Clientesporlocalidad("Madrid")){
                    System.out.println(cliente);
                }


                System.out.println("\n=== NÚMERO DE PEDIDOS POR CLIENTE (INCLUIDOS 0) ===");
                for (Object[] fila : consultas.numeroPedidosPorCliente())
                    System.out.println(fila[0] + ": " + fila[1]);

                System.out.println("\n--- PRODUCTOS POR ENCIMA DE LA MEDIA ---");
                consultas.precioSuperiorMedia().forEach(System.out::println);

                //pedidos con clientes y lineas cargadas
                System.out.println("\n--- CLIENTES CON PEDIDOS CARGADOS ---");
                for (Cliente cliente : consultas.clientesConPedidosCargados()) {
                    System.out.println(cliente);
                    cliente.getPedidos().forEach(System.out::println);
                }

                System.out.println("\nAbre MisConsultas.java para realizar los ejercicios.");

                MisConsultas misConsultas = new MisConsultas(em);
                //Consulta 1
                System.out.println("\n--- CONSULTA A1: Listar todos los clientes ---");
                for(Cliente cliente : misConsultas.a1Clientes()){
                    System.out.println(cliente);
                }
                //Consulta 3
                System.out.println("\n--- CONSULTA A3: Listar productos caros (precio > 100) ---");
                for(Producto producto : misConsultas.a3ProductosCaros(100)){
                    System.out.println(producto);
                }

                //Consulta 22
                System.out.println("\n--- CONSULTA C22: Listar clientes sin pedidos ---");
                for(Cliente cliente : misConsultas.c22ClientesSinPedidos()){
                    System.out.println(cliente);

                }
            }
        }
    }
}