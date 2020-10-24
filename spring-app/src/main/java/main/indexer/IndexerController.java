package main.indexer;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.DatabaseController;

import java.io.*;
import java.sql.SQLException;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class IndexerController {
    MediaType JSON;
    Gson gson;

    IndexerController() {
        this.JSON = MediaType.get("application/json; charset=utf-8");
        this.gson = new Gson();
    }



    @GetMapping("/index")
    ResponseEntity<String> index() throws IOException, JSONException, SQLException {
        DatabaseController controller = new DatabaseController();
        if (controller.establishConnection()) {
            System.out.println("Database connected");
        }
        controller.prepareDb();
        controller.initDb();
        controller.index();
/*
        controller.closeConnection();
*/
        return new ResponseEntity<>("Indexing done", HttpStatus.OK);
    }
}