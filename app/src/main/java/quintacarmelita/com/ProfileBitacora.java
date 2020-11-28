package quintacarmelita.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;

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
        final AutoCompleteTextView tv=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView7);
        tv.setText(getIntent().getStringExtra("NOM"));
        String a =getIntent().getStringExtra("NOM");
        final TextView tv1=(TextView)findViewById(R.id.textView2);
        tv1.setText(a);

        Button bot1=(Button) findViewById(R.id.RegBit);
        bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileBitacora.this,ProfileMenuActivity.class);
                startActivity(intent);
            }
        });
        //buttonDelete= findViewById(R.id.button5);
        buttonUpdate= findViewById(R.id.button4);

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

        TextView profileKidIncidentes = (TextView)findViewById(R.id.Bitacora3);
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

        TextView profileKidConductasPositivas = (TextView)findViewById(R.id.Bit1);
        drKid.child("conductasPositivas").setValue(profileKidConductasPositivas.getText().toString());

        TextView profileKidKidConductasNegativas = (TextView)findViewById(R.id.Bit2);
        drKid.child("conductasNegativas").setValue(profileKidKidConductasNegativas.getText().toString());

        TextView profileKidInc = (TextView)findViewById(R.id.Bitacora3);
        drKid.child("incidentes").setValue(profileKidInc.getText().toString());

        TextView profileKidAreaFisica = (TextView)findViewById(R.id.Bit4);
        drKid.child("areaFisica").setValue(profileKidAreaFisica.getText().toString());

        TextView profileKidConsecuencias = (TextView)findViewById(R.id.Bit5);
        drKid.child("consecuencias").setValue(profileKidConsecuencias.getText().toString());

        TextView profileKidComentarios = (TextView)findViewById(R.id.Bit6);
        drKid.child("comentarios").setValue(profileKidComentarios.getText().toString());


    }

    public void eliminateKidProfileOnFirebaseDatabase(String key) {
        //mFireBaseDatabase.removeEventListener(mListener);
        //DatabaseReference drKid = FirebaseDatabase.getInstance().getReference().child("Niños").child(key);
        //drKid.removeValue();
        //Intent intent=new Intent(ProfileDatosGenerales.this, SignedInActivity.class);
        //startActivity(intent);
    }
}
