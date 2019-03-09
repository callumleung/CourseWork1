import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SmartCard {

    private final Date dateOfBirth;
    private final String smartCardNumber;
    private final Date dateOfIssue;
    private Date expiryDate;

    public SmartCard(Student student, Date DoB, ArrayList<SmartCard> smartCards){
        //default initialises to current time and date
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        dateOfIssue = c.getTime();
        dateOfBirth = DoB;
        smartCardNumber = createSmartCardNumber(student, smartCards);
        setExpiryDate(student);
    }


    private void setExpiryDate(Student student){

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(this.dateOfIssue);

        if (student instanceof UndergraduateStudent){
            expiryDate.add(expiryDate.YEAR, 4);
        } else if (student instanceof PostgraduateTaughtStudent){
            expiryDate.add(expiryDate.YEAR, 2);
        } else if (student instanceof PostgraduateResearchStudent){
            expiryDate.add(expiryDate.YEAR, 5);
        }

        this.expiryDate = expiryDate.getTime();
    }

    public String getSmartCardNumber(){
        return smartCardNumber;
    }

    public Date getExpiryDate(){
        return this.expiryDate;
    }


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

    private String createSmartCardNumber(Student student, ArrayList<SmartCard> allSmartCards) {
        //need the current year and arbitrary serial number

        int year = Calendar.getInstance().get(Calendar.YEAR);
        String initials = this.getInitials(student);

        StringBuilder SCBuilder = new StringBuilder();

        SCBuilder.append(initials);
        SCBuilder.append("-");
        SCBuilder.append(String.valueOf(year));
        SCBuilder.append("-");

        //String provisionalSmartCardNumber = SCBuilder.toString();

        boolean isUniqueID = false;
        int i = 1;

        //get random two digit numbe
        //start at serial 00 check against existing students
        //check against preexisting id's

        ///TODO alternatively, count the number of existing serials that match the initials and year and then append that+1
        //O(n) instead of O(n^2)
        StringBuilder serialBuilder = new StringBuilder();
        while (!isUniqueID) {

            int serial = 1;

            if (serial < 10) {
                serialBuilder.append("0");
                serialBuilder.append(serial);
            } else {
                serialBuilder.append(serial);
            }

            SCBuilder.append(serialBuilder.toString());

            for (SmartCard sCard : allSmartCards) {
                if (sCard.getSmartCardNumber() == SCBuilder.toString()) {
                    //remove the last two chars in the serial and increment the serial
                    SCBuilder.delete(SCBuilder.length() -1, SCBuilder.length());
                    i++;
                    break;
                }

                //id is therefore unique and we can exit loop.
                isUniqueID = true;
            }
        }


        return SCBuilder.toString();
    }















}
