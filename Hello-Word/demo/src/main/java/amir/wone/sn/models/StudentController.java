package amir.wone.sn.models;

import amir.wone.sn.db.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final DataBase dataBase;

    @Autowired
    public StudentController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @GetMapping
    public ArrayList<Etudiant> student(@RequestParam(value = "sportif", defaultValue= "false") String sportif) throws SQLException {
        return sportif.equals("true") ? dataBase.getAtleticStudents() : dataBase.getStudents();
    }
}
