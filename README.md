# elEquipoIdeal
El objetivo del trabajo práctico es implementar una aplicación para resolver el problema del enunciado por fuerza bruta o backtracking que debe ejecutar en un thread separado de la aplicación principal.

Okay podemos pensar que cada individuo es un vertice en un grafo conexo.
Cuando hay individuos incompatibles entonces no habra aristas entre sus vertices correspondientes.
cada vertice tiene un beneficio (Calificacion), por lo tanto el objetivo del trabajo es encontrar
el clique dentro del grafo con mayor beneficio y con las limitaciones de los requerimientos pedidos.

Propongo usar una adaptacion del algoritmo de busqueda de clique maximo, para el grafo previamente diseñado.

De esta manera reciclamos mucho codigo del trabajo anterior, solo queda adaptarlo y desarrollar el algoritmo de fuerza bruta con backTRacking, junto alguna heuristica como para hacer alguna tabla comparativa en el informe.

