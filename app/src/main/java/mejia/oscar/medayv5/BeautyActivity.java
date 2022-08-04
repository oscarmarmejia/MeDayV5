package mejia.oscar.medayv5;

import static android.content.Intent.EXTRA_INDEX;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import mejia.oscar.medayv5.Model.Products;
import mejia.oscar.medayv5.Model.Products2;
import mejia.oscar.medayv5.Model.Users;
import mejia.oscar.medayv5.ViewHolder.ProductViewHolder;

public class BeautyActivity extends AppCompatActivity {
    private DatabaseReference ProductsRef, ClientRef, ReferenceUser, ClientImage, ClientLike, ClientRef0, ClientRef1,ClientRef2,ClientRef3, Client, test;
    private RecyclerView recyclerView;
    private ImageButton botonliked;
    private DatabaseReference mDatabaseReference, dealReference;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    int maxid2 = 0;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database;//prueba
    private DatabaseReference userRef;//prueba
    private static final String USER = "users";//prueba
    int maxid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);
        // Paper.init( this );
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        ReferenceUser = FirebaseDatabase.getInstance().getReference().child("Users");

        Intent intent3 = getIntent();

int hola = intent3.getIntExtra("key", 1);

        ProductsRef = FirebaseDatabase.getInstance().getReference().child( "Beauty" );




        database = FirebaseDatabase.getInstance();//prueba
        userRef = database.getReference(USER);//prueba


        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                    }
                });
                 Intent intent = new Intent(BeautyActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        } );


        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById( R.id.recycle_menu );
        recyclerView.setHasFixedSize( true );
        layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager );
        dealReference = FirebaseDatabase.getInstance().getReference().child("Beauty").child("like");

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        Users usuariosnet = new Users();
        String mailComparison = account.getEmail();
        Toast.makeText(this, mailComparison, Toast.LENGTH_SHORT).show();
         String maildata = account.getEmail();

test = FirebaseDatabase.getInstance().getReference().child("Test");
        Client = FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(hola));

        ClientRef = FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(hola)).child( "Beauty" );
        ClientRef0 = FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(hola)).child( "Beauty" ).child("0");
        ClientRef1 = FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(hola)).child( "Beauty" ).child("1");
        ClientRef2 = FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(hola)).child( "Beauty" ).child("2");
        ClientRef3 = FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(hola)).child( "Beauty" ).child("3");

        ClientRef0 = FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(hola)).child( "Beauty" ).child("0");
        ClientImage = FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(hola)).child( "Beauty" ).child("image");
        ClientLike = FirebaseDatabase.getInstance().getReference().child("User").child(String.valueOf(hola)).child( "Beauty" ).child("like");
/*
        ClientRef0.child("like").setValue("no");
        ClientRef0.child("image").setValue("https://firebasestorage.googleapis.com/v0/b/tamalapp42.appspot.com/o/Product%20Images%2Fimage%3A45127Jun%2022%2C%20202200%3A44%3A06%20AM.jpg?alt=media&token=f44d9a67-ec93-43d3-9874-ced4ca253edf");
        ClientRef1.child("like").setValue("no");
        ClientRef1.child("image").setValue("https://firebasestorage.googleapis.com/v0/b/tamalapp42.appspot.com/o/Product%20Images%2Fimage%3A45127Jun%2022%2C%20202200%3A44%3A06%20AM.jpg?alt=media&token=f44d9a67-ec93-43d3-9874-ced4ca253edf");
        ClientRef2.child("like").setValue("no");
        ClientRef2.child("image").setValue("https://firebasestorage.googleapis.com/v0/b/tamalapp42.appspot.com/o/Product%20Images%2Fimage%3A45127Jun%2022%2C%20202200%3A44%3A06%20AM.jpg?alt=media&token=f44d9a67-ec93-43d3-9874-ced4ca253edf");
        ClientRef3.child("like").setValue("no");
        ClientRef3.child("image").setValue("https://firebasestorage.googleapis.com/v0/b/tamalapp42.appspot.com/o/Product%20Images%2Fimage%3A45127Jun%2022%2C%20202200%3A44%3A06%20AM.jpg?alt=media&token=f44d9a67-ec93-43d3-9874-ced4ca253edf");
*/

    }





    @Override
    protected void onStart() {
        super.onStart();



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

        ReferenceUser.addListenerForSingleValueEvent(new ValueEventListener() {

            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(BeautyActivity.this);
            Users usuariosnet = new Users();
            String mailComparison = account.getEmail();

            final String maildata = account.getEmail();


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds :  snapshot.getChildren())
                {
                    if(ds.child("id").getValue().equals(maildata)){
                       // Toast.makeText(BeautyActivity.this, "Existe", Toast.LENGTH_SHORT).show();
                     //   ClientRef0.child("like").setValue(ds.child("like"));
                        Client.setValue(ds.getValue());
                        ClientRef0.child("like").setValue("no");
                        ClientRef0.child("image").setValue("https://firebasestorage.googleapis.com/v0/b/tamalapp42.appspot.com/o/Product%20Images%2Fimage%3A45127Jun%2022%2C%20202200%3A44%3A06%20AM.jpg?alt=media&token=f44d9a67-ec93-43d3-9874-ced4ca253edf");
test.setValue(ds.getValue());

                    }


                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });









        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery( ClientRef, Products.class )
                        .build();


        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>( options ) {
                    @Override
                    protected void onBindViewHolder(@NonNull final ProductViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Products model) {
                        holder.txtProductName.setText( model.getPname() );

                        holder.botonLike.setText(model.getLike());

                        String empexar = holder.botonLike.getText().toString();

                        switch (empexar){
                            case "yes":
                                holder.corazon1.setVisibility(View.VISIBLE);
                                holder.corazon2.setVisibility(View.INVISIBLE);
                                break;
                            case "no":
                                holder.corazon1.setVisibility(View.INVISIBLE);
                                holder.corazon2.setVisibility(View.VISIBLE);
                                break;

                            default:
                                Toast.makeText(BeautyActivity.this, "You did not save it", Toast.LENGTH_SHORT).show();

                                break;

                        }

                        holder. botonLike.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                String comparison = holder.botonLike.getText().toString();
                                //  Toast.makeText(Beauty.this, comparison, Toast.LENGTH_SHORT).show();

       /*
        if(comparison == "yes"){

         //   holder.botonLike.setText("no");
          //  ProductsRef.child(String.valueOf(position)).child("like").setValue("no");
            Toast.makeText(Beauty.this, "You dont like it", Toast.LENGTH_SHORT).show();
        }

        else if
        (comparison == "no"){

            //holder.botonLike.setText("yes");
            //ProductsRef.child(String.valueOf(position)).child("like").setValue("yes");
            Toast.makeText(Beauty.this, "You like it", Toast.LENGTH_SHORT).show();
        }

*/
                                //boolean testt = getItem(position).getLike();
                                //String test = String.valueOf(testt);





                                switch (comparison){
                                    case "yes":
                                       // ProductsRef.child(String.valueOf(position)).child("like").setValue("no");
                                        ClientRef.child(String.valueOf(position)).child("like").setValue("no");
                                        Toast.makeText(BeautyActivity.this, "You don't like it", Toast.LENGTH_SHORT).show();
                                        holder.botonLike.setText("no");
                                        break;
                                    case "no":
                                       // ProductsRef.child(String.valueOf(position)).child("like").setValue("yes");
                                       ClientRef.child(String.valueOf(position)).child("like").setValue("yes");
                                        Toast.makeText(BeautyActivity.this, "You like it", Toast.LENGTH_SHORT).show();
                                        holder.botonLike.setText("yes");
                                        break;

                                    default:
                                        //Toast.makeText(Beauty.this, "You did not save it", Toast.LENGTH_SHORT).show();

                                        break;

                                }


                            }
                        });
                        /*
holder.botonLike.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //String eres = ProductsRef.child(String.valueOf(position)).child("like").getDatabase().toString();

       // Toast.makeText(Beauty.this, "prueba"+ eres, Toast.LENGTH_SHORT).show();
       // ProductsRef.child(String.valueOf(position)).child("like").setValue(true);
       // Toast.makeText(Beauty.this, "", Toast.LENGTH_SHORT).show();
metodo1();


        }

    private void metodo1() {


        String eres = ProductsRef.child(String.valueOf(position)).child("like").toString();
        Toast.makeText(Beauty.this, "prueba111"+ eres, Toast.LENGTH_SHORT).show();



    }
});
*/
                        //      holder.txtProductDescription.setText( model.getDescription() );
                        //        holder.txtProductPrice.setText( "Precio = " + model.getPrice() + "$" );
                        Picasso.get().load( model.getImage() ).into( holder.imageView );


                        holder.imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {


                                Toast.makeText(BeautyActivity.this, "prueba link", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }



                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.deal_item_1_layout, parent, false );
                        ProductViewHolder holder = new ProductViewHolder( view );
                        return holder;
                    }
                };
        recyclerView.setAdapter( adapter );
        adapter.startListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //Paper.book().destroy();

        //Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //startActivity(intent);
        //finish();


    }

}
