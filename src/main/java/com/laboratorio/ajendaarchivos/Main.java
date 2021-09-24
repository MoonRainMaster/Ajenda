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
            for (int i = nombre.length(); i < 30; i++) {
                archivo.writeByte(0);
            }

            archivo.writeByte(edad);

            archivo.writeInt(tel);

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
                System.out.println("Teléfono: "+ tel + "\n");
            }

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
        byte edad, personas;
        int tel;

        System.out.println("Ingrese la cantidad de personas a almacenar");
        personas = leer.nextByte();

        for (int i = 0; i < personas; i++) {
            System.out.println("    \nAlmacenamiento de datos");
            System.out.println("Ingrese el nombre");
            nombre = letras.nextLine();
            System.out.println("Ingese edad");
            edad = leer.nextByte();
            System.out.println("Ingrese número de teléfono");
            tel = leer.nextInt();

            main.escrituraAleatoria(nombre, edad, tel);
        }

        System.out.println("\n  Agenda:");

        main.lecturaAleatoria();
    }
}
