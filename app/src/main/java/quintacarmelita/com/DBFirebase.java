package quintacarmelita.com;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DBFirebase {

    public static boolean isUserLoggedIn;

    private static FirebaseAuth auth;
    private static FirebaseUser currentUser;


    public DBFirebase(){
        if(this.auth == null)
          this.auth = FirebaseAuth.getInstance();
        isUserLoggedIn = auth.getCurrentUser() != null;
    }

    public Task<AuthResult> signInWithEmailAndPassword(String email, String password){
        return auth.signInWithEmailAndPassword(email, password);
    }

    public static void setCurrentUser(){
        if(auth != null)
          currentUser = auth.getCurrentUser();
    }

}
