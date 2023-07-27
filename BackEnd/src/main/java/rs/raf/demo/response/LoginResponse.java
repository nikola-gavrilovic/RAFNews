package rs.raf.demo.response;

public class LoginResponse {

    private String jwt;
    private String imePrezime;
    private String role;

    public LoginResponse(){

    }

    public LoginResponse(String jwt, String imePrezime, String role) {
        this.jwt = jwt;
        this.imePrezime = imePrezime;
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
