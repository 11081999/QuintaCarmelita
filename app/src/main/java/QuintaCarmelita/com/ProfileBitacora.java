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

public class ProfileBitacora  extends AppCompatActivity {

    //Real time database variables
    private FirebaseDatabase mFirebaseDatabaseInstance;
    private DatabaseReference mFireBaseDatabase;

    //Kid Variables
    public String inputKidNmae;
    public String displayConductasPositivas;
    public String displayConductasNegativas;
    public String displayIncidentes;
    public String displayAreaFisica;
    public String displayConsecuencias;
    public String displayComentarios;

    private ValueEventListener mListener;

    //Button buttonDelete;
    Button buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the view now
        setContentView(R.layout.activity_profile_bitacora);
        AutoCompleteTextView tv=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView7);
        tv.setText(getIntent().getStringExtra("NOM"));

        //buttonDelete= findViewById(R.id.button5);
        buttonUpdate= findViewById(R.id.saveMed);

        mFirebaseDatabaseInstance= FirebaseDatabase.getInstance();
        // get reference to 'niños' node
        mFireBaseDatabase = mFirebaseDatabaseInstance.getReference("Niños");
        mListener= new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //showFireBaseKidData(dataSnapshot);
                if (getIntent().getStringExtra("Valor") != null) {
                    //text.setText(getIntent().getStringExtra("Valor"));
                }

                inputKidNmae = "rob";

                Kid kidInfo = new Kid();
                kidInfo.setName(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getName());
                kidInfo.setConductasPositivas(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getConductasPositivas());
                kidInfo.setConductasNegativas(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getConductasNegativas());
                kidInfo.setIncidentes(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getIncidentes());
                kidInfo.setAreaFisica(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getAreaFisica());
                kidInfo.setConsecuencias(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getConsecuencias());
                kidInfo.setComentarios(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getComentarios());

                //inputKidNmae= text.getText().toString();
                //Fill variables
                //inputKidNmae= kidInfo.getName();
                displayConductasPositivas= kidInfo.getConductasPositivas();
                displayConductasNegativas= kidInfo.getConductasNegativas();
                displayIncidentes= kidInfo.getIncidentes();
                displayAreaFisica= kidInfo.getAreaFisica();
                displayConsecuencias= kidInfo.getConsecuencias();
                displayComentarios= kidInfo.getComentarios();

                //String ageToString= Integer.toString(kidInfo.getAge());
                //String pesoToString= Double.toString(displayKidPeso);
                updateProfileTextViewes(kidInfo.getName(),  displayConductasPositivas, displayConductasNegativas, displayIncidentes,
                        displayAreaFisica, displayConsecuencias, displayComentarios);
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
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void updateProfileTextViewes(String nameOfKid,  String displayConductasPositivas, String displayConductasNegativas, String displayIncidentes,
                                        String displayAreaFisica, String displayConsecuencias, String displayComentarios) {

        //TextView profileKidName = (TextView)findViewById(R.id.editTextTextPersonName4);
        //profileKidName.setText(nameOfKid);

        TextView profileKidConductasPositivas = (TextView)findViewById(R.id.Bit1);
        profileKidConductasPositivas.setText(displayConductasPositivas);

        TextView profileKidKidConductasNegativas = (TextView)findViewById(R.id.Bit2);
        profileKidKidConductasNegativas.setText(displayConductasNegativas);

        TextView profileKidIncidentes = (TextView)findViewById(R.id.Bit3);
        profileKidIncidentes.setText(displayIncidentes);

        TextView profileKidAreaFisica = (TextView)findViewById(R.id.Bit4);
        profileKidAreaFisica.setText(displayAreaFisica);

        TextView profileKidConsecuencias = (TextView)findViewById(R.id.Bit5);
        profileKidConsecuencias.setText(displayConsecuencias);

        TextView profileKidComentarios = (TextView)findViewById(R.id.Bit6);
        profileKidComentarios.setText(displayComentarios);
    }


    public void updateKidProfileOnFirebaseDatabase(String key) {
        DatabaseReference drKid = FirebaseDatabase.getInstance().getReference().child("Niños").child(key);
/*
        TextView profileKidDoctorEncargado = (TextView)findViewById(R.id.MedNom);
        drKid.child("doctorEncargado").setValue(profileKidDoctorEncargado.getText().toString());

        TextView profileKidKidPsicologoEncargado = (TextView)findViewById(R.id.MedSur);
        drKid.child("psicologoEncargdo").setValue(profileKidKidPsicologoEncargado.getText().toString());

        TextView profileKidKidAlergias = (TextView)findViewById(R.id.Med1);
        drKid.child("alergias").setValue(profileKidKidAlergias);

        TextView profileKidMedicamentos = (TextView)findViewById(R.id.Med2);
        drKid.child("medicamentos").setValue(profileKidMedicamentos.getText().toString());

        TextView profileKidFechaDeLlegada = (TextView)findViewById(R.id.Med3);
        drKid.child("reporteDoctores").setValue(profileKidFechaDeLlegada.getText().toString());

        TextView profileKidReporteDoctor = (TextView)findViewById(R.id.Med4);
        drKid.child("reportePsicologo").setValue(profileKidReporteDoctor.getText().toString());
*/

    }

    public void eliminateKidProfileOnFirebaseDatabase(String key) {
        //mFireBaseDatabase.removeEventListener(mListener);
        //DatabaseReference drKid = FirebaseDatabase.getInstance().getReference().child("Niños").child(key);
        //drKid.removeValue();
        //Intent intent=new Intent(ProfileDatosGenerales.this, SignedInActivity.class);
        //startActivity(intent);
    }
}
