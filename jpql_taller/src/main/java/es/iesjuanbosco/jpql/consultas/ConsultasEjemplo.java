package es.iesjuanbosco.jpql.consultas;
import es.iesjuanbosco.jpql.modelo.*;
import jakarta.persistence.*;
import java.util.*;
public class ConsultasEjemplo {
 private final EntityManager em;
 public ConsultasEjemplo(EntityManager em){
     this.em=em;
 }
 public List<Producto> productosCaros(double minimo){
     return em.createQuery("""
             SELECT p FROM Producto p WHERE p.precio > :minimo
              ORDER BY p.precio DESC
             """,Producto.class)
              .setParameter("minimo",minimo)
              .getResultList();}
 public List<Cliente> clientesConPedidos(){
     String jpql="SELECT DISTINCT c FROM Cliente c JOIN c.pedidos p";
     return em.createQuery(jpql,Cliente.class).getResultList();
 }
 public List<Cliente> clientesConPedidosCargados(){
     String jpql="SELECT DISTINCT c FROM Cliente c LEFT JOIN FETCH c.pedidos p";
     return em.createQuery(jpql,Cliente.class).getResultList();
 }
 public List<Object[]> numeroPedidosPorCliente(){
     String jpql="""
                 SELECT c.nombre, COUNT(p) FROM Cliente c 
                 LEFT JOIN c.pedidos p GROUP BY c.id, c.nombre ORDER BY c.nombre
                 """;
     return em.createQuery(jpql,Object[].class).getResultList();}

    public List<Producto> precioSuperiorMedia(){
     String jpql="""
                  SELECT p FROM Producto p 
                  WHERE p.precio > (SELECT AVG(p2.precio) FROM Producto p2)
                  """;
     return em.createQuery(jpql,Producto.class).getResultList();
 }
 public int subirPrecioCategoria(Long idCategoria,double factor){
     // Subir el precio de los productos de una categoría
     Categoria categoria=em.getReference(Categoria.class,idCategoria);
     EntityTransaction tx=em.getTransaction();
     try{
         tx.begin();
         int n=em.createQuery("UPDATE Producto p SET p.precio = p.precio * :factor"+
                 "vWHERE p.categoria = :categoria")
                 .setParameter("factor",factor)
                 .setParameter("categoria",categoria)
                 .executeUpdate();
         tx.commit();
         em.clear();
         return n;
     }
     catch(RuntimeException e){
         if(tx.isActive())
             tx.rollback();
         throw e;}}
}
