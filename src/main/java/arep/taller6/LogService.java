/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arep.taller6;

/**
 *
 * @author Yhonatan
 */

import com.mongodb.client.MongoDatabase;
import arep.taller6.repositorio.Taller6;
import arep.taller6.repositorio.MongoUtil;

import java.util.Date;

import static spark.Spark.*;

public class LogService {
    public static void main( String[] args )
    {
        port(getPort());
        MongoDatabase database = MongoUtil.getDB();
        Taller6 logDAO = new Taller6(database);

        get("/logservice", (req, res) -> {
            Date savingTime = new Date();
            res.type("application/json");
            logDAO.addLog(savingTime, req.queryParams("msg"));
            return logDAO.listLogs();
        });


    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
