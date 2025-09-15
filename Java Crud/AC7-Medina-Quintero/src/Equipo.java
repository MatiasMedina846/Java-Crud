public class Equipo {
    private int id_equipo;
    private String nombre_equipo;
    private String ciudad;


    public Equipo() {
    }



    public Equipo(String ciudad, int id_equipo, String nombre_equipo) {
        this.ciudad = ciudad;
        this.id_equipo = id_equipo;
        this.nombre_equipo = nombre_equipo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombre_equipo() {
        return nombre_equipo;
    }

    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }


    @Override
    public String toString() {
        return "Equipo{" +
                "ciudad='" + ciudad + '\'' +
                ", id_equipo=" + id_equipo +
                ", nombre_equipo='" + nombre_equipo + '\'' +
                '}';
    }
}
