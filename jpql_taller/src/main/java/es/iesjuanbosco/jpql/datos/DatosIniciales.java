package es.iesjuanbosco.jpql.datos;
import jakarta.persistence.*;
import es.iesjuanbosco.jpql.modelo.*;
import java.time.LocalDate;
import java.util.*;
public final class DatosIniciales {
 private DatosIniciales(){}
 public static void cargarSiVacia(EntityManager em){
  if(em.createQuery("SELECT COUNT(c) FROM Cliente c",Long.class).getSingleResult()>0) return;
  EntityTransaction tx=em.getTransaction();
  try {tx.begin();
   Categoria informatica=new Categoria("Informática"), hogar=new Categoria("Hogar"), libros=new Categoria("Libros");
   for(Categoria c:List.of(informatica,hogar,libros)) em.persist(c);
   Producto portatil=new Producto("Portátil profesional",950.0,8,informatica), raton=new Producto("Ratón inalámbrico",25.0,20,informatica), monitor=new Producto("Monitor panorámico",220.0,5,informatica), teclado=new Producto("Teclado mecánico",85.0,12,informatica), disco=new Producto("Disco SSD",110.0,3,informatica), webcam=new Producto("Webcam HD",65.0,7,informatica), lampara=new Producto("Lámpara",35.0,9,hogar), manual=new Producto("Manual de Java",45.0,15,libros);
   for(Producto p:List.of(portatil,raton,monitor,teclado,disco,webcam,lampara,manual)) em.persist(p);
   Cliente ana=new Cliente("Ana López","Madrid","ana@ejemplo.test"), luis=new Cliente("Luis Pérez","Toledo","luis@ejemplo.test"), maria=new Cliente("María Ruiz","Madrid","maria@ejemplo.test"), carlos=new Cliente("Carlos Díaz","Alcázar de San Juan",null), eva=new Cliente("Eva Martín","Toledo","eva@ejemplo.test");
   for(Cliente c:List.of(ana,luis,maria,carlos,eva)) em.persist(c);
   crear(em,ana,LocalDate.of(2026,10,1),1000.0,EstadoPedido.PENDIENTE,portatil,1,raton,2);
   crear(em,ana,LocalDate.of(2026,10,3),220.0,EstadoPedido.ENVIADO,monitor,1);
   crear(em,ana,LocalDate.of(2026,10,8),85.0,EstadoPedido.PENDIENTE,teclado,1);
   crear(em,ana,LocalDate.of(2026,10,12),110.0,EstadoPedido.ENVIADO,disco,1);
   crear(em,luis,LocalDate.of(2026,10,4),950.0,EstadoPedido.ENVIADO,portatil,1);
   crear(em,maria,LocalDate.of(2026,10,5),65.0,EstadoPedido.CANCELADO,webcam,1);
   crear(em,maria,LocalDate.of(2026,10,9),45.0,EstadoPedido.PENDIENTE,manual,1);
   crear(em,carlos,LocalDate.of(2026,10,6),35.0,EstadoPedido.PENDIENTE,lampara,1);
   tx.commit();
  } catch(RuntimeException e){if(tx.isActive())tx.rollback();throw e;}
 }
 private static void crear(EntityManager em,Cliente c,LocalDate f,Double total,EstadoPedido estado,Producto p,int cantidad){Pedido pe=new Pedido(f,total,estado,c);pe.agregarLinea(p,cantidad);em.persist(pe);}
 private static void crear(EntityManager em,Cliente c,LocalDate f,Double total,EstadoPedido estado,Producto p,int cantidad,Producto p2,int cantidad2){Pedido pe=new Pedido(f,total,estado,c);pe.agregarLinea(p,cantidad);pe.agregarLinea(p2,cantidad2);em.persist(pe);}
}
