package QuintaCarmelita.com;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProfileDatosGenerales  extends AppCompatActivity {

    //Real time database variables
    private FirebaseDatabase mFirebaseDatabaseInstance;
    private DatabaseReference mFireBaseDatabase;

    //Kid Variablez
    private String inputKidNmae, displayKidTalla, displayeKidNacimineto, displayKidFechaDeIngreso, displayKidFechaDeLlegada, displayStatus;
    private double displayKidPeso;

    private ValueEventListener mListener;

    //Button buttonDelete;
    Button buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the view now
        setContentView(R.layout.activity_profile_datosgenerales);
        final AutoCompleteTextView tv=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView6);
        tv.setText(getIntent().getStringExtra("NOM"));


        //buttonDelete= findViewById(R.id.button5);
        buttonUpdate= findViewById(R.id.button6);

        mFirebaseDatabaseInstance= FirebaseDatabase.getInstance();
        // get reference to 'niños' node
        mFireBaseDatabase = mFirebaseDatabaseInstance.getReference("Niños");
        mListener= new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //showFireBaseKidData(dataSnapshot);
                if (getIntent().getStringExtra("Valor")!= null){
                    tv.setText(getIntent().getStringExtra("Valor"));
                }

                inputKidNmae = tv.getText().toString();

                Kid kidInfo = new Kid();
                //kidInfo.setName(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getName());

                kidInfo.setName(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getName());
                kidInfo.setAge(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getAge());
                kidInfo.setFechaDeNacimiento(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getFechaDeNacimiento());
                kidInfo.setFechaDeIngreso(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getFechaDeIngreso());
                kidInfo.setFechaDeLLegada(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getFechaDeLLegada());
                kidInfo.setStatus(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getStatus());
                kidInfo.setTalla(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getTalla());
                kidInfo.setPeso(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getPeso());

                //inputKidNmae= text.getText().toString();
                //Fill variables
                //inputKidNmae= kidInfo.getName();
                displayeKidNacimineto= kidInfo.getFechaDeNacimiento();
                displayKidFechaDeIngreso= kidInfo.getFechaDeIngreso();
                displayKidFechaDeLlegada= kidInfo.getFechaDeLLegada();
                displayStatus= kidInfo.getStatus();
                displayKidTalla= kidInfo.getTalla();
                displayKidPeso= kidInfo.getPeso();

                String ageToString= Integer.toString(kidInfo.getAge());
                String pesoToString= Double.toString(displayKidPeso);
                updateProfileTextViewes(kidInfo.getName(),  displayeKidNacimineto, pesoToString, displayKidTalla, displayKidFechaDeIngreso, displayKidFechaDeLlegada, displayStatus);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mFireBaseDatabase.addValueEventListener(mListener);

        /*
        buttonDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                eliminateKidProfileOnFirebaseDatabase(inputKidNmae);
            }
        });
         */

        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                updateKidProfileOnFirebaseDatabase(inputKidNmae);
            }
        });
        Button bot1=(Button) findViewById(R.id.RegDG);
        bot1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileDatosGenerales.this,ProfileMenuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void updateProfileTextViewes(String nameOfKid, String displayeKidNacimineto, String displayKidPeso, String displayKidTalla,
                                        String displayKidFechaDeIngreso, String displayKidFechaDeLlegada, String displayKidStatus) {

        TextView profileKidName = (TextView)findViewById(R.id.editTextTextPersonName4);
        profileKidName.setText(nameOfKid);

        TextView profileKidNacimineto = (TextView)findViewById(R.id.editTextTextPersonName5);
        profileKidNacimineto.setText(displayeKidNacimineto);

        TextView profileKidPeso = (TextView)findViewById(R.id.editTextNumberDecimal2);
        profileKidPeso.setText(displayKidPeso);

        TextView profileKidTalla = (TextView)findViewById(R.id.editTextNumberDecimal);
        profileKidTalla.setText(displayKidTalla);

        TextView profileKidFechaDeIngreso = (TextView)findViewById(R.id.editTextDate2);
        profileKidFechaDeIngreso.setText(displayKidFechaDeIngreso);

        TextView profileKidFechaDeLlegada = (TextView)findViewById(R.id.editTextDate);
        profileKidFechaDeLlegada.setText(displayKidFechaDeLlegada);

        TextView profileKidStatus = (TextView)findViewById(R.id.editTextTextPersonName);
        profileKidStatus.setText(displayKidStatus);

        //TextView profileKidAge = (TextView)findViewById(R.id.textViewAge);
        //profileKidAge.setText(ageOfKid);
    }


    public void updateKidProfileOnFirebaseDatabase(String key) {
        DatabaseReference drKid = FirebaseDatabase.getInstance().getReference().child("Niños").child(key);

        TextView profileKidName = (TextView)findViewById(R.id.editTextTextPersonName4);
        drKid.child("name").setValue(profileKidName.getText().toString());

        TextView profileKidNacimineto = (TextView)findViewById(R.id.editTextTextPersonName5);
        drKid.child("fechaDeNacimiento").setValue(profileKidNacimineto.getText().toString());

        TextView profileKidPeso = (TextView)findViewById(R.id.editTextNumberDecimal2);
        double stringToDouble = Double.parseDouble(profileKidPeso.getText().toString());
        drKid.child("peso").setValue(stringToDouble);

        TextView profileKidTalla = (TextView)findViewById(R.id.editTextNumberDecimal);
        drKid.child("talla").setValue(profileKidTalla.getText().toString());

        TextView profileKidFechaDeIngreso = (TextView)findViewById(R.id.editTextDate2);
        drKid.child("fechaDeIngreso").setValue(profileKidFechaDeIngreso.getText().toString());

        TextView profileKidFechaDeLlegada = (TextView)findViewById(R.id.editTextDate);
        drKid.child("fechaDeLLegada").setValue(profileKidFechaDeLlegada.getText().toString());

        TextView profileKidStatus = (TextView)findViewById(R.id.editTextTextPersonName);
        drKid.child("status").setValue(profileKidStatus.getText().toString());


    }

    public void eliminateKidProfileOnFirebaseDatabase(String key) {
        //mFireBaseDatabase.removeEventListener(mListener);
        //DatabaseReference drKid = FirebaseDatabase.getInstance().getReference().child("Niños").child(key);
        //drKid.removeValue();
        //Intent intent=new Intent(ProfileDatosGenerales.this, SignedInActivity.class);
        //startActivity(intent);
    }
}