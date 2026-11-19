# Taller JPQL — 2.º DAM / Acceso a Datos

Proyecto Maven de **Java 17**, **Jakarta Persistence 3.1**, **Hibernate ORM 6.6** y **H2** (base de datos local en archivo). No requiere instalar MySQL ni crear tablas manualmente.

## Requisitos
- JDK 17 o superior (recomendado JDK 21).
- Maven 3.9 o IDE con soporte Maven (IntelliJ IDEA, Eclipse o VS Code).

## Cómo ejecutar
1. Abre esta carpeta como proyecto Maven.
2. Ejecuta `mvn compile exec:java` desde la raíz del proyecto, o ejecuta la clase `es.iesjuanbosco.jpql.Main` desde el IDE.
3. En la primera ejecución se crean las tablas y los datos de prueba en `datos/tienda.mv.db`.
4. Observa el SQL generado en consola; compara JPQL con SQL.
5. Resuelve los ejercicios en `consultas/MisConsultas.java` y llama a tus métodos desde `Main`.

## Estructura
- `modelo/`: entidades Cliente, Categoria, Producto, Pedido, LineaPedido y enum EstadoPedido.
- `datos/DatosIniciales.java`: carga inicial, solo si no hay clientes.
- `consultas/ConsultasEjemplo.java`: ejemplos funcionales para comparar.
- `consultas/MisConsultas.java`: plantilla para el alumnado.
- `resources/META-INF/persistence.xml`: conexión, proveedor JPA y SQL visible.

## Datos de prueba
5 clientes (Eva no tiene pedidos), de Madrid, Albacete, Valencia, Alicante y Barcelona; 3 categorías, 8 productos y 8 pedidos. Informática tiene 6 productos; Ana tiene 4 pedidos. Un pedido cancelado está relacionado con líneas de pedido: **no ejecutes DELETE masivo de Pedido sin revisar las claves foráneas**.

## Precauciones
- `hibernate.hbm2ddl.auto=update` se utiliza **solo para prácticas**, no para producción.
- La BD persiste entre ejecuciones. Para empezar de cero, detén el programa y elimina la carpeta `datos/`.
- Los datos iniciales solo se cargan si no hay clientes; los cambios en ellos no modifican automáticamente una base de datos existente.
- No ejecutes UPDATE/DELETE de los ejercicios sobre datos que quieras conservar. Haz copia o reinicia la BD.
- `Double` se utiliza para simplificar la enseñanza; para importes monetarios reales conviene `BigDecimal`.
- `JOIN FETCH` de una colección no debe combinarse ingenuamente con paginación.
- Si cambias nombres de campos o relaciones, adapta también las consultas.

## Ejemplo de consulta
```java
List<Producto> lista = em.createQuery(
    "SELECT p FROM Producto p WHERE p.precio > :minimo",
    Producto.class
).setParameter("minimo", 100.0).getResultList();
```
