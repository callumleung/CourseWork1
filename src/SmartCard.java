import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SmartCard {

    private final Date dateOfBirth;
    private final String smartCardNumber;
    private final Date dateOfIssue;
    private Date expiryDate;

    /** Public constructor. Sets the expiry date based on the type of student.
     * @param student Student to which the card is pertaining to.
     * @param smartCards List of existing smart cards to ensure smartCardNumber uniqueness.
     */
    public SmartCard(Student student,  ArrayList<SmartCard> smartCards){
        //default initialises to current time and date
        dateOfIssue = new Date();
        dateOfBirth = student.getBirthday();
        smartCardNumber = createSmartCardNumber(student, smartCards);
        setExpiryDate(student);
    }


    /** Sets the exipry date of the smartcard.
     *  For Undergraduates this is 4 years after issue.
     *  For Postgraduate Taught this is 2 years after issue.
     *  For Postgraduate Research this is 5 years after issue.
     * @param student The student the smartcard belongs to.
     */
    private void setExpiryDate(Student student){

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(this.dateOfIssue);


        //if instance of doesn't work, grab the first letter of the studentID instead
        if (student instanceof UndergraduateStudent){
            expiryDate.add(expiryDate.YEAR, 4);
        } else if (student instanceof PostgraduateTaughtStudent){
            expiryDate.add(expiryDate.YEAR, 2);
        } else if (student instanceof PostgraduateResearchStudent){
            expiryDate.add(expiryDate.YEAR, 5);
        }

        this.expiryDate = expiryDate.getTime();
    }

    /**
     * @return Returns the smartCardNumber.
     */
    public String getSmartCardNumber(){
        return smartCardNumber;
    }

    /**
     * @return Returns a copy of the expiry date of the card.
     */
    public Date getExpiryDate(){
        return new Date(this.expiryDate.getTime());
    }


    /** Fetches the initials of a student's name for use in the construction of the smartcard number.
     * @param student Initials will be fetched from this student's name.
     * @return A string containing only the student's initials.
     */
    private String getInitials(Student student){
        // need to get the students name, split based on " " and get the first letter of each word
        //grabs the initial of each name in the students name, no issue for Firstname Lastname but will return more than
        //two letters for Firstname Lastname Lastname etc
        String studentName = student.getName();
        String[] splitName = studentName.split(" ");

        StringBuilder sb = new StringBuilder();

        for (String s: splitName){
            sb.append(s.charAt(0));
        }

        return sb.toString();
    }

    /**Creates a smartcard number of the format INITIALS-YEAR-SERIAL, where serial is an incremental two digit number
     * to ensure uniqueness.
     * @param student The student that own's the smartcard.
     * @param allSmartCards List of all previously issued smartcards to ensure uniqueness.
     * @return a String in the format given above.
     */
    private String createSmartCardNumber(Student student, ArrayList<SmartCard> allSmartCards) {
        //need the current year and arbitrary serial number

        int year = Calendar.getInstance().get(Calendar.YEAR);
        String initials = this.getInitials(student);

        StringBuilder SCBuilder = new StringBuilder();

        SCBuilder.append(initials);
        SCBuilder.append("-");
        SCBuilder.append(String.valueOf(year));
        SCBuilder.append("-");

        String startOfNumber = SCBuilder.toString();

        int matchingSerial = 0;
        for (SmartCard s: allSmartCards){

            String scNum = s.getSmartCardNumber();
            String scNumNoSerial = scNum.substring(0, scNum.length()-1);
            if ( scNumNoSerial.equals(startOfNumber)){
                matchingSerial++;
            }

        }

        if (matchingSerial < 10){
            SCBuilder.append("0" + matchingSerial);
        } else {
            SCBuilder.append(matchingSerial);
        }

        return SCBuilder.toString();
    }















}
