package com.udacity.gradle.builditbigger;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.example.android.jokeviewerlib.JokeViewer;


public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    private final String TAG = MainActivityFragment.class.getSimpleName();
    private Context mContext;
    //private ProgressBar mProgressBar;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
       // mProgressBar = (ProgressBar) root.findViewById(R.id.joke_loading_spinner);
        progressBar = (ProgressBar)root.findViewById(R.id.spin_kit);
        DoubleBounce doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
        mContext = root.getContext();

        //Setting up button
        Button button = (Button)  root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveJoke();

            }
        });
        return root;
    }

    private void retrieveJoke() {
        Log.v(TAG,"Fetching Joke");
        //mProgressBar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        new FetchJoke(new Listener() {
            @Override
            public void onJokeLoaded(String joke) {
               // mProgressBar.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(mContext, JokeViewer.class);
                intent.putExtra(JokeViewer.JOKE_EXTRA, joke);
                startActivity(intent);
            }
        }).execute();
    }

}
