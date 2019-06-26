package net.adriann.randomuserapi.UI.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import net.adriann.randomuserapi.R;

import butterknife.BindView;

public class UserDetailFragment extends Fragment {

    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.first_name)
    TextView firstname;
    @BindView(R.id.last_name)
    TextView lastname;
    @BindView(R.id.user_image)
    ImageView userImage;

    Context context;


    private static final String USERNAME = "username";
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String EMAIL_ADDRESS = "email";
    private static final String USER_IMAGE_URL = "userimageurl";

    public static UserDetailFragment newInstance(String username,
                                                 String firstname,
                                                 String lastname,
                                                 String email,
                                                 String userImageUrl) {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME, username);
        args.putString(FIRST_NAME, firstname);
        args.putString(LAST_NAME, lastname);
        args.putString(EMAIL_ADDRESS, email);
        args.putString(USER_IMAGE_URL, userImageUrl);

        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        this.context = getContext();
        username.setText(getArguments().get(USERNAME).toString());
        firstname.setText(getArguments().get(FIRST_NAME).toString());
        lastname.setText(getArguments().get(LAST_NAME).toString());
        getImageFromUrl(getArguments().get(USER_IMAGE_URL).toString(),userImage);
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
