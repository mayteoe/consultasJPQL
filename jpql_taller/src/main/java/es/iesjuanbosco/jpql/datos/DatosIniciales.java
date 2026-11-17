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
       Categoria informatica=Categoria.builder().nombre("Informática").build();
       Categoria hogar=Categoria.builder().nombre("Hogar").build();
       Categoria libros=Categoria.builder().nombre("Libros").build();
       for(Categoria c:List.of(informatica,hogar,libros)) em.persist(c);

       Producto portatil=Producto.builder()
               .nombre("Portátil profesional")
               .precio(950.0)
               .stock(8)
               .categoria(informatica).build();
       Producto raton=Producto.builder().nombre("Ratón inalámbrico").precio(25.0).stock(20).categoria(informatica).build();
       Producto monitor=Producto.builder().nombre("Monitor panorámico").precio(220.0).stock(5).categoria(informatica).build();
       Producto teclado=Producto.builder().nombre("Teclado mecánico").precio(85.0).stock(12).categoria(informatica).build();
       Producto disco=Producto.builder().nombre("Disco SSD").precio(110.0).stock(3).categoria(informatica).build();
       Producto webcam=Producto.builder().nombre("Webcam HD").precio(65.0).stock(7).categoria(informatica).build();
       Producto lampara=Producto.builder().nombre("Lámpara").precio(35.0).stock(9).categoria(hogar).build();
       Producto  manual=Producto.builder().nombre("Manual de Java").precio(45.0).stock(15).categoria(libros).build();
       for(Producto p:List.of(portatil,raton,monitor,teclado,disco,webcam,lampara,manual)) em.persist(p);

       Cliente ana=Cliente.builder()
               .nombre("Ana López")
               .direccion("Madrid")
               .email("ana@ejemplo.test").build();
        Cliente luis=Cliente.builder()
                .nombre("Luis Pérez")
                .direccion("Toledo")
                .email("luis@ejemplo.test").build();
        Cliente maria=Cliente.builder()
                .nombre("María Ruiz")
                .direccion("Madrid")
                .email("maria@ejemplo.test").build();
        Cliente carlos=Cliente.builder()
                .nombre("Carlos Díaz")
                .direccion("Alcázar de San Juan")
                .email(null).build();
        Cliente eva=Cliente.builder()
                .nombre("Eva Martín")
                .direccion("Toledo")
                .email("eva@ejemplo.test").build();

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
 private static void crear(EntityManager em,Cliente c,LocalDate f,Double total,EstadoPedido estado,Producto p,int cantidad){
     Pedido pe=Pedido.builder().fecha(f).total(total).estado(estado).cliente(c).build();
     LineaPedido lp=LineaPedido.builder()
             .pedido(pe)
             .producto(p)
             .cantidad(cantidad)
             .precioUnitario(p.getPrecio())
             .build();
     pe.agregarLinea(lp);
     em.persist(pe);
 }
 private static void crear(EntityManager em,Cliente c,LocalDate f,Double total,EstadoPedido estado,Producto p,int cantidad,Producto p2,int cantidad2){
     Pedido pe=Pedido.builder()
             .fecha(f)
             .total(total)
             .estado(estado)
             .cliente(c)
             .build();
     LineaPedido l1=LineaPedido.builder()
             .pedido(pe)
             .producto(p)
             .cantidad(cantidad)
             .precioUnitario(p.getPrecio())
             .build();
     LineaPedido l2=LineaPedido.builder()
             .pedido(pe)
             .producto(p2)
             .cantidad(cantidad2)
             .precioUnitario(p2.getPrecio())
             .build();
     pe.agregarLinea(l1);
     pe.agregarLinea(l2);

     em.persist(pe);
 }
}
