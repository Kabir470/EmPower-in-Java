package Abstract;

public class CheckPassword {
    private static final String PASSWORD = "password";

    public boolean Check(String input) {
        return PASSWORD.equals(input);
    }
}
