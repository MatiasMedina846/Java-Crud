import java.sql.*;
import java.util.HashSet;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        HashSet<Equipo> equipos = new HashSet<>();
        HashSet<Jugador> jugadores = new HashSet<>();
        HashSet<Estadio>estadios = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        try{ //En el siguiente try/catch nos conectamos a la base de datos para crear las tablas correspondientes a cada clase.
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/act7_mrbd","root","ramiro12321");
            Statement stmt=con.createStatement();

            String sqlEquipos= "CREATE TABLE IF NOT EXISTS equipos (" +
                    "id_equipo INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre_equipo VARCHAR(100) NOT NULL, " +
                    "ciudad VARCHAR(100) " +
                    ")";
            stmt.executeUpdate(sqlEquipos);
            String sqlJugadores = "CREATE TABLE IF NOT EXISTS jugadores (" +
                    "id_jugador INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre_jugador VARCHAR(100) NOT NULL, " +
                    "apellido_jugador VARCHAR(100), " +
                    "id_equipo INT NOT NULL, " +
                    "FOREIGN KEY (id_equipo) REFERENCES equipos (id_equipo) " +
                    ")";
            stmt.executeUpdate(sqlJugadores);

            String sqlEstadio= "CREATE TABLE IF NOT EXISTS Estadio ("+
                    "nombre_estadio VARCHAR(50) PRIMARY KEY, " +
                    "id_equipo int NOT NULL, " +
                    "ubicacion VARCHAR(100) NOT NULL, " +
                    "FOREIGN KEY (id_equipo) REFERENCES equipos (id_equipo) " +
                    ")";
            stmt.executeUpdate(sqlEstadio);

            con.close();
        } catch(Exception e){ System.out.println(e);}
        System.out.println("INGRESE UNA OPCION: \n" +
                "-----------------------------------\n" + //Aqui se muestra un menu, donde el usuario podra elegir la opcion que desee ejecutar.
                "1:AGREGAR EQUIPO \n" +
                "2:AGREGAR JUGADOR \n" +
                "3:AGREGAR ESTADIO \n" +
                "4:CONSULTAR DATOS REGISTRADOS");
        int opcion= sc.nextInt();
        do{
            switch (opcion) {
                case 1: //Se creara un objeto Equipo y se insertara en la tabla de la base de datos.
                    agregarEquipo(equipos);
                    mostrarEquipos(equipos);
                    break;
                case 2: //Se creara un objeto Jugador y se insertara en la tabla de la base de datos.
                    agregarJugadores(jugadores, equipos);
                    mostrarJugadores(jugadores);
                    break;
                case 3: //Se creara un objeto Estadio y se insertara en la tabla de la base de datos.
                    agregarEstadio(estadios, equipos);
                    mostrarEstadios(estadios);
                    break;
                case 4: //Se abrira un nuevo menu para consulta de datos.
                    System.out.println("INGRESE UNA OPCION:  \n" +
                            "----------------------------------\n" +
                            "1:CONSULTAR REGISTRO EQUIPOS \n" +
                            "2:CONSULTAR REGISTRO JUGADORES \n" +
                            "3:CONSULTAR REGISTRO ESTADIOS");
                    int opcion1 = sc.nextInt();
                    do {
                        switch (opcion1) {
                            case 1: //Se conecta a la base de datos y se consultan datos de la tabla Equipos.
                                System.out.println("id / nombre      / ciudad");
                                consultarDatosEquipos();
                                break;
                            case 2: //Se conecta a la base de datos y se consultan datos de la tabla Jugadores.
                                System.out.println("id / nombre / apellido / id_equipo");
                                consultarDatosJugadores();
                                break;
                            case 3: //Se conecta a la base de datos y se consultan datos de la tabla Estadio.
                                System.out.println("nombre     /id_equipo/ ubicación  ");
                                consultarDatosEstadios();
                                break;

                        }
                        System.out.println("INGRESE UNA OPCION: \n" +
                                "---------------------------------\n" +
                                "1:CONSULTAR REGISTRO EQUIPOS \n" +
                                "2:CONSULTAR REGISTRO JUGADORES \n" +
                                "3:CONSULTAR REGISTRO ESTADIOS \n" +
                                "4:VOLVER AL MENU PRINCIPAL");
                        opcion1 = sc.nextInt();
                    }while(opcion1!=4);

            }
            System.out.println("INGRESE UNA OPCION:  \n" +
                    "----------------------------------\n" +
                    "1:AGREGAR EQUIPO \n" +
                    "2:AGREGAR JUGADOR \n" +
                    "3:AGREGAR ESTADIO \n" +
                    "4:CONSULTAR DATOS REGISTRADOS \n" +
                    "5:SALIR");
            opcion= sc.nextInt();
        }while(opcion!=5);
        if (opcion==5) {
            System.out.println("------------------------------------");
            System.out.println("HASTA PRONTO");
        }
    }
    //Aqui estan todos los metodos relacionados a Equipos.
    private static void agregarEquipo(HashSet<Equipo> equipos){
        Scanner sc= new Scanner(System.in);
        Equipo equipo1 =  new Equipo();
        System.out.println("INGRESE EL ID DEL EQUIPO: ");
        equipo1.setId_equipo(sc.nextInt());
        sc.nextLine();
        System.out.println("INGRESE EL NOMBRE DEL EQUIPO: ");
        equipo1.setNombre_equipo(sc.nextLine());
        System.out.println("INGRESE LA CIUDAD DEL EQUIPO: ");
        equipo1.setCiudad(sc.nextLine());
        equipos.add(equipo1);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/act7_mrbd", "root", "ramiro12321");
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO  equipos (id_equipo, nombre_equipo, ciudad) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, equipo1.getId_equipo());
            ps.setString(2, equipo1.getNombre_equipo());
            ps.setString(3, equipo1.getCiudad());

            ps.executeUpdate();
            con.close();
        } catch(Exception e){ System.out.println(e);}
    }
    private static void mostrarEquipos(HashSet<Equipo>equipos){
        for (Equipo e:equipos){
            System.out.println(e);
        }
    }
    private static void consultarDatosEquipos(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/act7_mrbd", "root", "ramiro12321");
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from equipos");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

            con.close();
        } catch(Exception e){ System.out.println(e);}
    }
    //Aqui estan todos los metodos relacionados a Jugadores.
    private static void agregarJugadores(HashSet<Jugador>jugadores, HashSet<Equipo>equipos){
        Scanner sc= new Scanner(System.in);
        Jugador jugador1 = new Jugador();
        System.out.println("INGRESE EL ID DEL JUGADOR ");
        jugador1.setId_jugador(sc.nextInt());
        sc.nextLine();
        System.out.println("INGRESE PRIMERO EL NOMBRE Y LUEGO EL APELLIDO DEL JUGADOR: ");
        jugador1.setNombre_jugador(sc.nextLine());
        jugador1.setApellido_jugador(sc.nextLine());
        mostrarEquipos(equipos);
        System.out.println("SELECCIONE EL ID DEL EQUIPO AL QUE PERTENECE EL JUGADOR");
        int respuesta = sc.nextInt();
        for (Equipo e:equipos){
            if (respuesta==e.getId_equipo()){
                jugador1.setEquipo(e);
            }
        }
        jugadores.add(jugador1);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/act7_mrbd", "root", "ramiro12321");
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO  jugadores (id_jugador, nombre_jugador, apellido_jugador, id_equipo) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, jugador1.getId_jugador());
            ps.setString(2, jugador1.getNombre_jugador());
            ps.setString(3, jugador1.getApellido_jugador());
            ps.setInt(4,jugador1.getEquipo().getId_equipo() );

            ps.executeUpdate();
            con.close();
        } catch(Exception e){ System.out.println(e);}

    }
    private static void mostrarJugadores(HashSet<Jugador>jugadores){
        for (Jugador j:jugadores){
            System.out.println(j);
        }
    }
    private static void consultarDatosJugadores(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/act7_mrbd", "root", "ramiro12321");
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from jugadores");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3) +"  "+ rs.getInt(4));

            con.close();
        } catch(Exception e){ System.out.println(e);}
    }
    //Aqui estan todos los metodos relacionados a Estadios.
    private static void agregarEstadio(HashSet<Estadio>estadios, HashSet<Equipo>equipos){
        Scanner sc= new Scanner(System.in);
        Estadio estadio = new Estadio();
        System.out.println("INGRESE EL NOMBRE DEL ESTADIO: ");
        estadio.setNombre_estadio(sc.nextLine());
        mostrarEquipos(equipos);
        System.out.println("SELECCIONE EL ID DEL EQUIPO AL QUE PERTENECE ESTE ESTADIO: ");
        int respuesta = sc.nextInt();
        for (Equipo e:equipos){
            if (respuesta==e.getId_equipo()){
                estadio.setEquipo(e);
                estadio.setUbicacion(e.getCiudad());
            }
        }
        estadios.add(estadio);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/act7_mrbd", "root", "ramiro12321");
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO  estadio (nombre_estadio, id_equipo, ubicacion) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, estadio.getNombre_estadio() );
            ps.setInt(2, estadio.getEquipo().getId_equipo());
            ps.setString(3, estadio.getUbicacion());

            ps.executeUpdate();
            con.close();
        } catch(Exception e){ System.out.println(e);}

    }
    private static void mostrarEstadios(HashSet<Estadio>estadios){
        for (Estadio e:estadios){
            System.out.println(e);
        }

    }
    private static void consultarDatosEstadios(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/act7_mrbd", "root", "ramiro12321");
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from estadio");
            while(rs.next())
                System.out.println(rs.getString(1)+"  "+rs.getInt(2)+"  "+rs.getString(3));
            con.close();
        } catch(Exception e){ System.out.println(e);}
    }
}
