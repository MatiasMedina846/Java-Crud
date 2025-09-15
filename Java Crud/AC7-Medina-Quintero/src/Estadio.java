public class Estadio {
    private String nombre_estadio;
    private String ubicacion;
    private Equipo equipo;

    public Estadio() {
    }

    public Estadio(String ubicacion, String nombre_estadio, Equipo equipo) {
        this.ubicacion = ubicacion;
        this.nombre_estadio = nombre_estadio;
        this.equipo = equipo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getNombre_estadio() {
        return nombre_estadio;
    }

    public void setNombre_estadio(String nombre_estadio) {
        this.nombre_estadio = nombre_estadio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Estadio{" +
                "equipo=" + equipo +
                ", nombre_estadio='" + nombre_estadio + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                '}';
    }
}
