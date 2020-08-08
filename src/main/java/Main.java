import com.complexible.common.rdf.query.resultio.TextPathQueryResultWriter;
import com.complexible.common.rdf.query.resultio.TextTablePathQueryResultWriter;
import com.complexible.common.rdf.query.resultio.TextTableQueryResultWriter;
import com.complexible.stardog.api.*;
import com.complexible.stardog.jena.SDJenaFactory;
import com.stardog.stark.query.SelectQueryResult;
import com.stardog.stark.query.io.QueryResultWriter;
import com.stardog.stark.query.io.QueryResultWriters;
import com.stardog.stark.query.io.json.SPARQLJSONSelectResultWriter;
import org.apache.jena.query.*;
import org.apache.jena.query.Query;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.core.Var;
import org.apache.jena.sparql.engine.binding.Binding;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        createConnection();
    }

    //Crea una conexion a la base de datos TPOntologias en el servidor localhost:5820
    public static void createConnection() {
        try (final Connection aConn = ConnectionConfiguration.to("TPOntologias").server("http://localhost:5820")
                .reasoning(true)
                .credentials("admin", "admin")
                .connect()) {

            System.out.println(aConn.name());

            //Hacer una Query simple
            /*SelectQuery query = aConn.select("select * {?a a TPADMR:Alumno}");
            SelectQueryResult selectQueryResult = query.execute();
            QueryResultWriters.write(selectQueryResult, System.out, TextTableQueryResultWriter.FORMAT);*/

            //Hacer una Query con 2 condiciones
            /*SelectQuery query_1 = aConn.select("select ?b ?c {?a a TPADMR:Alumno. ?a TPADMR:tieneNombre ?b. ?a TPADMR:tienePonderacion ?c}");
            SelectQueryResult selectQueryResult_1 = query_1.execute();
            QueryResultWriters.write(selectQueryResult_1, System.out, TextTableQueryResultWriter.FORMAT);*/

            //Dataset dataset = SDJenaFactory.createDataset(aConn);
            Model model = SDJenaFactory.createModel(aConn);

            model.begin();
            model.commit();

            StringBuffer qStrBf = new StringBuffer()
                    .append("PREFIX TPADMR: <http://www.semanticweb.org/gasto/ontologies/2020/4/TPADMR#>")
                    .append("select ?nom ?leg WHERE")
                    .append("{")
                    .append(" ?a a TPADMR:Alumno.")
                    .append(" ?a TPADMR:tieneNombre ?nom.")
                    .append(" ?a TPADMR:tieneLegajo ?leg.")
                    .append("}");

            Query query = QueryFactory.create(qStrBf.toString());
            QueryExecution execution = QueryExecutionFactory.create(query, model);
            ResultSet resultSet = execution.execSelect();
            while(resultSet.hasNext()){
                Binding binding = resultSet.nextBinding();
                Iterator<Var> iterator = binding.vars();
                while(iterator.hasNext()){
                    System.out.println(binding.get(iterator.next()));
                }
                System.out.println("Sale del while interno");
            }


        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
