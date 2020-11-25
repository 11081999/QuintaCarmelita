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

public class ProfilePerfilMedico  extends AppCompatActivity {

    //Real time database variables
    private FirebaseDatabase mFirebaseDatabaseInstance;
    private DatabaseReference mFireBaseDatabase;

    //Kid Variables
    private String inputKidNmae, displayKidDoctorEncargado, displayKidPsicologoEncargado, displayKidAlergias,
                    displayKidMedicamentos, displayKidReporteDoctor, displayKidReportePsicologo;

    private ValueEventListener mListener;

    //Button buttonDelete;
    Button buttonUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the view now
        EditText name=(EditText) findViewById(R.id.MedNom);
        EditText sur=(EditText) findViewById(R.id.MedSur);
        //String string1=name.toString();
        //String string2=sur.toString();
        //String full=string1+" "+string2; //Nombre y apellido juntos
        setContentView(R.layout.activity_profile_perfilmedico);
        final AutoCompleteTextView tv=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView5);
        tv.setText(getIntent().getStringExtra("NOM"));
        String a =getIntent().getStringExtra("NOM");
        final TextView tv1=(TextView)findViewById(R.id.textView3);
        tv1.setText(a);

        Button bot1=(Button) findViewById(R.id.regMed);
        bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfilePerfilMedico.this,ProfileMenuActivity.class);
                startActivity(intent);
            }
        });
        //buttonDelete= findViewById(R.id.button5);
        buttonUpdate= findViewById(R.id.saveMed);

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

                kidInfo.setDoctorEncargado(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getDoctorEncargado());
                kidInfo.setPsicologoEncargdo(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getPsicologoEncargdo());
                kidInfo.setAlergias(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getAlergias());
                kidInfo.setMedicamentos(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getMedicamentos());
                kidInfo.setReporteDoctores(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getReporteDoctores());
                kidInfo.setReportePsicologo(dataSnapshot.child(inputKidNmae).getValue(Kid.class).getReportePsicologo());

                //inputKidNmae= text.getText().toString();
                //Fill variables
                //inputKidNmae= kidInfo.getName();
                displayKidDoctorEncargado= kidInfo.getDoctorEncargado();
                displayKidPsicologoEncargado= kidInfo.getPsicologoEncargdo();
                displayKidAlergias= kidInfo.getAlergias();
                displayKidMedicamentos= kidInfo.getMedicamentos();
                displayKidReporteDoctor= kidInfo.getReporteDoctores();
                displayKidReportePsicologo= kidInfo.getReportePsicologo();

                //String ageToString= Integer.toString(kidInfo.getAge());
                //String pesoToString= Double.toString(displayKidPeso);
                updateProfileTextViewes(kidInfo.getName(),  displayKidDoctorEncargado, displayKidPsicologoEncargado, displayKidAlergias,
                                                            displayKidMedicamentos, displayKidReporteDoctor, displayKidReportePsicologo);
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

    public void updateProfileTextViewes(String nameOfKid,  String displayKidDoctorEncargado, String displayKidPsicologoEncargado, String displayKidAlergias,
                                        String displayKidMedicamentos, String displayKidReporteDoctor, String displayKidReportePsicologo) {

        //TextView profileKidName = (TextView)findViewById(R.id.editTextTextPersonName4);
        //profileKidName.setText(nameOfKid);

        TextView profileKidDoctorEncargado = (TextView)findViewById(R.id.MedNom);
        profileKidDoctorEncargado.setText(displayKidDoctorEncargado);

        TextView profileKidKidPsicologoEncargado = (TextView)findViewById(R.id.MedSur);
        profileKidKidPsicologoEncargado.setText(displayKidPsicologoEncargado);

        TextView profileKidKidAlergias = (TextView)findViewById(R.id.Med1);
        profileKidKidAlergias.setText(displayKidAlergias);

        TextView profileKidMedicamentos = (TextView)findViewById(R.id.Med2);
        profileKidMedicamentos.setText(displayKidMedicamentos);

        TextView profileKidFechaDeLlegada = (TextView)findViewById(R.id.Med3);
        profileKidFechaDeLlegada.setText(displayKidReporteDoctor);

        TextView profileKidReporteDoctor = (TextView)findViewById(R.id.Med4);
        profileKidReporteDoctor.setText(displayKidReportePsicologo);

        //TextView profileKidAge = (TextView)findViewById(R.id.textViewAge);
        //profileKidAge.setText(ageOfKid);
    }


    public void updateKidProfileOnFirebaseDatabase(String key) {
        DatabaseReference drKid = FirebaseDatabase.getInstance().getReference().child("Niños").child(key);

        TextView profileKidDoctorEncargado = (TextView)findViewById(R.id.MedNom);
        drKid.child("doctorEncargado").setValue(profileKidDoctorEncargado.getText().toString());

        TextView profileKidKidPsicologoEncargado = (TextView)findViewById(R.id.MedSur);
        drKid.child("psicologoEncargdo").setValue(profileKidKidPsicologoEncargado.getText().toString());

        TextView profileKidKidAlergias = (TextView)findViewById(R.id.Med1);
        drKid.child("alergias").setValue(profileKidKidAlergias.getText().toString());

        TextView profileKidMedicamentos = (TextView)findViewById(R.id.Med2);
        drKid.child("medicamentos").setValue(profileKidMedicamentos.getText().toString());

        TextView profileKidFechaDeLlegada = (TextView)findViewById(R.id.Med3);
        drKid.child("reporteDoctores").setValue(profileKidFechaDeLlegada.getText().toString());

        TextView profileKidReporteDoctor = (TextView)findViewById(R.id.Med4);
        drKid.child("reportePsicologo").setValue(profileKidReporteDoctor.getText().toString());


    }

    public void eliminateKidProfileOnFirebaseDatabase(String key) {
        //mFireBaseDatabase.removeEventListener(mListener);
        //DatabaseReference drKid = FirebaseDatabase.getInstance().getReference().child("Niños").child(key);
        //drKid.removeValue();
        //Intent intent=new Intent(ProfileDatosGenerales.this, SignedInActivity.class);
        //startActivity(intent);
    }
}