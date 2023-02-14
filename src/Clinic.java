import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Clinic {
    HashMap<String,Specialist> specialists;
    HashMap<String,Patient> patients;

    public Clinic(){
        specialists =new HashMap<>();
        patients =new HashMap<>();
    }

    public void cmd(){
        String s;
        Scanner sc = new Scanner(System.in);
        do {
            s= sc.nextLine();
            s.toLowerCase();
            switch (s){
                case "addsp":
                    System.out.println("Enter specialist's data in format Name,123 , please.");
                    String specialistStr= sc.nextLine();
                    if (addSpecialist(specialistStr)){
                        CSV.writeSpecialist(specialistStr);
                    }

                    break;
                case "addpat":
                    System.out.println("Enter patient's data, please.");
                    String patientStr= sc.nextLine();
                    if (addMeeting(patientStr))
                        CSV.writePatient(patientStr);
                    break;
                case "delsp":
                    System.out.println("Enter specialist's name.");
                    String sName=sc.nextLine();
                    specialists.get(sName).delMeetings();
                    specialists.remove(sName);
                    break;
                case "showsp"://TODO

                    break;
                case ""://TODO

                case "exit":
                    break;
                default:
                    System.out.println("It is not correct command.");
            }
        }while (!s.equals("exit"));
    }

    public void readSpecialists(){
        BufferedReader br = CSV.read("Specialists.csv");
        boolean stop=false;
        while (!stop){
            try{
                String s= br.readLine();
                if (s==null)
                    stop=true;
                else
                    addSpecialist(s);
            }catch (NoSuchElementException exception){
                stop=true;
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }

    public void readPatients(){
        BufferedReader br = CSV.read("Patients.csv");
        boolean stop=false;
        while (!stop) {
            try {
                addMeeting(br.readLine());
            } catch (NoSuchElementException exception) {
                stop = true;
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }

    boolean addSpecialist(String str){//TODO
        String[] strings=str.split(",");
        String name=strings[0];
        if (name.equals("")){
            System.out.println("You wrote empty name.");
            return false;
        }
        int week=Integer.parseInt(strings[1]);
        specialists.put(name,new Specialist(name,week));
        return true;
    }

    boolean addMeeting(String str){//TODO
        String[] strings=str.split(",");
        String pName=strings[0];
        String sName=strings[1];
        String strDate=strings[2];
        Date date=parseDate(strDate);
        GregorianCalendar calendar=new GregorianCalendar();
        calendar.setTime(date);
        if (calendar.MINUTE!=0){
            System.out.println("Minute is not correct.");
            return false;
        }
        int hour=calendar.HOUR_OF_DAY;
        if ((hour<9)|(16<hour)){
            System.out.println("You can't record to "+hour+" o'clock, because the clinic works from 9 to 17.");
            return false;
        }
        if (!specialists.containsKey(sName)){
            System.out.println("There is not such specialist. ("+sName+")");
            return false;
        }
        Specialist specialist=specialists.get(sName);
        if (!specialist.isDayWorking(calendar.DAY_OF_WEEK)){
            System.out.println(sName+" doesn't work at "+strDate);
            return false;
        }
        if (specialist.isBusy(date)){
            System.out.println(sName+" is busy at "+strDate);
            return false;
        }
        if (!patients.containsKey(pName)){
            patients.put(pName,new Patient(pName));
        }
        Patient patient=patients.get(pName);
        specialist.addMeeting(date,patient);
        patient.addMeeting(date,specialist);
        return true;
    }



    Date parseDate(String strDate){
        SimpleDateFormat formatter = new SimpleDateFormat("H:mm d.MM.yyyy");
        Date date=new Date(0);
        try {
            date = formatter.parse(strDate);
        }
        catch (ParseException e) {
            System.out.println("Date format is not correct.");
        }
        return date;
    }
}
