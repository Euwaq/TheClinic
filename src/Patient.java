import java.util.Date;
import java.util.HashMap;

public class Patient {

    private String name;
    private HashMap<Date,Specialist> meetings=new HashMap<>();

    public void addMeeting(Date date,Specialist specialist){
        meetings.put(date, specialist);
    }

    public void delMeeting(Date date){
        meetings.remove(date);
    }

    public HashMap getMeetings(){
        return meetings;
    }


    public Patient(String name){
        this.name=name;

    }
}
