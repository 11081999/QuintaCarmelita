package QuintaCarmelita.com;

public class Kid {
    public String name;
    public String age;

    public Kid(){
        //public no-arg constructor needed
    }

    public Kid(String name, String age){
        this.name= name;
        this.age= age;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setAge(String age){
        this.age=age;
    }

    public String getAge(){
        return age;
    }

}