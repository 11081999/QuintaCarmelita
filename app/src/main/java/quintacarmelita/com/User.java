package quintacarmelita.com;

public class User {
    public String name;
    public String email;

    private UserType userType;

    public User(){
        //public no-arg constructor needed
    }

    public User(String name, String email, UserType userType){
        this.name= name;
        this.email= email;
        this.userType = userType;
    }

    public String getUserType(){
        return this.userType.stringValue;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getEmail(){
        return email;
    }

}
