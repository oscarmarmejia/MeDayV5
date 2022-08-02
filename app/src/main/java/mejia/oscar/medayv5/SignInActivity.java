package mejia.oscar.medayv5;

import static android.content.Intent.EXTRA_INDEX;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;

import mejia.oscar.medayv5.Model.Users;


public class SignInActivity extends AppCompatActivity {
//private FirebaseDatabase database;
private DatabaseReference userRef, ReferenceUser, ReferenceBeauty;
SignInButton entrada;
    int maxid = 0;
    Button salir;
//private static final String USERS ="USERS";
    GoogleSignInOptions gso;
   GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ReferenceUser = FirebaseDatabase.getInstance().getReference().child("Users");
        ReferenceBeauty = FirebaseDatabase.getInstance().getReference().child("Beauty");
      entrada = findViewById(R.id.button_sign_in);
       // database = FirebaseDatabase.getInstance();
       // userRef = database.getReference(USERS);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

salir = findViewById(R.id.button_out);

salir.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
        });
    }
});

        ReferenceUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxid = (int)dataSnapshot.getChildrenCount();

                    //Toast.makeText(SignInActivity.this, "cuenta " + maxid , Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        entrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });

        /*
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds  : snapshot.getChildren()){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
*/



    }


    private void SignIn() {
        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                task.getResult(ApiException.class);

                Activity();


            }catch (ApiException e){
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }

        }


    }

    private void Activity() {
        //finish();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        Users usuariosnet = new Users();
        String mailComparison = account.getEmail();

        final String maildata = account.getEmail();
        usuariosnet.setId(mailComparison);
        int a = maxid;
        int k = 0;
        int v = 0;
   //     ReferenceUser.child(String.valueOf(maxid + 1)).setValue(usuariosnet);
        ReferenceUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds :  snapshot.getChildren())
                {
                    if(ds.child("id").getValue().equals(maildata)){


ReferenceUser.child(String.valueOf(maxid+1)).child("nombre").setValue(maildata);
ReferenceUser.child(String.valueOf(maxid+1)).setValue(ds.getValue());



                    }


                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        ReferenceUser.child(String.valueOf(maxid + 1)).setValue(usuariosnet);
        
int d = maxid+1;




        Intent intent2 = new Intent(SignInActivity.this, BeautyActivity.class);
        intent2.putExtra("key", d);
        startActivity(intent2);
    }

    }