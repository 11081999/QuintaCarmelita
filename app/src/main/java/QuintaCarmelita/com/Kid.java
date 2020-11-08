package QuintaCarmelita.com;

public class Kid {
    public String name;
    public int age;

    public Kid(){
        //public no-arg constructor needed
    }

    public Kid(String name, int age){
        this.name= name;
        this.age= age;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setAge(int age){
        this.age=age;
    }

    public int getAge(){
        return age;
    }

}