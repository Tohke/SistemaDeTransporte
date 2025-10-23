package modelo;

import org.bson.types.ObjectId;
import java.util.Objects;

public class Operators {

    private ObjectId id;
    private String operatorName;
    private String login;
    private String password;


    public Operators(){}

    public Operators(String operatorName, String login, String password) {
        this.operatorName = operatorName;
        this.login = login;
        this.password = password;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
