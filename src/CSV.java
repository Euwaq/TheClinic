import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CSV {
    //TODO
//          1)reading


    public static BufferedReader read(String name){
        FileReader reader= null;
        try {
            reader = new FileReader(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufReader=new BufferedReader(reader);
        return bufReader;
    }





//  TODO    2)writing
    public static void writePatient(String s){

    }public static void writeSpecialist(String s){

    }
}
