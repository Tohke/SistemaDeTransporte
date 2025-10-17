package modelo;

public class User {
    //private String userLogin;
    private String userName;
    //private String userPassword;
    private String address;
    private String cnh;
    private String sector;

    public User(){}

    public User(String userName, String address, String cnh, String sector) {
        this.userName = userName;
        this.address = address;
        this.cnh = cnh;
        this.sector = sector;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
