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
private DatabaseReference userRef, ReferenceUser, ReferenceBeauty, ReferenceTest;
SignInButton entrada;
    int maxid = 0;
    int d=0;
    Button salir;
//private static final String USERS ="USERS";
    GoogleSignInOptions gso;
   GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ReferenceUser = FirebaseDatabase.getInstance().getReference().child("Users");
        ReferenceTest = FirebaseDatabase.getInstance().getReference().child("test");
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
                //ReferenceTest.child("test").setValue("test");
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

                //    Toast.makeText(SignInActivity.this, "cuenta " + maxid , Toast.LENGTH_SHORT).show();

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

            ReferenceUser.child(String.valueOf(maxid + 1)).setValue(usuariosnet);
            //   ReferenceUser.child(String.valueOf(maxid + 1)).setValue(usuariosnet);
            ReferenceUser.child(String.valueOf(maxid + 1)).child("Beauty").child("0").child("like").setValue("no");
            ;
            ReferenceUser.child(String.valueOf(maxid + 1)).child("Beauty").child("0").child("image").setValue("https://firebasestorage.googleapis.com/v0/b/tamalapp42.appspot.com/o/Product%20Images%2Fimage%3A45127Jun%2022%2C%20202200%3A44%3A06%20AM.jpg?alt=media&token=f44d9a67-ec93-43d3-9874-ced4ca253edf");

            ReferenceUser.child(String.valueOf(maxid + 1)).child("Beauty").child("1").child("like").setValue("no");
            ;
            ReferenceUser.child(String.valueOf(maxid + 1)).child("Beauty").child("1").child("image").setValue("https://firebasestorage.googleapis.com/v0/b/tamalapp42.appspot.com/o/Product%20Images%2Fimage%3A45127Jun%2022%2C%20202200%3A44%3A06%20AM.jpg?alt=media&token=f44d9a67-ec93-43d3-9874-ced4ca253edf");

            ReferenceUser.child(String.valueOf(maxid + 1)).child("Beauty").child("2").child("like").setValue("no");
            ;
            ReferenceUser.child(String.valueOf(maxid + 1)).child("Beauty").child("2").child("image").setValue("https://firebasestorage.googleapis.com/v0/b/tamalapp42.appspot.com/o/Product%20Images%2Fimage%3A45127Jun%2022%2C%20202200%3A44%3A06%20AM.jpg?alt=media&token=f44d9a67-ec93-43d3-9874-ced4ca253edf");

            ReferenceUser.child(String.valueOf(maxid + 1)).child("Beauty").child("3").child("like").setValue("no");
            ;
            ReferenceUser.child(String.valueOf(maxid + 1)).child("Beauty").child("3").child("image").setValue("https://firebasestorage.googleapis.com/v0/b/tamalapp42.appspot.com/o/Product%20Images%2Fimage%3A45127Jun%2022%2C%20202200%3A44%3A06%20AM.jpg?alt=media&token=f44d9a67-ec93-43d3-9874-ced4ca253edf");

            ReferenceUser.child(String.valueOf(maxid + 1)).child("nombre").setValue("hola");
            //     ReferenceUser.child(String.valueOf(maxid + 1)).setValue(usuariosnet);

            d = maxid + 1;



            ReferenceUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        if (ds.child("nombre").getValue().equals(maildata)) {

                            ReferenceUser.child(String.valueOf(maxid + 1)).setValue(ds.getValue());
                            // d = maxid+2;
//ReferenceUser.child(String.valueOf(maxid+1)).child("nombre").setValue(maildata);
//ReferenceUser.child(String.valueOf(maxid+1)).setValue(ds.getValue());


                        }


                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


//ReferenceUser.child("hola").setValue("hola");

            //Toast.makeText(this, "prueba", Toast.LENGTH_SHORT).show();

            ReferenceUser.child(String.valueOf(maxid + 1)).child("nombre").setValue(maildata);




        Intent intent2 = new Intent(SignInActivity.this, BeautyActivity.class);
        intent2.putExtra("key", d);
        startActivity(intent2);
    }

    }