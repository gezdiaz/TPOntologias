import com.complexible.common.rdf.query.resultio.TextPathQueryResultWriter;
import com.complexible.common.rdf.query.resultio.TextTablePathQueryResultWriter;
import com.complexible.common.rdf.query.resultio.TextTableQueryResultWriter;
import com.complexible.stardog.api.*;
import com.complexible.stardog.jena.SDJenaFactory;
import com.stardog.stark.query.BindingSet;
import com.stardog.stark.query.SelectQueryResult;
import com.stardog.stark.query.io.QueryResultWriter;
import com.stardog.stark.query.io.QueryResultWriters;
import com.stardog.stark.query.io.json.SPARQLJSONSelectResultWriter;
import org.apache.jena.query.*;
import org.apache.jena.query.Query;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.core.Var;
import org.apache.jena.sparql.engine.binding.Binding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.xml.bind.annotation.XmlAttachmentRef;

public class Main {

    public static void main(String[] args) {
//        createConnection();
        JFrame ventana = new JFrame();
        ventana.setContentPane(new MainPanel(ventana));
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setVisible(true);
//        createConnection();

    }

    //Crea una conexion a la base de datos TPOntologias en el servidor localhost:5820
    public static List<Alumno> createConnection() {
        try (final Connection aConn = ConnectionConfiguration.to("TPOntologias").server("http://localhost:5820")
                .reasoning(true)
                .credentials("admin", "admin")
                .connect()) {

            System.out.println("Conexión: "+aConn.name());

            //Hacer una Query simple
            /*SelectQuery query = aConn.select("select * {?a a TPADMR:Alumno}");
            SelectQueryResult selectQueryResult = query.execute();
            QueryResultWriters.write(selectQueryResult, System.out, TextTableQueryResultWriter.FORMAT);*/

            //Hacer una Query con 2 condiciones
            /*SelectQuery query_1 = aConn.select("select ?b ?c {?a a TPADMR:Alumno. ?a TPADMR:tieneNombre ?b. ?a TPADMR:tienePonderacion ?c}");
            SelectQueryResult selectQueryResult_1 = query_1.execute();
            QueryResultWriters.write(selectQueryResult_1, System.out, TextTableQueryResultWriter.FORMAT);*/


            /*Dataset dataset = SDJenaFactory.createDataset(aConn);
            Model model = SDJenaFactory.createModel(aConn);

            model.begin();
            model.commit();*/

            StringBuffer qStrBf = new StringBuffer()
                    .append("PREFIX TPADMR: <http://www.semanticweb.org/gasto/ontologies/2020/4/TPADMR#>")
                    .append("select ?nom ?leg ?pond ?edad ?carr ?cmat ?mat ?prom WHERE")
                    .append("{")
                    .append(" ?a a TPADMR:Alumno.")
                    .append(" ?a TPADMR:aproboMateria ?mat.")
                    .append(" ?a TPADMR:tienePromedioGeneral ?prom.")
                    .append(" ?a TPADMR:tieneNombre ?nom.")
                    .append(" ?a TPADMR:tieneLegajo ?leg.")
                    .append(" ?a TPADMR:tieneEdad ?edad.")
                    .append(" ?a TPADMR:tieneCarrera ?carr.")
                    .append(" ?a TPADMR:tieneCantidadMateriasAprobadas ?cmat.")
                    .append(" ?a TPADMR:tienePonderacion ?pond.")
                    .append("}");

            /*Query query = QueryFactory.create(qStrBf.toString());
            QueryExecution execution = QueryExecutionFactory.create(query, dataset);
            ResultSet resultSet = execution.execSelect();
            while(resultSet.hasNext()){
                Binding binding = resultSet.nextBinding();
                Iterator<Var> iterator = binding.vars();
                while(iterator.hasNext()){
                    System.out.println(binding.get(iterator.next()));
                }
                System.out.println("Sale del while interno");
            }*/

            List<Alumno> alumnos = new ArrayList<Alumno>();
            SelectQuery selectQuery = aConn.select(qStrBf.toString());
            System.out.println("Query: "+qStrBf.toString());
            System.out.println("Se ejecuta la query");
            SelectQueryResult selectQueryResult = selectQuery.execute();
            System.out.println("Se termino de ejecutar la query");
            Pattern pattern = Pattern.compile("\"(.*?)\"");
            while(selectQueryResult.hasNext()){
                System.out.println("Inicio del While");
                BindingSet bindingSet = selectQueryResult.next();
                boolean bandera = false;
                if(!alumnos.isEmpty()){
                    for(Alumno a : alumnos){
                        Integer legajo = Integer.parseInt(bindingSet.get("leg").toString().substring(1, bindingSet.get("leg").toString().lastIndexOf("\"")));
                        if (a.getLegajo() == legajo){
                            bandera=true;
                            a.setUltimasMaterias(a.getUltimasMaterias()+1);
                            break;
                        }
                    }
                }
                if(!bandera){
                    Alumno alumno = new Alumno();
//                    alumno.setLegajo(Integer.parseInt(bindingSet.get("leg").toString().substring(1, bindingSet.get("leg").toString().lastIndexOf("\""))));
//                    alumno.setNombre(bindingSet.get("nom").toString().substring(1,bindingSet.get("nom").toString().lastIndexOf("\"")));
//                    alumno.setEdad(Integer.parseInt(bindingSet.get("edad").toString().substring(1,bindingSet.get("edad").toString().lastIndexOf("\""))));
//                    alumno.setTotalMaterias(Integer.parseInt(bindingSet.get("cmat").toString().substring(1,bindingSet.get("cmat").toString().lastIndexOf("\""))));
//                    alumno.setUltimasMaterias(alumno.getUltimasMaterias()+1);
//                    alumno.setPromedio(Float.parseFloat(bindingSet.get("prom").toString().substring(1,bindingSet.get("prom").toString().lastIndexOf("\""))));
//                    alumno.setPonderacion(Float.parseFloat(bindingSet.get("pond").toString().substring(1,bindingSet.get("pond").toString().lastIndexOf("\""))));
//                    alumno.setCarrera(bindingSet.get("carr").toString().substring(bindingSet.get("carr").toString().indexOf("#")+1));
                    alumno.setLegajo(Integer.parseInt(pattern.matcher(bindingSet.get("leg").toString()).group(1)));
                    alumno.setNombre(pattern.matcher(bindingSet.get("nom").toString()).group(1));
                    alumno.setEdad(Integer.parseInt(pattern.matcher(bindingSet.get("edad").toString()).group(1)));
                    alumno.setTotalMaterias(Integer.parseInt(pattern.matcher(bindingSet.get("cmat").toString()).group(1)));
                    alumno.setUltimasMaterias(alumno.getUltimasMaterias()+1);
                    alumno.setPromedio(Float.parseFloat(pattern.matcher(bindingSet.get("prom").toString()).group(1)));
                    alumno.setPonderacion(Float.parseFloat(pattern.matcher(bindingSet.get("pond").toString()).group(1)));
                    alumno.setCarrera(pattern.matcher(bindingSet.get("carr").toString()).group(1));
                    alumnos.add(alumno);
                    System.out.println("Creado el alumno: "+alumno.toString());
                }

            }
            return alumnos;

        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
}
