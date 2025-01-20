# Respuestas a las preguntas de teoría

a) ¿Qué ventajas e inconvenientes encuentras al usar una base de datos documental con MongoDB?

**Ventajas:**

- Es flexible porque no necesita un esquema fijo, lo que facilita manejar datos dinámicos.
- Es rápido para operaciones de lectura y escritura.
- Escala bien al distribuir datos en varios servidores.

**Inconvenientes:**

- Puede haber inconsistencias en los datos porque no obliga a usar un esquema.
- Las transacciones no son tan completas como en bases relacionales.
- Requiere más validaciones en la aplicación para garantizar la integridad de los datos.

---

b) ¿Cuáles han sido los principales problemas que has tenido al desarrollar la aplicación?

- **Conexión a la base de datos:** Al principio, mantener y cerrar la conexión en cada operación no funcionaba bien.
- **Validaciones:** Tuve que asegurarme de que los datos fueran consistentes, como evitar títulos duplicados.
- **Estructura del código:** Organizar bien las funciones para que sean claras y evitar redundancias fue un desafío.

---

c) ¿Cómo solucionarías el problema de la inconsistencia de datos en una base de datos documental?

- Usar validadores de esquema que ofrece MongoDB para definir reglas en las colecciones (Se realizaría estableciendo reglas en `jsonSchema`).
- Validar los datos desde la aplicación antes de insertarlos o actualizarlos.
- Documentar bien cómo deben estructurarse los datos para mantener consistencia.
- Usar herramientas como Mongoose (en Node.js) para manejar esquemas de forma más sencilla.
