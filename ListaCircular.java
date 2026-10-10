/**
 * Lista Circular Simple de clientes.
 *
 * Idea clave: en una lista simple normal el último nodo apunta a null.
 * Aquí el último nodo apunta de nuevo al PRIMERO, formando un círculo:
 *
 *     primero --> [B] --> [C] --> [D] = ultimo
 *        ^                           |
 *        +---------------------------+   (ultimo.siguiente == primero)
 */
public class ListaCircular {

    // ---------- Clase interna Nodo ----------
    private class Nodo {
        Cliente dato;
        Nodo siguiente;

        Nodo(Cliente dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    // ---------- Atributos de la lista ----------
    private Nodo primero;
    private Nodo ultimo;
    private int tamano;

    public ListaCircular() {
        this.primero = null;
        this.ultimo = null;
        this.tamano = 0;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public int getTamano() {
        return tamano;
    }

    // ---------- INSERTAR AL INICIO ----------
    // Retorna el Cliente insertado, o null si el id ya existe.
    public Cliente insertarAlInicio(Cliente cliente) {
        if (buscarPorId(cliente.getId()) != null) {
            return null; // id repetido
        }
        Nodo nuevo = new Nodo(cliente);

        if (estaVacia()) {
            primero = nuevo;
            ultimo = nuevo;
            nuevo.siguiente = nuevo;      // CIRCULAR: con un solo nodo, se apunta a sí mismo
        } else {
            nuevo.siguiente = primero;    // el nuevo apunta al antiguo primero
            primero = nuevo;              // el nuevo pasa a ser el primero
            ultimo.siguiente = primero;   // CIRCULAR: el último "cierra el círculo" con el nuevo primero
        }
        tamano++;
        return cliente;
    }

    // ---------- INSERTAR AL FINAL ----------
    // Retorna el Cliente insertado, o null si el id ya existe.
    public Cliente insertarAlFinal(Cliente cliente) {
        if (buscarPorId(cliente.getId()) != null) {
            return null; // id repetido
        }
        Nodo nuevo = new Nodo(cliente);

        if (estaVacia()) {
            primero = nuevo;
            ultimo = nuevo;
            nuevo.siguiente = nuevo;      // CIRCULAR: único nodo, se apunta a sí mismo
        } else {
            ultimo.siguiente = nuevo;     // el antiguo último apunta al nuevo
            ultimo = nuevo;               // el nuevo pasa a ser el último
            ultimo.siguiente = primero;   // CIRCULAR: el nuevo último apunta de vuelta al primero
        }
        tamano++;
        return cliente;
    }

    // ---------- BUSCAR POR ID ----------
    // Retorna el Cliente encontrado, o null si no existe.
    public Cliente buscarPorId(int id) {
        if (estaVacia()) {
            return null;
        }
        Nodo actual = primero;
        // do-while: no podemos parar en null (no hay null en una lista circular).
        // Paramos cuando damos la vuelta completa y regresamos al primero.
        do {
            if (actual.dato.getId() == id) {
                return actual.dato;
            }
            actual = actual.siguiente;
        } while (actual != primero);

        return null;
    }

    // ---------- ELIMINAR POR ID ----------
    // Retorna el Cliente eliminado, o null si no se encontró.
    public Cliente eliminarPorId(int id) {
        if (estaVacia()) {
            return null;
        }
        Nodo actual = primero;
        Nodo anterior = ultimo; // el anterior al primero es el último (¡por la circularidad!)

        do {
            if (actual.dato.getId() == id) {
                Cliente eliminado = actual.dato;

                if (tamano == 1) {
                    primero = null;               // la lista queda vacía
                    ultimo = null;
                } else {
                    anterior.siguiente = actual.siguiente; // saltamos el nodo eliminado
                    if (actual == primero) {
                        primero = actual.siguiente;        // ultimo.siguiente ya apunta al nuevo primero
                    }
                    if (actual == ultimo) {
                        ultimo = anterior;                 // anterior.siguiente ya apunta al primero
                    }
                }
                tamano--;
                return eliminado;
            }
            anterior = actual;
            actual = actual.siguiente;
        } while (actual != primero);

        return null;
    }

    // ---------- MOSTRAR (una vuelta completa) ----------
    public String mostrar() {
        if (estaVacia()) {
            return "La lista está vacía.";
        }
        StringBuilder sb = new StringBuilder();
        Nodo actual = primero;
        do {
            sb.append(actual.dato).append("\n");
            actual = actual.siguiente;
        } while (actual != primero);
        sb.append("(vuelve al primero: ").append(primero.dato.getNombre()).append(")");
        return sb.toString();
    }

    // ---------- RECORRER VARIAS VUELTAS ----------
    // Demuestra la circularidad: nunca se llega a null, se puede seguir girando.
    public String recorrerVueltas(int vueltas) {
        if (estaVacia()) {
            return "La lista está vacía.";
        }
        StringBuilder sb = new StringBuilder();
        Nodo actual = primero;
        for (int i = 0; i < vueltas * tamano; i++) {
            sb.append(actual.dato.getNombre()).append(" -> ");
            actual = actual.siguiente; // al llegar al último, pasa solo al primero
        }
        sb.append("(sigue girando...)");
        return sb.toString();
    }
}
