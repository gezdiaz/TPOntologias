import com.complexible.common.rdf.query.resultio.TextTableQueryResultWriter;
import com.complexible.stardog.api.*;
import com.stardog.stark.query.SelectQueryResult;
import com.stardog.stark.query.io.QueryResultWriters;

public class Main {

    public static void main(String[] args) {
        createConnection();
    }

    //Crea una conexion a la base de datos TPOntologias en el servidor localhost:5820
    public static void createConnection() {
        try (final Connection aConn = ConnectionConfiguration.to("TPOntologias").server("http://localhost:5820")
                .credentials("admin", "admin")
                .connect()) {

            System.out.println(aConn.name());

            //Hacer una Query
            SelectQuery query = aConn.select("select * {?a a TPADMR:Alumno}");
            SelectQueryResult selectQueryResult = query.execute();
            QueryResultWriters.write(selectQueryResult, System.out, TextTableQueryResultWriter.FORMAT);

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
