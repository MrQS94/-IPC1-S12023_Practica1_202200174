package super25;

import java.util.Scanner;

/**
 * 
 * @author queza
 */
public class Super25 {

    /**
     * @param args the command line arguments
     */
    private static Scanner sca = new Scanner(System.in);
    private static int quantity; 
    private static int[] cantidad;
    private static final int CANTIDADTOT = 20;
    private static String[] nombreProd;
    private static float[] precioProd;
    private static String[] codDesc;
    private static float[] porcentDesc;
    private static float[] total;

    public static void main(String[] args) {
        System.out.println("A continuacion ingrese su nombre y contraseña:");
        String nombre = "";
        String pass = "";
        int n = 3;
        do {
            System.out.print("Nombre: ");
            nombre = sca.nextLine();
            System.out.print("Contraseña: ");
            pass = sca.nextLine();
            if (!ComprobarUser(nombre, pass)) {
                System.out.println("Error!!");
                System.out.println("Tiene intentos limitados (" + n + "), favor ingrese "
                        + "correctamente su nombre y contraseña.");
                n--;
                if (n <= 0) {
                    System.out.println("No puede continuar usando el programa.");
                    System.out.println("Cumplio el número máximo de intentos.");
                    break;
                }
            } else {
                MenuInicial();
            }
        } while (!ComprobarUser(nombre, pass));
    }

    public static Boolean ComprobarUser(String nombre, String pass) {
        // cajero_202200174 ipc1_202200174
        if (!nombre.equals("cajero_202200174") || !pass.equals("ipc1_202200174")) {
            return false;
        } else {
            return true;
        }
    }

    public static String ComprobarNit(String nit) {
        String mensaje = "CF";
        if (nit.length() >= 8 && nit.length() <= 9) {
            mensaje = nit;
        }
        return mensaje;
    }

    public static void MenuInicial() {
        int opcion = -1;
        do {
            System.out.println("---------------------------------------------");
            System.out.println("Bienvenido a SUPER-25");
            System.out.println("1. Agregar nuevos productos.");
            System.out.println("2. Agregar cupones de descuento.");
            System.out.println("3. Realizar ventas.");
            System.out.println("4. Realizar reporte.");
            System.out.println("0. Cerrar programa.");
            System.out.println("Ingrese cualquier número entre 0 y 4 para continuar.");
            opcion = Integer.parseInt(sca.nextLine());
            System.out.println("---------------------------------------------");
            if (opcion < 0 || opcion > 4) {
                System.out.println("Error, ingrese un número entre 0 y 4.");
            } else if (opcion == 0) {
                System.out.println("Cerrando programa...");
                break;
            } else if (opcion == 1) {
                System.out.println("Bienvenido a \"Agregar nuevos productos\"");
                do {
                    System.out.println("Ingrese la cantidad de productos que "
                            + "desee agregar. Solo puede ingresar un maximo de 20 elementos.");
                    quantity = Integer.parseInt(sca.nextLine());
                    if (quantity > 0 && quantity <= CANTIDADTOT) {
                        AgregarProducto(quantity);
                    }
                } while (quantity <= 0 || quantity > CANTIDADTOT);
            } else if (opcion == 2) {
                System.out.println("Bienvenido a \"Agregar cupones de descuento\"");
                do {
                    System.out.println("Ingrese la cantidad de cupones que "
                            + "desee agregar. Solo puede ingresar un maximo de 20 cupones.");
                    quantity = Integer.parseInt(sca.nextLine());
                    if (quantity > 0 && quantity <= CANTIDADTOT) {
                        AgregarCupones(quantity);
                    }
                } while (quantity <= 0 || quantity > CANTIDADTOT);
            } else if (opcion == 3) {
                System.out.println("Bienvenido a \"Realizar ventas\"");
                RealizarVentas();
            } else if (opcion == 4) {
                System.out.println("Bienvenido a \"Realizar reporte\"");
                EmitirReporte();
            }
        } while (opcion < 0 || opcion > 4);

    }

    public static void AgregarProducto(int cantidad) {
        String[] nombreProdActual = new String[cantidad];
        float[] precioProdActual = new float[cantidad];
        for (int i = 0; i < nombreProdActual.length; i++) {
            System.out.println("---------------------- " + i + " --------------------");
            System.out.println("Ingrese el nombre del producto");
            nombreProdActual[i] = sca.nextLine();
            System.out.println("Ingrese el precio del producto");
            precioProdActual[i] = Float.parseFloat(sca.nextLine());
        }

        nombreProd = new String[nombreProdActual.length];
        precioProd = new float[precioProdActual.length];

        if (nombreProdActual.length == 1) {
            for (int i = 0; i < nombreProdActual.length; i++) {
                nombreProd[i] = nombreProdActual[i];
                precioProd[i] = precioProdActual[i];
            }
        } else {
            for (int i = 0; i < nombreProdActual.length - 1; i++) {
                for (int j = i + 1; j < nombreProdActual.length; j++) {
                    if (precioProdActual[j] <= 0 || nombreProdActual[i].equals(nombreProdActual[j])) {
                        System.out.println("Hay nombres duplicados o el precio tiene un valor menor a cero.");
                        System.out.println("Devuelta al menu principal, para introducir de forma correcta los datos.");
                        System.out.println("Presione ENTER para continuar...");
                        sca.nextLine();
                        MenuInicial();
                        break;
                    } else {
                        for (int k = 0; k < nombreProdActual.length; k++) {
                            nombreProd[k] = nombreProdActual[k];
                            precioProd[k] = precioProdActual[k];
                        }
                    }
                }
            }
        }
        System.out.println("Los datos han sido guardados.");
        System.out.println("Presione ENTER para continuar...");
        sca.nextLine();
        MenuInicial();
    }

    public static void AgregarCupones(int cantidad) {
        String[] codDescActual = new String[cantidad];
        float[] porcentDescActual = new float[cantidad];
        System.out.println("Recuerde que el codigo de descuento debe tener "
                + "una logitud de 4 caracteres.");
        System.out.println("El porcentaje debe ser entre 0 y 100.");
        for (int i = 0; i < codDescActual.length; i++) {
            System.out.println("---------------------- " + i + " --------------------");
            System.out.println("Ingrese el codigo de descuento");
            codDescActual[i] = sca.nextLine();
            System.out.println("Ingrese el porcentaje de descuento");
            porcentDescActual[i] = Float.parseFloat(sca.nextLine());
        }

        codDesc = new String[codDescActual.length];
        porcentDesc = new float[porcentDescActual.length];

        if (codDescActual.length == 1) {

            for (int i = 0; i < codDescActual.length; i++) {
                if ((porcentDescActual[i] <= 0 || porcentDescActual[i] > 100)
                        || (codDescActual[i].length() < 4 || codDescActual[i].length() > 4)) {
                    System.out.println("El porcentaje tiene un valor menor a 0 o mayor a 100.");
                    System.out.println("El codigo de descuento es diferente a 4 caracteres.");
                    System.out.println("Devuelta al menu principal, para introducir de forma correcta los datos.");
                    System.out.println("Presione ENTER para continuar...");
                    sca.nextLine();
                    MenuInicial();
                    break;
                } else {
                    for (int k = 0; k < codDescActual.length; k++) {
                        codDesc[k] = codDescActual[k];
                        porcentDesc[k] = porcentDescActual[k];
                    }

                }
            }
        } else {

            if (ComprobarLongitud(codDescActual)) {
                for (int i = 0; i < codDescActual.length - 1; i++) {
                    for (int j = i + 1; j < codDescActual.length; j++) {
                        if ((porcentDescActual[i] <= 0 || porcentDescActual[i] > 100)
                                || (codDescActual[i].equals(codDescActual[j]))) {
                            System.out.println("Hay codigos de descuento duplicados o "
                                    + "el porcentaje tiene un valor menor a 0 o mayor a 100.");
                            System.out.println("Devuelta al menu principal, para introducir de forma correcta los datos.");
                            System.out.println("Presione ENTER para continuar...");
                            sca.nextLine();
                            MenuInicial();
                            break;
                        } else {
                            for (int k = 0; k < codDescActual.length; k++) {
                                codDesc[k] = codDescActual[k];
                                porcentDesc[k] = porcentDescActual[k];
                            }
                        }
                    }
                }
            } else {
                System.out.println("El codigo de descuento es diferente a 4 caracteres.");
                System.out.println("Devuelta al menu principal, para introducir de forma correcta los datos.");
                System.out.println("Presione ENTER para continuar...");
                sca.nextLine();
                MenuInicial();
            }

        }
        System.out.println("Los datos han sido guardados.");
        System.out.println("Presione ENTER para continuar...");
        sca.nextLine();
        MenuInicial();
    }

    public static boolean ComprobarLongitud(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].length() == 4) {
                return true;
            }
        }
        return false;
    }

    public static void RealizarVentas() {
        if (nombreProd == null) {
            System.out.println("No ha agregado ningún producto.");
            System.out.println("Presione ENTER para continuar...");
            sca.nextLine();
            MenuInicial();
        } else {
            System.out.println("Ingrese el nombre completo del cliente: ");
            String nombre = sca.nextLine();
            System.out.println("A continuación debe ingresar un NIT, el NIT debe tener entre 8 y 9 digitos.");
            System.out.println("Sino ingresa un NIT valido o no tiene (dejar en blanco) será CF.");
            System.out.println("Ingrese su NIT: ");
            String nit = sca.nextLine();
            nit = ComprobarNit(nit);
            cantidad = new int[nombreProd.length];
            int indice[] = new int[nombreProd.length];
            total = new float[nombreProd.length];
            for (int i = 0; i < nombreProd.length; i++) {
                System.out.println("----------------------- " + i + " ----------------------");
                System.out.println("Nombre del Producto: " + nombreProd[i]);
                System.out.println("Precio del Producto: Q." + precioProd[i]);
                indice[i] = i;
            }
            int n = 0;
            float subtotal = 0;
            do {
                n = 0;
                System.out.println("-----------------------------------------------");
                System.out.println("Ingrese el No. del producto que desea añadir: ");
                n = Integer.parseInt(sca.nextLine());

                for (int i = 0; i < nombreProd.length; i++) {
                    if (indice[i] == n) {
                        System.out.println("Ingrese la cantidad del producto que desea (" + i + "):");
                        cantidad[i] += Integer.parseInt(sca.nextLine());
                        total[i] = precioProd[i] * cantidad[i];
                        subtotal += total[i];
                    }
                }
                System.out.println("-----------------------------------------------");
                System.out.println("Si ya no desea ingresar datos, entonces escriba \"-1\"");
            } while (n >= 0);
            System.out.println("-----------------------------------------------");
            System.out.println("Total de la compra: Q" + subtotal);
            System.out.println("-----------------------------------------------");
            System.out.println("A continuación, ingrese un código de descuento, sino tiene uno ingrese \"0\"");
            String codDesActual = sca.nextLine();
            EmitirFactura(nombre, nit, subtotal, codDesActual, cantidad);
        }
    }

    public static void EmitirFactura(String nombreCliente, String noNIT, float subtotal, String codDes, int[] cantidad) {
        float porcenDesc = 0;
        String mensaje = "No hay porcentaje de descuento.";
        if (codDesc != null) {
            for (int i = 0; i < codDesc.length; i++) {
                if (codDesc[i] != null) {
                    for (int j = 0; j < codDesc.length; j++) {
                        if (codDesc[j].equals(codDes)) {
                            porcenDesc = porcentDesc[j];
                        }
                    }
                }
            }
        }
        System.out.println("Nombre de la empresa: SUPER-25");
        System.out.println("Nombre del cajero: Andres Alejandro Quezada Cabrera");
        System.out.println("Nombre del cliente: " + nombreCliente);
        System.out.println("Número de NIT: " + noNIT);
        System.out.println("Fecha de Emision: 10-02-2023");
        for (int i = 0; i < nombreProd.length; i++) {
            if (cantidad[i] != 0) {
                System.out.println("----------------------- " + i + " ----------------------");
                System.out.println("Nombre del Producto: " + nombreProd[i]);
                System.out.println("Precio del Producto: Q." + precioProd[i]);
                System.out.println("Cantidad Comprada: " + cantidad[i]);
                System.out.println("Total: Q" + total[i]);
                System.out.println("-----------------------------------------------");
            }
        }
        System.out.println("Subtotal: Q." + subtotal);
        if (porcenDesc != 0) {
            mensaje = "Porcentaje de descuento: " + (porcenDesc / 100);
            subtotal = subtotal * ((100 - porcenDesc) / 100);
        }
        System.out.println(mensaje);
        System.out.println("El total de la compra: Q." + subtotal);
        System.out.println("Presione ENTER para continuar...");
        sca.nextLine();
        MenuInicial();
    }

    public static void EmitirReporte() {
        if (nombreProd != null && cantidad != null) {
            for (int i = 0; i < cantidad.length; i++) {
                for (int j = 0; j < cantidad.length - 1; j++) {
                    if (cantidad[j] < cantidad[j + 1]) {
                        int aux = cantidad[j];
                        cantidad[j] = cantidad[j + 1];
                        cantidad[j + 1] = aux;

                        String aux2 = nombreProd[j];
                        nombreProd[j] = nombreProd[j + 1];
                        nombreProd[j + 1] = aux2;
                    }
                }
            }
            for (int i = 0; i < nombreProd.length; i++) {
                if (cantidad[i] > 1) {
                    System.out.println(i + ") El producto: " + nombreProd[i] + " ha sido vendido " + cantidad[i] + " veces.");
                } else {
                    System.out.println(i + ") El producto: " + nombreProd[i] + " ha sido vendido " + cantidad[i] + " vez.");
                }
            }
            System.out.println("Presione ENTER para continuar...");
            sca.nextLine();
            MenuInicial();
        } else {
            System.out.println("No hay productos o no se han efectuados las compras favor ingrese los productos");
            System.out.println("Presione ENTER para continuar...");
            sca.nextLine();
            MenuInicial();
        }
    }
    
}
