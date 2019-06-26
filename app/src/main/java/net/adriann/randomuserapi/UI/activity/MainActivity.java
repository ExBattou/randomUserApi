package net.adriann.randomuserapi.UI.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.os.Bundle;
import android.widget.FrameLayout;

import net.adriann.randomuserapi.R;
import net.adriann.randomuserapi.UI.fragment.UserDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    ListFragment userListFragment;
    UserDetailFragment userDetailFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    public void switchFragments(@NonNull Fragment fragment, String tag) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_container,fragment,tag);
        t.commit();
    }

    public void setUpListFragment() {
        if (userListFragment != null) {
            userListFragment = new ListFragment();
        }
        switchFragments(userListFragment,userListFragment.getTag());

    }

    public void setUpUserFragment(String username,
                                  String firstname,
                                  String lastname,
                                  String email,
                                  String imageUrl){
        Fragment fragment = UserDetailFragment.newInstance(username,firstname,lastname,email,imageUrl);
        switchFragments( fragment,fragment.getTag());

    }

}
