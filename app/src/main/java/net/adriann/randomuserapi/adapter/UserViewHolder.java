package net.adriann.randomuserapi.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.adriann.randomuserapi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.user_image)
    public ImageView userImage;
    @BindView(R.id.username_text)
    public TextView username_text;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) { //TODO do something on click
         }

}
