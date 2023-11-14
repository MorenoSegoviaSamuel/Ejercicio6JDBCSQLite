package org.example;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        Path rutaBaseDatos = Path.of("src", "main", "resources", "db", "f12006sqlite.db");

        Piloto p = new Piloto("MRW","Walter","White", "03-09-1975","USA","https://en.wikipedia.org/wiki/Walter_White_%28Breaking_Bad%29");

        //Crear piloto
        //OperacionesCRUDPilotos.crearPiloto(String.valueOf(rutaBaseDatos),p);

        //Leer piloto
        System.out.println("\n-33-\n");
        OperacionesCRUDPilotos.LeerPiloto(String.valueOf(rutaBaseDatos), 4);

        //Leer lista de pilotos

        System.out.println("\n-LISTA DE PILOTOS-\n");
        OperacionesCRUDPilotos.LeerPilotos(String.valueOf(rutaBaseDatos));

        //Actualizar piloto
        //OperacionesCRUDPilotos.actualizarPiloto(String.valueOf(rutaBaseDatos),p,38);

        //Borrar piloto y comprobar que estan borrados
        OperacionesCRUDPilotos.borrarPiloto(String.valueOf(rutaBaseDatos),p);

        System.out.println("\n-NUEVA BASE DE DATOS-\n");
        OperacionesCRUDPilotos.LeerPilotos(String.valueOf(rutaBaseDatos));

        System.out.println("CLASIFICACIÓN:\n");
        OperacionesCRUDPilotos.MostrarClasificacionPiloto(String.valueOf(rutaBaseDatos));

        System.out.println("CLASIFICACIÓN CONSTRUCTORES:\n");
        OperacionesCRUDPilotos.mostrarClasificacionConstructores(String.valueOf(rutaBaseDatos));
    }

    }
