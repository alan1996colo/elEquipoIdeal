# elEquipoIdeal
El objetivo del trabajo práctico es implementar una aplicación para resolver el problema del enunciado por fuerza bruta o backtracking que debe ejecutar en un thread separado de la aplicación principal.

Okay podemos pensar que cada individuo es un vertice en un grafo conexo.
Cuando hay individuos incompatibles entonces no habra aristas entre sus vertices correspondientes.
cada vertice tiene un beneficio (Calificacion), por lo tanto el objetivo del trabajo es encontrar
el clique dentro del grafo con mayor beneficio y con las limitaciones de los requerimientos pedidos.

Propongo usar una adaptacion del algoritmo de busqueda de clique maximo, para el grafo previamente diseñado.

De esta manera reciclamos mucho codigo del trabajo anterior, solo queda adaptarlo y desarrollar el algoritmo de fuerza bruta con backTRacking, junto alguna heuristica como para hacer alguna tabla comparativa en el informe.

-----------------------update 30/5------------------------------------------

Se planteó y soluciono el siguiente problema: Se quiere persistir el estado del programa en el tiempo, con todos los vertices y aristas del grafo en el estado actual. El problema se da en que como la clase Persona contiene a la clase Arista en un HashSet y a su vez la clase Arista contiene dos objetos de tipo Persona, como uno se apunta al otro recursivamente, la libreria gson desconoce como guardar el estado en un archivo.
La solucion fue agregar a la clase Arista dos atributos adicionales "String nombre del vertice" para poder guardar el estado de la arista con persistencia y que los atributos de tipo objeto sean no trazables, o transient. De esta forma se puede almacenar el estado del grafo con sus aristas completamente, sin embargo nos limita a que el nombre o apellido de cada vertice debe ser UNICO para cada objeto. Por lo tanto, desde ahora si  hay dos personas que tienen todos los atributos diferentes pero su nombre es igual, sera considerado la misma persona. 
TASK TO DO:
- [ ] Modificar la implementacion del equals de personas para que se mantenga esta nueva especificacion.
- [x] Agregar algun grafico para mostrar los grafos en tiempo real.
- [x] Agregar metodos para cargar las fotos.
- [x] Finalizar los action listener de las pestañas cargar y guardar archivo.
- [ ] Crear una nueva pestaña y boton para calcular la solucion.
- [ ] crear una funcion en la interfazVisual que asigne posiciones libres de forma dinamica.
- [ ] Crear una opcion para que el usuario voluntariamente pueda cargar una imagen propia



