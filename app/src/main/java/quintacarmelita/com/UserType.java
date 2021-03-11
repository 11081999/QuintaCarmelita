package quintacarmelita.com;

public enum UserType {
    ADMINISTRATIVO("administrativo"),
    YAYA("yaya"),
    DIRECTOR("director");

    final String stringValue;

    UserType(String tipo){
        this.stringValue = tipo;
    }

    public static UserType selectCorrectUserType(String userTypeString){
        for(UserType u: UserType.values()){
            if(u.stringValue.equals(userTypeString))
                return u;
        }
        return YAYA;
    }
}
