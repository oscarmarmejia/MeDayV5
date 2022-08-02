package mejia.oscar.medayv5.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import mejia.oscar.medayv5.R;

import mejia.oscar.medayv5.Interface.ItemClickListner;


public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ImageView imageView, corazon1, corazon2;
    public ItemClickListner listner;
    public Button botonLike;

    public ProductViewHolder(@NonNull View itemView) {
        super( itemView );
        imageView = itemView.findViewById( R.id.product_image);
        txtProductName =  itemView.findViewById( R.id.product_name);
       // txtProductDescription =  itemView.findViewById(R.id.product_description);
      //  txtProductPrice =  itemView.findViewById(R.id.product_price);
       botonLike = itemView.findViewById(R.id.boton_6);
       corazon1 = itemView.findViewById(R.id.corazon1);
        corazon2 = itemView.findViewById(R.id.corazon2);
    }


    public void setItemClickLister( ItemClickListner lister)
    {
        this.listner = listner;

    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false  );
    }
}
