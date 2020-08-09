import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TablaResultadoModel extends AbstractTableModel {

    ArrayList<Alumno> resultados;
    private String[] columnas = {"Legajo", "Nombre y apellido", "Edad", "Carrera", "Promedio", "Ponderaci\u00f3n","Materias ultimo a\u00f1o","Total materias"};

    public TablaResultadoModel(ArrayList<Alumno> resultados){
        this.resultados = resultados;
    }

    public void setResultados(ArrayList<Alumno> resultados){
        this.resultados = resultados;
    }

    @Override
    public int getRowCount() {
        return resultados.size();
    }

    @Override
    public int getColumnCount() {
        //La cantidad de culumnas es fija, porque son los atributos de la clase.
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //TODO ver como llenar la tabla, ver que te devuelve el server
        Alumno a = this.resultados.get(rowIndex);
        switch (columnIndex){
            case 0://Legajo
                return a.getLegajo();
            case 1://Nombre
                return a.getNombre();
            case 2://Edad
                return a.getEdad();
            case 3://Carrera
                return a.getCarrera();
            case 4://Promedio
                return a.getPromedio();
            case 5://Ponderación
                return a.getPonderacion();
            case 6://Últimas materias
                return a.getUltimasMaterias();
            case 7://Total materias
                return a.getTotalMaterias();
            default:
                System.out.println("Esto no debería pasar");
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return this.columnas[column];
    }
}
