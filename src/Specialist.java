import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Specialist {

    public Specialist(String name,int week){
        this.name=name;
        while (week>0){
            this.workingWeek[week%10-1]=true;
            week/=10;
        }
    }

    private String name;
    private boolean [] workingWeek= new boolean[5];
    private HashMap<Date,Patient>meetings=new HashMap<>();

    public boolean isBusy(Date date){
        return meetings.containsKey(date);
    }

    public void addMeeting(Date date,Patient patient){
        meetings.put(date, patient);
    }

    public boolean isDayWorking(int i){
        if (i>5) return false;
        return workingWeek[i-1];
    }

    public void delMeetings(){
        for (Map.Entry<Date,Patient> meeting:meetings.entrySet()){
            Date date = meeting.getKey();
            Patient patient=meeting.getValue();
            patient.delMeeting(date);
        }
    }

    public void showWeek(){
        for (int i = 0; i < workingWeek.length; i++) {
            System.out.println(i+1+". "+workingWeek[i]);
        }
    }

    @Override
    public String toString(){
        return name;
    }



}
