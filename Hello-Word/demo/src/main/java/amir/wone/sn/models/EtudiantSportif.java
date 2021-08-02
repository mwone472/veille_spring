package amir.wone.sn.models;

public class EtudiantSportif extends Etudiant{

    private String sportName;

    public EtudiantSportif(int id, String name, String firstName, String email, String address, String dateOfBirth, String sportName) {
        super(id, name, firstName, email, address, dateOfBirth);
        this.sportName = sportName;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }
}
