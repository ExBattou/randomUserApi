package net.adriann.randomuserapi.UI.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import net.adriann.randomuserapi.R;
import net.adriann.randomuserapi.UI.activity.MainActivity;
import net.adriann.randomuserapi.adapter.UserAdapter;
import net.adriann.randomuserapi.model.Response;
import net.adriann.randomuserapi.service.RandomService;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;

public class ListFragment extends Fragment  {

    @BindView(R.id.recycler_list)
    public androidx.recyclerview.widget.RecyclerView recyclerView;
    @BindView(R.id.empty_state)
    public ImageView emptyState;
    @BindView(R.id.progress_che)
    ProgressBar progressBar;

    public UserAdapter adapter;

    public ListFragment() { }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        progressBar.setVisibility(View.VISIBLE);
        populateList();
    }

    public void populateList(){
        RandomService.getInstance()
                .getRandomUseApi()
                .getUsers()
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Snackbar.make(getView(),"Loading list...",Snackbar.LENGTH_LONG);
                        setRecyclerWithAdapter(response.body());
                        progressBar.setVisibility(View.GONE);
                    }
                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(),"Aw Geez, Rick! something went wrong and the list won´t Load!",Toast.LENGTH_LONG);
                    }
                });

    }

    public void setRecyclerWithAdapter(Response r){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3,RecyclerView.VERTICAL,false);

        if (recyclerView == null ) {
            recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_list);
        }
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new UserAdapter(r, getContext(), new UserAdapter.Callback() {
            @Override
            public void onDudeClicked(String username, String first, String last, String email, String picUrl) {
                ((MainActivity) getActivity()).setUpUserFragment(username,first,last,email,picUrl);
            }
        });
        setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
        validateEmptyState();
    }

    public RecyclerView.Adapter getAdapter() {
        return recyclerView.getAdapter();
    }

    public void validateEmptyState() {
        if(adapter.getItemCount()==0) {
            recyclerView.setVisibility(View.GONE);
            emptyState.setVisibility(View.VISIBLE);
        }else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyState.setVisibility(View.GONE);
        }
    }

}
