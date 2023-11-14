package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class OperacionesCRUDPilotos {


    public static void crearPiloto(String path, Piloto p) {
        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:" + path.toString())) {
            String insercionSQL = "INSERT INTO drivers (code, forename, surname, dob, nationality, url) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insercion = conexion.prepareStatement(insercionSQL);
            insercion.setString(1, p.getCode());
            insercion.setString(2, p.getForename());
            insercion.setString(3, p.getSurname());
            insercion.setString(4, p.getDob().toString());
            insercion.setString(5, p.getNationality());
            insercion.setString(6, p.getUrl());

            insercion.executeUpdate();
            insercion.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Piloto LeerPiloto(String path, int n) {
        Piloto p;
        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:" + path.toString())) {
            String consultaSQL = "SELECT *" +
                    "FROM drivers " +
                    "WHERE driverid = ? ";
            PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
            consulta.setInt(1, n);

            ResultSet resultados = consulta.executeQuery();

            p = new Piloto(resultados.getString("code"), resultados.getString("forename"), resultados.getString("surname"), resultados.getString("dob"), resultados.getString("nationality"), resultados.getString("url"));

            System.out.println(p);
            consulta.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    public static List<Piloto> LeerPilotos(String path) {
        Piloto p;
        List<Piloto> listaPilotos = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:" + path.toString())) {
            String consultaSQL = "SELECT *" +
                    "FROM drivers ";
            PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                p = new Piloto(resultados.getString("code"), resultados.getString("forename"), resultados.getString("surname"), resultados.getString("dob"), resultados.getString("nationality"), resultados.getString("url"));
                listaPilotos.add(p);
            }

            listaPilotos.stream().forEach(System.out::println);
            consulta.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPilotos;
    }

    public static void actualizarPiloto(String path, Piloto p, int n) {
        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:" + path.toString())) {
            String updateSQL = "UPDATE drivers SET code=?, forename=?, surname=?, dob=?, nationality=?, url=? WHERE driverid=?";
            PreparedStatement update = conexion.prepareStatement(updateSQL);
            update.setString(1, p.getCode());
            update.setString(2, p.getForename());
            update.setString(3, p.getSurname());
            update.setString(4, p.getDob());
            update.setString(5, p.getNationality());
            update.setString(6, p.getUrl());
            update.setInt(7, n);

            int filasActualizadas = update.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Piloto actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún piloto con el ID proporcionado.");
            }

            OperacionesCRUDPilotos.LeerPiloto(path, n);
            update.close();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el piloto: " + e.getMessage());
        }

    }

    public static void borrarPiloto(String path, Piloto p) {

        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:" + path.toString())) {
            String borradoSQL = "DELETE FROM drivers WHERE code = ?";
            PreparedStatement borrado = conexion.prepareStatement(borradoSQL);
            borrado.setString(1, p.getCode());
            borrado.executeUpdate();

            borrado.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void MostrarClasificacionPiloto(String path) {
        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:" + path.toString())) {
            String consultaSQL = "SELECT drivers.driverid, drivers.forename, drivers.surname, SUM(results.points) AS total_points " +
                    "FROM drivers " +
                    "JOIN results ON drivers.driverid = results.driverid " +
                    "GROUP BY drivers.driverid, drivers.forename, drivers.surname " +
                    "ORDER BY total_points DESC";
            PreparedStatement consulta = conexion.prepareStatement(consultaSQL);

            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                System.out.println("-PILOTO-");
                System.out.println(resultados.getInt("driverid"));
                System.out.println(resultados.getString("forename"));
                System.out.println(resultados.getString("surname"));
                System.out.println();



            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarClasificacionConstructores(String rutaBaseDatos){
        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:" + rutaBaseDatos.toString())) {
            String consultaSQl = "SELECT constructors.name, SUM(results.points) AS puntos " +
                    "FROM constructors " +
                    "INNER JOIN drivers ON constructors.constructorid = drivers.constructorid " +
                    "INNER JOIN results ON drivers.driverid = results.driverid " +
                    "GROUP BY constructors.name " +
                    "ORDER BY puntos DESC";

            PreparedStatement consulta = conexion.prepareStatement(consultaSQl);
            ResultSet resultado = consulta.executeQuery();
            System.out.println("Clasificación de escuderias: ");
            while (resultado.next()){
                System.out.println(resultado.getString("name") + " " + resultado.getInt("puntos"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

