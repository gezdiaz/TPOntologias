import com.complexible.common.rdf.query.resultio.TextTableQueryResultWriter;
import com.complexible.stardog.api.*;
import com.stardog.stark.query.SelectQueryResult;
import com.stardog.stark.query.io.QueryResultWriter;
import com.stardog.stark.query.io.QueryResultWriters;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
//        createConnection();
        JFrame ventana = new JFrame();
        ventana.setContentPane(new MainPanel(ventana));
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
//        createConnection();

    }

    //Crea una conexion a la base de datos TPOntologias en el servidor localhost:5820
    public static void createConnection() {
        try (final Connection aConn = ConnectionConfiguration.to("TPOntologias").server("http://localhost:5820")
                .reasoning(true)
                .credentials("admin", "admin")
                .connect()) {

            System.out.println("Conexión: "+aConn.name());

            //Hacer una Query simple
            SelectQuery query = aConn.select("select ?nombre ?legajo ?a\n" +
                    "where {\n" +
                    "  ?a a TPADMR:Alumno.\n" +
                    "  ?a TPADMR:tieneNombre ?nombre.\n" +
                    "  ?a TPADMR:tieneLegajo ?legajo.\n" +
                    "  ?a a TPADMR:AlumnoBecado\n" +
                    "  }");
            SelectQueryResult selectQueryResult = query.execute();
            QueryResultWriters.write(selectQueryResult, System.out, TextTableQueryResultWriter.FORMAT);

            //Hacer una Query con 2 condiciones
            SelectQuery query_1 = aConn.select("select * {?a a TPADMR:Alumno. ?a TPADMR:tienePonderacion ?b}");
            SelectQueryResult selectQueryResult_1 = query_1.execute();
            QueryResultWriters.write(selectQueryResult_1, System.out, TextTableQueryResultWriter.FORMAT);

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
