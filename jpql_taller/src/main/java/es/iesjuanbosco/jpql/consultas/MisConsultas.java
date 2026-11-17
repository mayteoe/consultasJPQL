package es.iesjuanbosco.jpql.consultas;
import es.iesjuanbosco.jpql.modelo.*;
import jakarta.persistence.*;
import java.util.*;
/** Completa los métodos de los ejercicios sin consultar las soluciones. */
public class MisConsultas {
 private final EntityManager em;
 public MisConsultas(EntityManager em){this.em=em;}

    public List<Cliente> a1Clientes(){
  // TODO A1: SELECT c FROM Cliente c
  throw new UnsupportedOperationException("Completa A1");
 }
 public List<Producto> a3ProductosCaros(double precio){
  // TODO A3: utiliza un parámetro nominal
  throw new UnsupportedOperationException("Completa A3");
 }
 public List<Cliente> c22ClientesSinPedidos(){
  // TODO C22: usa NOT EXISTS o LEFT JOIN
  throw new UnsupportedOperationException("Completa C22");
 }
 public List<Object[]> d26PedidosPorCliente(){
  // TODO D26: agrupa por cliente y cuenta pedidos
  throw new UnsupportedOperationException("Completa D26");
 }
}
