public class Jugador {
    private int id_jugador;
    private String nombre_jugador;
    private String apellido_jugador;
    private Equipo equipo;

    public Jugador() {
    }

    public Jugador(String nombre_jugador, int id_jugador, Equipo equipo, String apellido_jugador) {
        this.nombre_jugador = nombre_jugador;
        this.id_jugador = id_jugador;
        this.equipo = equipo;
        this.apellido_jugador = apellido_jugador;
    }

    public String getApellido_jugador() {
        return apellido_jugador;
    }

    public void setApellido_jugador(String apellido_jugador) {
        this.apellido_jugador = apellido_jugador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public String getNombre_jugador() {
        return nombre_jugador;
    }

    public void setNombre_jugador(String nombre_jugador) {
        this.nombre_jugador = nombre_jugador;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id: " + id_jugador +
                "nombre: " + nombre_jugador + '\'' +
                "apellido: " + apellido_jugador + '\'' +
                "equipo: " + equipo.getId_equipo() +
                '}';
    }

}
