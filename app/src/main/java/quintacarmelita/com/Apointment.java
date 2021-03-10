package quintacarmelita.com;



import java.time.*;


public class Apointment {
    public int kidID;
    public int yayaID;
    public String type;
    public String description;
    public LocalDate date;
    public LocalTime time;
    //opcional??
    public int doctorID;

    //constructores
    public Apointment(){
        //no args constructor
    }

    public Apointment(int kidID, int yayaID, String type, String description, LocalDate date, LocalTime time, int doctorID){
        this.kidID = kidID;
        this.yayaID = yayaID;
        this.type = type;
        this.description = description;
        this.date = date;
        this.time = time;
        this.doctorID = doctorID;
    }

    //getters y setters
    public void setKidID(int kidID){ this.kidID = kidID;}
    public int getKidID(){return kidID;}

    public void setYayaID(int yayaID){ this.yayaID = yayaID;}
    public int getYayaID(){return yayaID;}

    public void setType(String type){ this.type = type;}
    public String getType(){return type;}

    public void setDescription(String description){ this.description = description;}
    public String getDescription(){return description;}

    public void setDate(LocalDate date){this.date = date;}
    public LocalDate getDate(){return date;}

    public void setTime(LocalTime time){this.time = time;}
    public LocalTime getTime(){return time;}

    public void setDoctorID(int doctorID){ this.doctorID = doctorID;}
    public int getDoctorID(){return doctorID;}

}
