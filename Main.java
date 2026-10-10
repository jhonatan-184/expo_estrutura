import java.util.Scanner;

/**
 * Menú interactivo para probar la Lista Circular Simple con objetos Cliente.
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ListaCircular lista = new ListaCircular();
        int opcion;

        do {
            System.out.println("\n===== LISTA CIRCULAR DE CLIENTES =====");
            System.out.println("1. Insertar al inicio");
            System.out.println("2. Insertar al final");
            System.out.println("3. Buscar cliente por id");
            System.out.println("4. Eliminar cliente por id");
            System.out.println("5. Mostrar lista (una vuelta)");
            System.out.println("6. Recorrer varias vueltas (ver la circularidad)");
            System.out.println("7. Cargar clientes de ejemplo");
            System.out.println("0. Salir");
            opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1:
                    Cliente c1 = lista.insertarAlInicio(pedirCliente());
                    System.out.println(c1 != null ? "Insertado al inicio: " + c1 : "Error: ya existe un cliente con ese id.");
                    break;
                case 2:
                    Cliente c2 = lista.insertarAlFinal(pedirCliente());
                    System.out.println(c2 != null ? "Insertado al final: " + c2 : "Error: ya existe un cliente con ese id.");
                    break;
                case 3:
                    Cliente buscado = lista.buscarPorId(leerEntero("Id a buscar: "));
                    System.out.println(buscado != null ? "Encontrado: " + buscado : "No se encontró el cliente.");
                    break;
                case 4:
                    Cliente eliminado = lista.eliminarPorId(leerEntero("Id a eliminar: "));
                    System.out.println(eliminado != null ? "Eliminado: " + eliminado : "No se encontró el cliente.");
                    break;
                case 5:
                    System.out.println(lista.mostrar());
                    break;
                case 6:
                    int vueltas = leerEntero("¿Cuántas vueltas? ");
                    System.out.println(lista.recorrerVueltas(vueltas));
                    break;
                case 7:
                    int cargados = cargarEjemplos(lista);
                    System.out.println("Clientes de ejemplo cargados: " + cargados);
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static Cliente pedirCliente() {
        int id = leerEntero("Id: ");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        return new Cliente(id, nombre, telefono, correo);
    }



    // Retorna cuántos clientes de ejemplo se insertaron (los ids repetidos se ignoran)
    private static int cargarEjemplos(ListaCircular lista) {
        int insertados = 0;
        if (lista.insertarAlFinal(new Cliente(1, "Ana Gómez", "3001112233", "ana@mail.com")) != null) insertados++;
        if (lista.insertarAlFinal(new Cliente(2, "Luis Pérez", "3004445566", "luis@mail.com")) != null) insertados++;
        if (lista.insertarAlFinal(new Cliente(3, "María Ruiz", "3007778899", "maria@mail.com")) != null) insertados++;
        return insertados;
    }

    // Lee un entero de forma segura (repite hasta que el usuario escriba un número válido)
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresa un número entero.");
            }
        }
    }
}
