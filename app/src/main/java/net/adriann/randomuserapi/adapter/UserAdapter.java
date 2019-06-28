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
import com.squareup.picasso.Picasso;

import net.adriann.randomuserapi.R;
import net.adriann.randomuserapi.model.Response;
import net.adriann.randomuserapi.model.Result;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    Response response;
    Context context;
    Callback callback;

    public UserAdapter(Response response, Context context,UserAdapter.Callback callback) {
        this.response = response;
        this.context = context;
        this.callback = callback;
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
        holder.username_text.setText(res.getLogin().getUsername());
        getImageFromUrl(res.getPicture().getLarge(),view);

        holder.userImage.setOnClickListener(v -> {
            if (callback != null) {
                callback.onDudeClicked(res.getLogin().getUsername(),
                        res.getName().getFirst(),
                        res.getName().getLast(),
                        res.getEmail(),
                        res.getPicture().getLarge());
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.response.getResults().size();
    }

    private void getImageFromUrl(String Url, ImageView view) {
        Picasso.get()
                .load(Url)
                .placeholder(R.color.colorPrimary)
                .error(R.color.colorPrimary)
                .into(view);
    }

    public interface Callback {
        void onDudeClicked(String username, String first, String last, String email, String picUrl);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
