public class Alumno {

    private int legajo, edad, totalMaterias, ultimasMaterias;
    private String nombre, carrera;
    private double ponderacion, promedio;

    public Alumno() {
        ultimasMaterias = 0;
    }

    public Alumno(int legajo, int edad, String nombre, String carrera, double promedio, double ponderacion, int ultimasMaterias, int totalMaterias) {
        this.legajo = legajo;
        this.edad = edad;
        this.nombre = nombre;
        this.carrera = carrera;
        this.promedio = promedio;
        this.ponderacion = ponderacion;
        this.totalMaterias = totalMaterias;
        this.ultimasMaterias = ultimasMaterias;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTotalMaterias() {
        return totalMaterias;
    }

    public void setTotalMaterias(int totalMaterias) {
        this.totalMaterias = totalMaterias;
    }

    public int getUltimasMaterias() {
        return ultimasMaterias;
    }

    public void setUltimasMaterias(int ultimasMaterias) {
        this.ultimasMaterias = ultimasMaterias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(double ponderacion) {
        this.ponderacion = ponderacion;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "legajo=" + legajo +
                ", edad=" + edad +
                ", totalMaterias=" + totalMaterias +
                ", ultimasMaterias=" + ultimasMaterias +
                ", nombre='" + nombre + '\'' +
                ", carrera='" + carrera + '\'' +
                ", ponderacion=" + ponderacion +
                ", promedio=" + promedio +
                '}';
    }
}
