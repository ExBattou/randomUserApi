package net.adriann.randomuserapi.UI.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import net.adriann.randomuserapi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserDetailFragment extends Fragment {

    @BindView(R.id.username_detail)
    TextView username_tv;
    @BindView(R.id.first_name)
    TextView firstname_tv;
    @BindView(R.id.last_name)
    TextView lastname_tv;
    @BindView(R.id.email)
    TextView email_tv;
    @BindView(R.id.image_user_detail)
    ImageView userImage_iv;



    Context context;


    private static final String USERNAME = "username";
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String EMAIL_ADDRESS = "email";
    private static final String USER_IMAGE_URL = "userimageurl";

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String userimageurl;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString(USERNAME);
            lastname = getArguments().getString(LAST_NAME);
            firstname = getArguments().getString(FIRST_NAME);
            email = getArguments().getString(EMAIL_ADDRESS);
            userimageurl = getArguments().getString(USER_IMAGE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        this.context = getContext();
        username_tv.setText(username);
        firstname_tv.setText(firstname);
        lastname_tv.setText(lastname);
        email_tv.setText(email);

        getImageFromUrl(userimageurl,userImage_iv);
    }

    @OnClick(R.id.closing_x)
    public void closeFragment(){
        FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        fragTransaction.show(this);
        fragTransaction.commit();
        getActivity().onBackPressed();
    }


    private void getImageFromUrl(String Url, ImageView view) {
        Picasso.get()
                .load(Url)
                .placeholder(R.color.colorPrimary)
                .error(R.color.colorPrimary)
                .into(view);
    }

}
