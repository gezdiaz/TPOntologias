import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import javafx.scene.control.Tab;

public class TablaResultadoModel extends AbstractTableModel {

    ArrayList<Object> resultados;

    public TablaResultadoModel(ArrayList<Object> resultados){
        this.resultados = resultados;
    }

    public void setResultados(ArrayList<Object> resultados){
        this.resultados = resultados;
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //TODO ver como llenar la tabla, ver que te devuelve el server
        return null;
    }
}
