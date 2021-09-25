package com.laboratorio.ajendaarchivos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moonrain
 */
public class Main {

    private void escrituraAleatoria(String nombre, byte edad, int tel) {
        try {
            RandomAccessFile archivo = new RandomAccessFile("datos.bin", "rw");

            long longitudArchivo = archivo.length();
            archivo.seek(longitudArchivo);

            archivo.writeBytes(nombre);

            archivo.writeBytes("$");

            archivo.writeInt(tel);

            archivo.writeByte(edad);

            archivo.writeBytes("#");

            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void lecturaAleatoria() {
        try {
            RandomAccessFile archivo = new RandomAccessFile("datos.bin", "r"); //r para escribir - w para escribir - rw para hacer ambos

            archivo.seek(0);

            Integer bloques = 30 + 1 + 4;

            for (int i = 0; i < archivo.length() / bloques; i++) {
                byte nombreBytes[] = new byte[30];
                archivo.read(nombreBytes);
                System.out.println("Nombre: " + new String(nombreBytes));

                byte edad = archivo.readByte();
                System.out.println("Edad: " + edad);

                int tel = archivo.readInt();
                System.out.println("Teléfono: " + tel + "\n");
            }

            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void escrituraSeparadorBinario(String nombre, byte edad, int tel) {
        try {
            RandomAccessFile archivo = new RandomAccessFile("datos.bin", "rw");

            long longitudArchivo = archivo.length();
            archivo.seek(longitudArchivo);

            archivo.writeBytes(nombre);

            archivo.writeBytes("$");

            archivo.writeInt(tel);

            archivo.writeByte(edad);

            archivo.writeBytes("#");

            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lecturaSeparadorBinario() {
        try {
            RandomAccessFile archivo = new RandomAccessFile("datos.bin", "r");

            archivo.seek(0);

            byte caracter = 0;
            int inicio= 0, fin = 0; 
            long tam = archivo.length();

            do {
                do {
                    
                    if (caracter == 35) {
                        inicio = fin + 5;
                        fin += 5;
                    }
                    
                    caracter = archivo.readByte();
                    fin++;
                    
                } while (caracter != 36);
                
                archivo.seek(inicio);
                
                byte nombreBytes[] = new byte[(fin - inicio) - 1];
                archivo.read(nombreBytes);
                System.out.println("Nombre: " + new String(nombreBytes));
                
                archivo.readByte();
                
                System.out.println("Teléfono: " + archivo.readInt());
                
                System.out.println("Edad: " + archivo.readByte());
                
            } while (fin < (tam - 7));

            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner leer = new Scanner(System.in);
        Scanner letras = new Scanner(System.in);

        String nombre;
        byte edad;
        int tel, opc = 01;
        boolean salir = false;

        do {
            System.out.println("\n  MENU"
                    + "\n1.Archivo secuencial con separadores binarios"
                    + "\n2.Archivo secuencial con separador por tamaño"
                    + "\n3.Salir");

            opc = leer.nextInt();

            switch (opc) {
                case 1:

                    do {

                        System.out.println("    \nAlmacenamiento de datos secuenciales"
                                + "\n1.Ingreser datos a la agenda"
                                + "\n2.Mostrar datos de la agenda"
                                + "\n3.Regresar al menú principal");

                        opc = leer.nextInt();

                        switch (opc) {

                            case 1:
                                System.out.println("Ingrese el nombre completo");
                                nombre = letras.nextLine();
                                System.out.println("Ingese teléfono");
                                tel = leer.nextInt();
                                System.out.println("Ingrese edad");
                                edad = leer.nextByte();

                                main.escrituraSeparadorBinario(nombre, edad, tel);
                                break;

                            case 2:
                                System.out.println("\n  Agenda:");
                                main.lecturaSeparadorBinario();
                                break;

                            case 3:
                                salir = true;
                                break;

                        }

                    } while (!salir);

                    salir = false;

                    break;

                case 2:
                    break;

                case 3:
                    salir = true;
                    break;
            }

        } while (!salir);
    }
}
