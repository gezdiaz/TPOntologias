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

import org.apache.jena.base.Sys;
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

    public static List<Alumno> getListaAlumnosBecadosIngresantes(){
        try (final Connection aConn = ConnectionConfiguration.to("TPOntologias").server("http://localhost:5820")
                .reasoning(false)
                .credentials("admin", "admin")
                .connect()) {

            System.out.println("Conexión: " + aConn.name());

            StringBuffer qStrBf = new StringBuffer()
                    .append("PREFIX TPADMR: <http://www.semanticweb.org/gasto/ontologies/2020/4/TPADMR#>")
                    .append("select ?nom ?leg ?pond ?edad ?carr WHERE")
                    .append("{")
                    .append(" ?a a TPADMR:AlumnoBecado.")
                    .append(" ?a a TPADMR:AlumnoIngresante.")
                    .append(" ?a TPADMR:tieneNombre ?nom.")
                    .append(" ?a TPADMR:tieneLegajo ?leg.")
                    .append(" ?a TPADMR:tieneEdad ?edad.")
                    .append(" ?a TPADMR:tieneCarrera ?carr.")
                    .append(" ?a TPADMR:tienePonderacion ?pond.")
                    .append("}");

            List<Alumno> alumnos = new ArrayList<Alumno>();
            SelectQuery selectQuery = aConn.select(qStrBf.toString());
            System.out.println("Query: " + qStrBf.toString());
            System.out.println("Se ejecuta la query");
            SelectQueryResult selectQueryResult = selectQuery.execute();
            System.out.println("Se termino de ejecutar la query");
            Pattern pattern = Pattern.compile("^\"(.*?)\"");
            Pattern patternInt = Pattern.compile("^\"(.*?)[.\"]");
            Pattern patternCarrera = Pattern.compile("#(.*?)$");
            while (selectQueryResult.hasNext()) {
                System.out.println("Inicio del While");
                BindingSet bindingSet = selectQueryResult.next();
                Alumno alumno = new Alumno();
                alumno.setCondicion("Ingresante");
//                    alumno.setLegajo(Integer.parseInt(bindingSet.get("leg").toString().substring(1, bindingSet.get("leg").toString().lastIndexOf("\""))));
//                    alumno.setNombre(bindingSet.get("nom").toString().substring(1,bindingSet.get("nom").toString().lastIndexOf("\"")));
//                    alumno.setEdad(Integer.parseInt(bindingSet.get("edad").toString().substring(1,bindingSet.get("edad").toString().lastIndexOf("\""))));
//                    alumno.setTotalMaterias(Integer.parseInt(bindingSet.get("cmat").toString().substring(1,bindingSet.get("cmat").toString().lastIndexOf("\""))));
//                    alumno.setUltimasMaterias(alumno.getUltimasMaterias()+1);
//                    alumno.setPromedio(Float.parseFloat(bindingSet.get("prom").toString().substring(1,bindingSet.get("prom").toString().lastIndexOf("\""))));
//                    alumno.setPonderacion(Float.parseFloat(bindingSet.get("pond").toString().substring(1,bindingSet.get("pond").toString().lastIndexOf("\""))));
                Matcher m = patternCarrera.matcher(bindingSet.get("carr").toString());
                m.find();
                alumno.setCarrera(m.group(1));

                m = pattern.matcher(bindingSet.get("leg").toString());
                m.find();
                alumno.setLegajo(Integer.parseInt(m.group(1)));

                m = pattern.matcher(bindingSet.get("nom").toString());
                m.find();
                alumno.setNombre(m.group(1));

                m = patternInt.matcher(bindingSet.get("edad").toString());
                m.find();
                alumno.setEdad(Integer.parseInt(m.group(1)));

                m = pattern.matcher(bindingSet.get("pond").toString());
                m.find();
                alumno.setPonderacion(Float.parseFloat(m.group(1)));

//                alumno.setCarrera(pattern.matcher(bindingSet.get("carr").toString()).group(1));
                alumnos.add(alumno);
                System.out.println("Creado el alumno: " + alumno.toString());

            }
            return alumnos;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //Crea una conexion a la base de datos TPOntologias en el servidor localhost:5820
    public static List<Alumno> getListaAlumnosBecadosRegulares() {
        try (final Connection aConn = ConnectionConfiguration.to("TPOntologias").server("http://localhost:5820")
                .reasoning(false)
                .credentials("admin", "admin")
                .connect()) {

            System.out.println("Conexión: " + aConn.name());

            StringBuffer qStrBf = new StringBuffer()
                    .append("PREFIX TPADMR: <http://www.semanticweb.org/gasto/ontologies/2020/4/TPADMR#>")
                    .append("select ?nom ?leg ?pond ?edad ?carr ?cmat ?cmatant ?prom WHERE")
                    .append("{")
                    .append(" ?a a TPADMR:AlumnoBecado.")
                    .append(" ?a a TPADMR:AlumnoRegular.")
                    .append(" ?a TPADMR:tieneAprobadasAnioAnterior ?cmatant.")
                    .append(" ?a TPADMR:tienePromedioGeneral ?prom.")
                    .append(" ?a TPADMR:tieneNombre ?nom.")
                    .append(" ?a TPADMR:tieneLegajo ?leg.")
                    .append(" ?a TPADMR:tieneEdad ?edad.")
                    .append(" ?a TPADMR:tieneCarrera ?carr.")
                    .append(" ?a TPADMR:tieneCantidadMateriasAprobadas ?cmat.")
                    .append(" ?a TPADMR:tienePonderacion ?pond.")
                    .append("}");

            List<Alumno> alumnos = new ArrayList<Alumno>();
            SelectQuery selectQuery = aConn.select(qStrBf.toString());
            System.out.println("Query: " + qStrBf.toString());
            System.out.println("Se ejecuta la query");
            SelectQueryResult selectQueryResult = selectQuery.execute();
            System.out.println("Se termino de ejecutar la query");
            Pattern pattern = Pattern.compile("^\"(.*?)\"");
            Pattern patternInt = Pattern.compile("^\"(.*?)[.\"]");
            Pattern patternCarrera = Pattern.compile("#(.*?)$");
            while (selectQueryResult.hasNext()) {
                System.out.println("Inicio del While");
                BindingSet bindingSet = selectQueryResult.next();
                Alumno alumno = new Alumno();
                alumno.setCondicion("Regular");
//                    alumno.setLegajo(Integer.parseInt(bindingSet.get("leg").toString().substring(1, bindingSet.get("leg").toString().lastIndexOf("\""))));
//                    alumno.setNombre(bindingSet.get("nom").toString().substring(1,bindingSet.get("nom").toString().lastIndexOf("\"")));
//                    alumno.setEdad(Integer.parseInt(bindingSet.get("edad").toString().substring(1,bindingSet.get("edad").toString().lastIndexOf("\""))));
//                    alumno.setTotalMaterias(Integer.parseInt(bindingSet.get("cmat").toString().substring(1,bindingSet.get("cmat").toString().lastIndexOf("\""))));
//                    alumno.setUltimasMaterias(alumno.getUltimasMaterias()+1);
//                    alumno.setPromedio(Float.parseFloat(bindingSet.get("prom").toString().substring(1,bindingSet.get("prom").toString().lastIndexOf("\""))));
//                    alumno.setPonderacion(Float.parseFloat(bindingSet.get("pond").toString().substring(1,bindingSet.get("pond").toString().lastIndexOf("\""))));
                Matcher m = patternCarrera.matcher(bindingSet.get("carr").toString());
                m.find();
                alumno.setCarrera(m.group(1));
                m = pattern.matcher(bindingSet.get("leg").toString());
                m.find();
                alumno.setLegajo(Integer.parseInt(m.group(1)));
                m = pattern.matcher(bindingSet.get("nom").toString());
                m.find();
                alumno.setNombre(m.group(1));
                m = patternInt.matcher(bindingSet.get("edad").toString());
                m.find();
                alumno.setEdad(Integer.parseInt(m.group(1)));
                m = patternInt.matcher(bindingSet.get("cmat").toString());
                m.find();
                alumno.setTotalMaterias(Integer.parseInt(m.group(1)));
                m = patternInt.matcher(bindingSet.get("cmatant").toString());
                m.find();
                alumno.setUltimasMaterias(Integer.parseInt(m.group(1)));
                m = pattern.matcher(bindingSet.get("prom").toString());
                m.find();
                alumno.setPromedio(Float.parseFloat(m.group(1)));
                m = pattern.matcher(bindingSet.get("pond").toString());
                m.find();
                alumno.setPonderacion(Float.parseFloat(m.group(1)));
//                alumno.setCarrera(pattern.matcher(bindingSet.get("carr").toString()).group(1));
                alumnos.add(alumno);
                System.out.println("Creado el alumno: " + alumno.toString());

            }
            return alumnos;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
}
