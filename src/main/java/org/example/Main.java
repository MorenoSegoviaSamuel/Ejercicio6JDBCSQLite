package org.example;

import java.nio.file.Path;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Path rutaBaseDatos = Path.of("src", "main", "resources", "db", "f12006sqlite.db");


        Piloto p = new Piloto("MRW","Walter","White", LocalDate.of(1982,12,20),"USA","https://en.wikipedia.org/wiki/Walter_White_%28Breaking_Bad%29");


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
        OperacionesCRUDPilotos.mostrarClasificacionPiloto(String.valueOf(rutaBaseDatos));

        System.out.println("CLASIFICACIÓN CONSTRUCTORES:\n");
        OperacionesCRUDPilotos.mostrarClasificacionConstructores(String.valueOf(rutaBaseDatos));
    }

    }
