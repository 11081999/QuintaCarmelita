package QuintaCarmelita.com;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class SignedInActivity extends AppCompatActivity {

    Button btn_bs;

    SearchView searchView;
    ListView listView;
    ArrayList list;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);

        ArrayList<String> Nombre=new ArrayList<>();
        Nombre.add("maria");
        Nombre.add("mariana");
        Nombre.add("mario");
        AutoCompleteTextView edit =findViewById(R.id.autoCompleteTextView3);
        ArrayAdapter<String> adapter1= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Nombre);
        edit.setAdapter(adapter1);

        btn_bs =findViewById(R.id.button);
        btn_bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignedInActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        //


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(SignedInActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                    listView.setVisibility(View.INVISIBLE);
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                listView.setVisibility(View.VISIBLE);
                return false;
            }
        });
    }

    public void onLogout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void createKid(View view) {
        Intent intent=new Intent(SignedInActivity.this, ProfileCreateActivity.class);
        startActivity(intent);
    }

    public void getUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to authenticate with your backend server, if you  have one. Use FirebaseUser.getToken() instead
            String uid = user.getUid();
        }

    }
}