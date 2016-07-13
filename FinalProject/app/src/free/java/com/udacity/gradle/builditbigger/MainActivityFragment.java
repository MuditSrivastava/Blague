package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.android.jokeviewerlib.JokeViewer;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    private final String TAG = MainActivityFragment.class.getSimpleName();
    private Context mContext;
    private InterstitialAd mInterstitialAd;
    //private ProgressBar mProgressBar;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        //mProgressBar = (ProgressBar) root.findViewById(R.id.joke_loading_spinner);
        progressBar = (ProgressBar)root.findViewById(R.id.spin_kit);
        DoubleBounce doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        //Setting up InterstitialAd
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_test_ad_id));
        requestNewInterstitial();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Log.v(TAG,"Closing InterstitialAd");
                requestNewInterstitial();
                retrieveJoke();
            }
        });

        mContext = root.getContext();

        //Setting up button
        Button button = (Button)  root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    Log.v(TAG,"Showing InterstitialAd");
                    mInterstitialAd.show();
                } else {
                    Log.v(TAG,"InterstitialAd loading not finished");
                    retrieveJoke();
                }
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
                //mProgressBar.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(mContext, JokeViewer.class);
                intent.putExtra(JokeViewer.JOKE_EXTRA, joke);
                startActivity(intent);
            }
        }).execute();
    }

    private void requestNewInterstitial() {
        Log.v(TAG,"Requesting new InterstitialAd");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }


}
