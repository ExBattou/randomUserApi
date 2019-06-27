package net.adriann.randomuserapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import net.adriann.randomuserapi.R;
import net.adriann.randomuserapi.model.Response;
import net.adriann.randomuserapi.model.Result;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    Response response;
    Context context;

    public UserAdapter(Response response, Context context) {
        this.response = response;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_user,parent,false);

        return new UserViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Result res = response.getResults().get(position);
        ImageView view = holder.userImage;

        getImageFromUrl(res.getPicture().getThumbnail(),view);
    }

    @Override
    public int getItemCount() {
        return this.response.getResults().size();
    }

    private void getImageFromUrl(String Url, ImageView view) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.color.colorPrimaryDark)
                .centerCrop()
                .error(android.R.color.darker_gray);

        Glide.with(context)
                .load(Url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(view);
    }
}
