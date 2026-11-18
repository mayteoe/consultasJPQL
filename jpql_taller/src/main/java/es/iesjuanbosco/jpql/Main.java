package es.iesjuanbosco.jpql;
import jakarta.persistence.*;
import es.iesjuanbosco.jpql.datos.DatosIniciales;
import es.iesjuanbosco.jpql.consultas.ConsultasEjemplo;
import es.iesjuanbosco.jpql.modelo.Cliente;
import es.iesjuanbosco.jpql.modelo.Producto;
public class Main {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("tallerJPQL")) {
            try (EntityManager em = emf.createEntityManager()) {
                DatosIniciales.cargarSiVacia(em);
                ConsultasEjemplo consultas = new ConsultasEjemplo(em);
                System.out.println("\n=== PRODUCTOS CON PRECIO > 100 ===");
                for (Producto producto : consultas.productosCaros(100)) {
                    System.out.println(producto);
                }

                System.out.println("\n=== CLIENTES CON PEDIDOS ===");
                for (Cliente cliente : consultas.clientesConPedidos()) {
                    System.out.println(cliente);
                }

                System.out.println("\n=== NÚMERO DE PEDIDOS POR CLIENTE (INCLUIDOS 0) ===");
                for (Object[] fila : consultas.numeroPedidosPorCliente())
                    System.out.println(fila[0] + ": " + fila[1]);

                System.out.println("\n=== PRODUCTOS POR ENCIMA DE LA MEDIA ===");
                consultas.precioSuperiorMedia().forEach(System.out::println);

                System.out.println("\nAbre MisConsultas.java para realizar los ejercicios.");
            }
        }
    }
}