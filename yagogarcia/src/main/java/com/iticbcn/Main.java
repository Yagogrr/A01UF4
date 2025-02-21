package com.iticbcn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.checkerframework.checker.units.qual.cd;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.iticbcn.DAO.CompanyiaDAO;
import com.iticbcn.DAO.EstacioDAO;
import com.iticbcn.DAO.TrajecteDAO;
import com.iticbcn.DAO.TrenDAO;
import com.iticbcn.model.*;

public class Main {

    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            buclePrincipal(sessionFactory);
        } catch (Exception e) {
            System.err.println("Error inesperado al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void buclePrincipal(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession();
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                try {
                    opcionesMenu();
                    String respuesta = inputReader.readLine();
                    if (respuesta == null) {
                        System.out.println("Conexión de entrada cerrada. Saliendo.");
                        break;
                    }
                    procesarOpcion(respuesta, inputReader, sessionFactory, session);
                } catch (IOException e) {
                    System.err.println("Error de E/S: " + e.getMessage());
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (HibernateException e) {
            System.err.println("Error al inicializar Hibernate: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al inicializar BufferedReader: " + e.getMessage());
        }
    }

    private static void opcionesMenu() {
        System.out.printf(
                "\n¿Qué tipo de tarea quieres hacer?%n" +
                        "a) Añadir entidad%n" +
                        "b) Consultar entidad%n" +
                        "c) Eliminar entidad%n" +
                        "d) Actualizar entidad%n" +
                        "e) Cantidad entidades%n" +
                        "f) Salir%n");
    }

    private static void procesarOpcion(String respuesta, BufferedReader inputReader, SessionFactory sessionFactory,
            Session session) throws IOException, Exception {
        switch (respuesta.trim().toLowerCase()) {
            case "a":
                añadirEntidad(inputReader, sessionFactory);
                break;
            case "b":
                buscarEntidad(inputReader, sessionFactory);
                break;
            case "c":
                eliminarEntidad(inputReader, sessionFactory);
                break;
            case "d":
                actualizarEntidad(inputReader, sessionFactory);
                break;
            case "e":
                contarEntidades(inputReader, sessionFactory);
                break;
            case "f":
                System.out.println("Adeu!");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    public static void añadirEntidad(BufferedReader input, SessionFactory session) throws IOException, Exception {
        System.out.printf("\n¿Qué entidad quieres agregar?%n" +
                "a) Companyia%n" +
                "b) Tren%n" +
                "c) Trajecte%n" +
                "d) Estacio%n");
        String respuesta = input.readLine();
        if (respuesta == null)
            return;

        switch (respuesta.trim().toLowerCase()) {
            case "a":
                CompanyiaDAO cdao = new CompanyiaDAO(session);
                cdao.save(crearCompanyia(input));
                // CompanyiaDAO.añadirCompanyia(crearCompanyia(input), session);
                break;
            case "b":
                TrenDAO tdao = new TrenDAO(session);
                tdao.save(crearTren(input));
                // TrenDAO.añadirTren(crearTren(input), session);
                break;
            case "c":
                TrajecteDAO trajdao = new TrajecteDAO(session);
                trajdao.save(crearTrajecte(input));
                break;
            case "d":
                EstacioDAO edao = new EstacioDAO(session);
                edao.save(crearEstacio(input));
                // EstacioDAO.añadirEstacio(crearEstacio(input), session);
                break;
            default:
                System.out.println("Entidad no válida.");
        }
    }

    public static void buscarEntidad(BufferedReader reader, SessionFactory session) throws IOException, Exception {
        System.out.print("\n¿Quiere buscar una entidad en específico? (s/n): ");
        String especificoStr = reader.readLine();
        if (especificoStr == null)
            return;
        boolean especifico = especificoStr.trim().equalsIgnoreCase("s");

        int id = -1;
        if (especifico) {
            try {
                System.out.print("Introduzca el ID de la entidad: ");
                String idSTR = reader.readLine();
                if (idSTR == null)
                    return;
                id = Integer.parseInt(idSTR.trim());
            } catch (NumberFormatException e) {
                System.out.println("ID inválido. Debe ser un número entero.");
                return;
            }
        }

        System.out.printf("\n¿Qué entidad quieres consultar?%n" +
                "a) Companyia%n" +
                "b) Tren%n" +
                "c) Trajecte%n" +
                "d) Estacio%n");
        String respuesta = reader.readLine();
        if (respuesta == null)
            return;

        switch (respuesta.trim().toLowerCase()) {
            case "a":
                CompanyiaDAO cdao = new CompanyiaDAO(session);
                if (especifico)
                    System.out.println(cdao.get(id));
                // CompanyiaDAO.consultarCompanyia(id, session);
                else
                    for(Companyia c : cdao.getAll()){
                        System.out.println(c);
                    }
                break;
            case "b":
                TrenDAO tdao = new TrenDAO(session);
                if (especifico)
                    System.out.println(tdao.get(id));
                // TrenDAO.consultarTren(id, session);
                else
                    for(Tren t : tdao.getAll()){
                        System.out.println(t);
                    }
                // TrenDAO.consultarTrenes(session);
                break;
            case "c":
                TrajecteDAO trajdao = new TrajecteDAO(session);
                if (especifico)
                    System.out.println(trajdao.get(id));
                // TrajecteDAO.consultarTrajecte(id, session);
                else
                    for(Trajecte t : trajdao.getAll()){
                        System.out.println(t);
                    }
                // TrajecteDAO.consultarTrayectos(session);
                break;
            case "d":
                EstacioDAO edao = new EstacioDAO(session);
                if (especifico)
                System.out.println(edao.get(id));
                // EstacioDAO.consultarEstacio(id, session);
                else
                    for(Estacio e : edao.getAll()){
                        System.out.println(e);
                    }
                // EstacioDAO.consultarEstaciones(session);
                break;
            default:
                System.out.println("Entidad no válida.");
        }
    }

    public static void contarEntidades(BufferedReader reader, SessionFactory session) throws IOException, Exception {
        System.out.printf("\n¿Qué entidad quieres saber la cantidad?%n" +
                "a) Companyia%n" +
                "b) Tren%n" +
                "c) Trajecte%n" +
                "d) Estacio%n");
        String respuesta = reader.readLine();
        if (respuesta == null)
            return;

        switch (respuesta.trim().toLowerCase()) {
            case "a":
                CompanyiaDAO cdao = new CompanyiaDAO(session);
                long companyCount = cdao.count();
                System.out.println("Hay " + companyCount + (companyCount == 1 ? " compañía." : " compañías."));
                break;
            case "b":
                TrenDAO tdao = new TrenDAO(session);
                long trainCount = tdao.count();
                System.out.println("Hay " + trainCount + (trainCount == 1 ? " tren." : " trenes."));
                break;
            case "c":
                TrajecteDAO trajdao = new TrajecteDAO(session);
                long routeCount = trajdao.count();
                System.out.println("Hay " + routeCount + (routeCount == 1 ? " trayecto." : " trayectos."));
                break;
            case "d":
                EstacioDAO edao = new EstacioDAO(session);
                long stationCount = edao.count();
                System.out.println("Hay " + stationCount + (stationCount == 1 ? " estación." : " estaciones."));
                break;
            default:
                System.out.println("Entidad no válida.");
        }

    }

    public static void eliminarEntidad(BufferedReader reader, SessionFactory session) throws IOException, Exception {
        int id = leerIdEntidad(reader);
        if (id == -1)
            return;

        System.out.printf("\n¿Qué entidad quieres eliminar?%n" +
                "a) Companyia%n" +
                "b) Tren%n" +
                "c) Trajecte%n" +
                "d) Estacio%n");
        String respuesta = reader.readLine();
        if (respuesta == null)
            return;

        switch (respuesta.trim().toLowerCase()) {
            case "a":
                CompanyiaDAO cdao = new CompanyiaDAO(session);
                cdao.delete(cdao.get(id));
                // CompanyiaDAO.eliminarCompanyia(id, session);
                break;
            case "b":
                TrenDAO tdao = new TrenDAO(session);
                tdao.delete(tdao.get(id));
                // TrenDAO.eliminarTren(id, session);
                break;
            case "c":
                TrajecteDAO trajdao = new TrajecteDAO(session);
                trajdao.delete(trajdao.get(id));
                // TrajecteDAO.eliminarTrajecte(id, session);
                break;
            case "d":
                EstacioDAO edao = new EstacioDAO(session);
                edao.delete(edao.get(id));
                // EstacioDAO.eliminarEstacio(id, session);
                break;
            default:
                System.out.println("Entidad no válida.");
        }
    }

    public static void actualizarEntidad(BufferedReader reader, SessionFactory session) throws IOException, Exception {
        int id = leerIdEntidad(reader);
        if (id == -1)
            return;

        System.out.printf("\n¿Qué entidad quieres editar?%n" +
                "a) Companyia%n" +
                "b) Tren%n" +
                "c) Trajecte%n" +
                "d) Estacio%n");
        String respuesta = reader.readLine();
        if (respuesta == null)
            return;

        switch (respuesta.trim().toLowerCase()) {
            case "a":
                actualizarCompanyia(reader, id, session);
                break;
            case "b":
                actualizarTren(reader, id, session);
                break;
            case "c":
                actualizarTrajecte(reader, id, session);
                break;
            case "d":
                actualizarEstacio(reader, id, session);
                break;
            default:
                System.out.println("Entidad no válida.");
        }
    }

    private static int leerIdEntidad(BufferedReader reader) throws IOException {
        try {
            System.out.print("Introduzca el ID de la entidad: ");
            String idSTR = reader.readLine();
            if (idSTR == null)
                return -1;
            return Integer.parseInt(idSTR.trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Debe ser un número entero.");
            return -1;
        }
    }

    private static void actualizarCompanyia(BufferedReader reader, int id, SessionFactory session)
            throws IOException, Exception {
        System.out.print("Introduzca el nuevo nombre de la compañía: ");
        String nuevoNombre = reader.readLine();
        if ((nuevoNombre == null) || (nuevoNombre.isBlank())) {
            return;
        }
        CompanyiaDAO cdao = new CompanyiaDAO(session);
        Companyia c = cdao.get(id);
        c.setNameCompanie(nuevoNombre);
        cdao.update(c);
        // CompanyiaDAO.editarCompanyia(id, nuevoNombre, session);
    }

    private static void actualizarTren(BufferedReader reader, int id, SessionFactory session)
            throws IOException, Exception {
        try {
            System.out.print("Introduzca el nuevo nombre del tren: ");
            String nuevoNombre = reader.readLine();
            if (nuevoNombre == null)
                return;
            if (nuevoNombre.isBlank()) {
                throw new IllegalArgumentException("El nombre del tren no puede estar en blanco.");
            }

            System.out.print("Introduzca la nueva capacidad del tren: ");
            String capacidadStr = reader.readLine();
            if (capacidadStr == null)
                return;
            int nuevaCapacidad = Integer.parseInt(capacidadStr.trim());

            TrenDAO tdao = new TrenDAO(session);
            Tren t = tdao.get(id);
            t.setCapacity(nuevaCapacidad);
            t.setNameTrain(nuevoNombre);
            tdao.update(t);
            // TrenDAO.editarTren(id, nuevoNombre, session, nuevaCapacidad);

        } catch (NumberFormatException e) {
            System.out.println("Capacidad inválida. Debe ser un número entero.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void actualizarTrajecte(BufferedReader reader, int id, SessionFactory session) throws IOException, Exception {
        try {
            System.out.print("Introduzca el nombre del trayecto: ");
            String nombre = reader.readLine();
            if (nombre == null)
                return;
            if (nombre.isBlank())
                throw new IllegalArgumentException("El nombre no puede estar en blanco.");

            System.out.print("Introduzca la hora de llegada del trayecto: ");
            String horaLlegada = reader.readLine();
            if (horaLlegada == null)
                return;
            if (horaLlegada.isBlank())
                throw new IllegalArgumentException("La hora de llegada no puede estar en blanco.");

            System.out.print("Introduzca la hora de salida del trayecto: ");
            String horaSalida = reader.readLine();
            if (horaSalida == null)
                return;
            if (horaSalida.isBlank())
                throw new IllegalArgumentException("La hora de salida no puede estar en blanco.");

            System.out.print("Introduzca el precio del trayecto: ");
            String precioStr = reader.readLine();
            if (precioStr == null)
                return;
            int precio = Integer.parseInt(precioStr.trim());

            TrajecteDAO trajdao = new TrajecteDAO(session);
            Trajecte traj = trajdao.get(id);
            traj.setEntryHourRoute(horaLlegada);
            traj.setExitHour(horaSalida);
            traj.setName(nombre);
            traj.setPrice(precio);
            trajdao.update(traj);
            //TrajecteDAO.editarTrajecte(id, nombre, horaLlegada, horaSalida, session, precio);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void actualizarEstacio(BufferedReader reader, int id, SessionFactory session) throws IOException, Exception {
        System.out.print("Introduzca el nuevo nombre de la estación: ");
        String nombreEst = reader.readLine();
        if (nombreEst == null)
            return;
        if (nombreEst.isBlank()) {
            System.out.println("El nombre de la estación no puede estar en blanco.");
            return;
        }
        EstacioDAO edao = new EstacioDAO(session);
        Estacio e = edao.get(id);
        e.setName(nombreEst);
        edao.update(e);
        //EstacioDAO.editarEstacio(id, nombreEst, session);
    }

    public static Companyia crearCompanyia(BufferedReader reader) throws IOException {
        String nameCompanie = leerInputsNoVacios(reader, "Introduzca el nombre de su compañía: ",
                "El nombre de la compañía no puede estar en blanco.");
        Companyia companyia = new Companyia(nameCompanie);

        boolean nuevoTren = true;
        while (nuevoTren) {
            if (!preguntarSiNo(reader, "Quiere añadir un tren? (s/n): ")) {
                nuevoTren = false;
            } else {
                Tren tren = crearTrenForCompanyia(reader);
                if (tren != null) {
                    companyia.addTrain(tren);
                }
            }
        }
        return companyia;
    }

    private static Tren crearTrenForCompanyia(BufferedReader reader) throws IOException {
        String nombreTren = leerInputsNoVacios(reader, "Introduzca el nombre del Tren: ",
                "El nombre del tren no puede estar en blanco.");
        int capacidadTren = leerNumero(reader, "Introduzca la capacidad del Tren: ",
                "Tienes que introducir un número en la capacidad.");
        if (capacidadTren != -1) {
            return new Tren(nombreTren, capacidadTren);
        }
        return null;
    }

    public static Tren crearTren(BufferedReader reader) throws IOException {
        String nombreTren = leerInputsNoVacios(reader, "Introduzca el nombre de su Tren: ",
                "El nombre del tren no puede estar en blanco.");
        int capacidadTren = leerNumero(reader, "Introduzca la capacidad de su Tren: ",
                "Tienes que introducir un número en la capacidad.");
        if (capacidadTren == -1)
            return null;

        Companyia companyia = crearCompanyiaForTren(reader);
        if (companyia == null)
            return null;

        Tren tren = new Tren(nombreTren, capacidadTren);
        tren.setCompanie(companyia);

        boolean nuevoTrayecto = true;
        while (nuevoTrayecto) {
            if (!preguntarSiNo(reader, "Quiere añadir un trayecto? (s/n): ")) {
                nuevoTrayecto = false;
            } else {
                Trajecte trajecte = crearTrajecteForTren(reader);
                if (trajecte != null) {
                    tren.addTrajecte(trajecte);
                }
            }
        }
        return tren;
    }

    private static Companyia crearCompanyiaForTren(BufferedReader reader) throws IOException {
        String nameCompanie = leerInputsNoVacios(reader, "Introduzca el nombre de su compañía: ",
                "El nombre de la compañía no puede estar en blanco.");
        return new Companyia(nameCompanie);
    }

    private static Trajecte crearTrajecteForTren(BufferedReader reader) throws IOException {
        String nombre = leerInputsNoVacios(reader, "Diga el nombre de su trayecto: ",
                "El nombre del trayecto no puede estar en blanco.");
        String horaLlegada = leerInputsNoVacios(reader, "Diga la hora de entrada de su trayecto: ",
                "La hora de llegada no puede estar en blanco.");
        String horaSalida = leerInputsNoVacios(reader, "Diga la hora de salida de su trayecto: ",
                "La hora de salida no puede estar en blanco.");
        int precio = leerNumero(reader, "Diga el precio de su trayecto: ", "El precio tiene que ser un número.");
        if (precio == -1)
            return null;

        return new Trajecte(nombre, precio, horaSalida, horaLlegada);
    }

    public static Trajecte crearTrajecte(BufferedReader reader) throws IOException {
        String nombre = leerInputsNoVacios(reader, "Diga el nombre de su trayecto: ",
                "El nombre del trayecto no puede estar en blanco.");
        String horaLlegada = leerInputsNoVacios(reader, "Diga la hora de entrada de su trayecto: ",
                "La hora de llegada no puede estar en blanco.");
        String horaSalida = leerInputsNoVacios(reader, "Diga la hora de salida de su trayecto: ",
                "La hora de salida no puede estar en blanco.");
        int precio = leerNumero(reader, "Diga el precio de su trayecto: ", "El precio tiene que ser un número.");
        if (precio == -1)
            return null;

        Trajecte trajecte = new Trajecte(nombre, precio, horaSalida, horaLlegada);

        boolean nuevoTren = true;
        while (nuevoTren) {
            if (!preguntarSiNo(reader, "Quiere añadir un tren mas al trayecto? (s/n): ")) {
                nuevoTren = false;
            } else {
                Tren tren = crearTrenForTrajecte(reader);
                if (tren != null) {
                    trajecte.addTren(tren);
                }
            }
        }

        boolean nuevaEstacion = true;
        while (nuevaEstacion) {
            if (!preguntarSiNo(reader, "Quiere añadir una estación mas al trayecto? (s/n): ")) {
                nuevaEstacion = false;
            } else {
                Estacio estacio = crearEstacioForTrajecte(reader);
                if (estacio != null) {
                    trajecte.addEstacio(estacio);
                }
            }
        }
        return trajecte;
    }

    private static Tren crearTrenForTrajecte(BufferedReader reader) throws IOException {
        String nombreTren = leerInputsNoVacios(reader, "Introduzca el nombre del Tren: ",
                "El nombre del tren no puede estar en blanco.");
        int capacidadTren = leerNumero(reader, "Introduzca la capacidad del Tren: ",
                "Tienes que introducir un número en la capacidad.");
        if (capacidadTren != -1) {
            Companyia companyia = crearCompanyiaForTren(reader);
            if (companyia != null) {
                Tren tren = new Tren(nombreTren, capacidadTren);
                tren.setCompanie(companyia);
                return tren;
            }
        }
        return null;
    }

    private static Estacio crearEstacioForTrajecte(BufferedReader reader) throws IOException {
        String nameStation = leerInputsNoVacios(reader, "Diga el nombre de la estación: ",
                "El nombre de la estación no puede estar en blanco.");
        return new Estacio(nameStation);
    }

    public static Estacio crearEstacio(BufferedReader reader) throws IOException {
        String nameStation = leerInputsNoVacios(reader, "Diga el nombre de la estación: ",
                "El nombre de la estación no puede estar en blanco.");
        Estacio estacio = new Estacio(nameStation);

        boolean nuevoTrayecto = true;
        while (nuevoTrayecto) {
            if (!preguntarSiNo(reader, "Quiere añadir un trayecto mas a la estación? (s/n): ")) {
                nuevoTrayecto = false;
            } else {
                Trajecte trajecte = crearTrajecteForEstacio(reader);
                if (trajecte != null) {
                    estacio.addTrajecte(trajecte);
                }
            }
        }
        return estacio;
    }

    private static Trajecte crearTrajecteForEstacio(BufferedReader reader) throws IOException {
        String nombre = leerInputsNoVacios(reader, "Diga el nombre de su trayecto: ",
                "El nombre del trayecto no puede estar en blanco.");
        String horaLlegada = leerInputsNoVacios(reader, "Diga la hora de entrada de su trayecto: ",
                "La hora de llegada no puede estar en blanco.");
        String horaSalida = leerInputsNoVacios(reader, "Diga la hora de salida de su trayecto: ",
                "La hora de salida no puede estar en blanco.");
        int precio = leerNumero(reader, "Diga el precio de su trayecto: ", "El precio tiene que ser un número.");
        if (precio != -1) {
            return new Trajecte(nombre, precio, horaSalida, horaLlegada);
        }
        return null;
    }

    private static String leerInputsNoVacios(BufferedReader reader, String prompt, String errorMessage)
            throws IOException {
        String input;
        do {
            System.out.print(prompt);
            input = reader.readLine();
            if (input == null)
                return null;
            if (input.trim().isBlank()) {
                System.out.println(errorMessage);
            } else {
                return input.trim();
            }
        } while (true);
    }

    private static int leerNumero(BufferedReader reader, String prompt, String errorMessage) throws IOException {
        while (true) {
            System.out.print(prompt);
            String inputStr = reader.readLine();
            if (inputStr == null)
                return -1;
            try {
                return Integer.parseInt(inputStr.trim());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    private static boolean preguntarSiNo(BufferedReader reader, String prompt) throws IOException {
        while (true) {
            System.out.print(prompt);
            String response = reader.readLine();
            if (response == null)
                return false;
            if (response.trim().equalsIgnoreCase("s")) {
                return true;
            } else if (response.trim().equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Por favor, responda 's' para sí o 'n' para no.");
            }
        }
    }
}