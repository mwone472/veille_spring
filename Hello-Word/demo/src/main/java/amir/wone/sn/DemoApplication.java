package amir.wone.sn;

import amir.wone.sn.db.DataBase;
import amir.wone.sn.models.Etudiant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RestController
public class DemoApplication {

	private static final String template = "Hello , %s!";
	private final AtomicLong counter = new AtomicLong();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "wordl") String name){
	    return String.format("<h1>Hello %s ! </h1>", name);
    }

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "Word") String name){
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/student")
	public ArrayList<Etudiant> student(@RequestParam(value = "sportif", defaultValue= "false") String sportif) throws SQLException {
		ArrayList<Etudiant> response;
		if (sportif.equals("true")){
			response = DataBase.getAtleticStudents();
		}else{
			response = DataBase.getStudents();
		}
		return response;
	}

}

