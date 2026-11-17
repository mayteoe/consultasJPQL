package es.iesjuanbosco.jpql;
import jakarta.persistence.*;
import es.iesjuanbosco.jpql.datos.DatosIniciales;
import es.iesjuanbosco.jpql.consultas.ConsultasEjemplo;
public class Main {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("tallerJPQL")) {
            EntityManager em = emf.createEntityManager();
            try {
                DatosIniciales.cargarSiVacia(em);
                ConsultasEjemplo consultas = new ConsultasEjemplo(em);
                System.out.println("\n=== PRODUCTOS CON PRECIO > 100 ===");
                consultas.productosCaros(100).forEach(System.out::println);

                System.out.println("\n=== CLIENTES CON PEDIDOS ===");
                consultas.clientesConPedidos().forEach(System.out::println);

                System.out.println("\n=== NÚMERO DE PEDIDOS POR CLIENTE (INCLUIDOS 0) ===");
                for (Object[] fila : consultas.numeroPedidosPorCliente())
                    System.out.println(fila[0] + ": " + fila[1]);

                System.out.println("\n=== PRODUCTOS POR ENCIMA DE LA MEDIA ===");
                consultas.precioSuperiorMedia().forEach(System.out::println);

                System.out.println("\nAbre MisConsultas.java para realizar los ejercicios.");
                }
                finally {
                em.close();
            }
        }
    }
}