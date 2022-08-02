package mejia.oscar.medayv5.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import mejia.oscar.medayv5.Interface.ItemClickListner;
import mejia.oscar.medayv5.R;

public class DealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ImageView imageView;
    public ItemClickListner listner;
    public Button botonlike;

    public DealViewHolder(@NonNull View itemView) {
        super( itemView );
        imageView = itemView.findViewById( R.id.product_image);
        txtProductName =  itemView.findViewById( R.id.product_name);
botonlike = itemView.findViewById(R.id.boton_6);
     // botonlike = itemView.findViewById(R.id.like_button1);

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
