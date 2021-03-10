package quintacarmelita.com;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import androidx.annotation.NonNull;

public class DBFirebase {

    public static boolean isUserLoggedIn;
    public static User user;

    private static FirebaseAuth auth;
    private static DatabaseReference db;
    private static FirebaseUser currentUser;


    public DBFirebase(){
        if(this.auth == null)
            this.auth = FirebaseAuth.getInstance();
        isUserLoggedIn = auth.getCurrentUser() != null;
        if(this.db == null)
            this.db = FirebaseDatabase.getInstance().getReference();
    }

    public Task<AuthResult> createUserWithEmailAndPassword(String email, String pass){
        return auth.createUserWithEmailAndPassword(email, pass);
    }

    public Task<AuthResult> signInWithEmailAndPassword(String email, String password){
        return auth.signInWithEmailAndPassword(email, password);
    }

    public static void setCurrentUser(){
        if(auth != null) {
            currentUser = auth.getCurrentUser();
            String userId = currentUser.getUid();
            final String email = currentUser.getEmail();
            db.child("users").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()){
                        HashMap usuario = (HashMap) task.getResult().getValue();
                        UserType userType = UserType.selectCorrectUserType(usuario.get("userType").toString());
                        String username = usuario.get("name").toString();
                        user = new User(username, email, userType);
                        Log.d("Tipo del usuario", user.getUserType());
                        Log.d("firebase", String.valueOf(usuario));
                    } else {
                        Log.e("Inicio sesion", "Error login", task.getException());
                    }
                }
            });
        } else {
            user = null;
        }
    }

    public static boolean insertChild(){
        return true;
    }

}
