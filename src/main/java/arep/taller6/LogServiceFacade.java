/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arep.taller6;

/**
 *
 * @author Yhonatan
 */
import static spark.Spark.*;

public class LogServiceFacade {
    private static final String[] LOG_SERVICE_URL = {"http://logserv1:40001/logservice", "http://logserv2:50002/logservice", "http://logserv3:60003/logservice"};

    public static void main(String[] args) {
        port(get_current_port());
        staticFileLocation("/public");

        RemoteLogServiceInvoker remoteLogServerInvoke = new RemoteLogServiceInvoker(LOG_SERVICE_URL);

        get("/logservicefacade", (req, res) -> {
            res.type("application/json");
            return remoteLogServerInvoke.invoke(req.queryParams("msg"));
        });
    }

    private static int get_current_port() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
