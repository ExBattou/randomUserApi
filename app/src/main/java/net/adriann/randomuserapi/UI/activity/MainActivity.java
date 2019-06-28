package net.adriann.randomuserapi.UI.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import net.adriann.randomuserapi.R;
import net.adriann.randomuserapi.UI.fragment.ListFragment;
import net.adriann.randomuserapi.UI.fragment.UserDetailFragment;
import net.adriann.randomuserapi.adapter.UserAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements UserAdapter.Callback {

    private static final String BACK_STACK_ROOT_TAG = "USER_STACK";
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;


    ListFragment userListFragment;
    UserDetailFragment userDetailFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpListFragment();
    }

    public void switchFragments(@NonNull Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();
    }

    public void setUpListFragment() {
        if (userListFragment == null) {
            userListFragment = ListFragment.newInstance();
            switchFragments(userListFragment);
            Toast.makeText(this,"loading Fragment",Toast.LENGTH_LONG);
        }
    }

    public void setUpUserFragment(String username,
                                  String firstname,
                                  String lastname,
                                  String email,
                                  String imageUrl){
        Fragment fragment = UserDetailFragment.newInstance(username,firstname,lastname,email,imageUrl);
        switchFragments(fragment);

    }


    @Override
    public void onDudeClicked(String username, String first, String last, String email, String picurl) {
        setUpUserFragment(username,first,last,email,picurl);
    }
}
