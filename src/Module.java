public class Module {

    private String name;
    private String code;
    private int credits;




    public Module(String name, String code, int credits) {
        this.name = name;
        this.code = code;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public int getCredits() {
        return credits;
    }

    private void setCredits(int credits) {
        this.credits = credits;
    }

    //TODO override equals and hash methods
}
