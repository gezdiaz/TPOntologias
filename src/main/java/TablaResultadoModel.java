import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TablaResultadoModel extends AbstractTableModel {

    ArrayList<Alumno> resultados;
    private String[] columnas = {"Legajo", "Nombre y apellido", "Carrera", "Edad", "Ponderacion","Materias ultimo a\u00f1o","Total materias"};

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
        return 7;
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
            case 2://Nombre
                return a.getCarrera();
            case 3://Edad
                return a.getEdad();
            case 4://Ponderación
                return a.getPonderacion();
            case 5://Últimas materias
                return a.getUltimasMaterias();
            case 6://Total materias
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
