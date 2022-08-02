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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import mejia.oscar.medayv5.Model.Products;
import mejia.oscar.medayv5.Model.Users;
import mejia.oscar.medayv5.ViewHolder.ProductViewHolder;

public class BeautyActivity extends AppCompatActivity {
    private DatabaseReference ProductsRef, ClientRef, ReferenceUser;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);
        // Paper.init( this );
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);



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






        ClientRef = FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(hola)).child( "Beauty" );


    }





    @Override
    protected void onStart() {
        super.onStart();





        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery( ProductsRef, Products.class )
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
