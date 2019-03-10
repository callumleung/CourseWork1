public class Module {

    private final String name;
    private final String code;
    private final int credits;


    /**Constructor
     * @param code The course code, ie CSC8002.
     * @param name Name of the course.
     * @param credits
     */
    public Module(String code, String name,  int credits) {
        this.name = name;
        this.code = code;
        this.credits = credits;
    }

    /**
     * @return String with the name of the course
     */
    public String getName() {
        return name;
    }

    /**
     * @return String with the course code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return int equal to the value of course credits.
     */
    //Primitive no defensive copy needed.
    public int getCredits() {
        return credits;
    }

    /**
     * Two modules are only equal if their course code, name, and credit total are the same.
     * @param obj the object to be tested.
     * @return boolean, True if two modules are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Module &&
                ((Module) obj).getCode().equals(this.getCode()) &&
                ((Module) obj).getName().equals(this.getName()) &&
                ((Module) obj).getCredits() == this.getCredits()) {
            return true;

        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        int hash = 3;
        hash = 47*hash + this.getCode().hashCode();
        hash = 47*hash + this.getName().hashCode();
        hash = 47*hash + this.getCredits();

        return hash;
    }

}
