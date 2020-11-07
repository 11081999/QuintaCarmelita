package QuintaCarmelita.com;

public class kid{
    public String name;
    public String description;

    public kid(){
        //public no-arg constructor needed
    }

    public kid(String name, String email){
        this.name= name;
        this.description= email;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setDescription(String email){
        this.description=email;
    }

    public String getDescription(){
        return description;
    }

}