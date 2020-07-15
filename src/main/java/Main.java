import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;

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

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
