package es.iesjuanbosco.jpql.consultas;
import es.iesjuanbosco.jpql.modelo.*;
import jakarta.persistence.*;
import java.util.*;
/** Completa los métodos de los ejercicios sin consultar las soluciones. */
public class MisConsultas {
 private final EntityManager em;
 public MisConsultas(EntityManager em){this.em=em;}

    public List<Cliente> a1Clientes(){
     String jpql="SELECT c FROM Cliente c";
     try{
         return em.createQuery(jpql,Cliente.class).getResultList();
     } catch (Exception e) {
         throw new RuntimeException("Error al ejecutar la consulta A1", e);


     }
 }

 public List<Producto> a3ProductosCaros(double precio){
  try{
      return em.createNamedQuery("Producto.caros",Producto.class)
              .setParameter("minimo",precio)
              .getResultList();
    } catch (Exception e) {
        throw new RuntimeException("Error al ejecutar la consulta A3", e);
    }
  }

  public List<Cliente> a4Clientesporlocalidad(String localidad){
     String jpql="SELECT c FROM Cliente c WHERE c.localidad=:ciudad";
     try{
         return em.createQuery(jpql,Cliente.class).setParameter("ciudad",localidad).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar la consulta A4", e);
        }


  }
  public List<Producto> a5ProductosStockInferior(String numero){
     String jpql="SELECT p FROM Producto p WHERE p.stock < :stock";
     try{
         return em.createQuery(jpql,Producto.class).setParameter("stock",numero).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar la consulta A5", e);
        }
    }

    public List<Pedido> a6PedidosPendientes(EstadoPedido estado){
     String jpql="SELECT p FROM Pedido p WHERE p.estado=:estado";

     try{
         return em.createQuery(jpql,Pedido.class).setParameter("estado",estado).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar la consulta A6", e);
        }
    }


    public List<Cliente> c22ClientesSinPedidos() {
     // TODO C22: usa NOT EXISTS o LEFT JOIN
     String jpql = "SELECT c FROM Cliente c LEFT JOIN c.pedidos p WHERE p IS NULL";
     try {
         return em.createQuery(jpql, Cliente.class).getResultList();
     } catch (Exception e) {
         throw new RuntimeException("Error al ejecutar la consulta C22", e);
     }
 }

 public List<Object[]> d26PedidosPorCliente(){
  // TODO D26: agrupa por cliente y cuenta pedidos
     String jpql ="SELECT c.nombre, COUNT(c.pedidos) FROM Cliente c GROUP BY c.id, c.nombre";
        try {
            return em.createQuery(jpql, Object[].class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar la consulta D26", e);
        }
 }
}
