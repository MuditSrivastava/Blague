package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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


public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    private final String TAG = MainActivityFragment.class.getSimpleName();
    private Context mContext;
    private InterstitialAd mInterstitialAd;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        progressBar = (ProgressBar)root.findViewById(R.id.spin_kit);
        DoubleBounce doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_test_ad_id));
        requestNewInterstitial();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Log.v(TAG,getResources().getString(R.string.tag_closeAd));
                requestNewInterstitial();
                retrieveJoke();
            }
        });

        mContext = root.getContext();

        Button button = (Button)  root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    Log.v(TAG,getResources().getString(R.string.tag_showAd));
                    mInterstitialAd.show();
                } else {
                    Log.v(TAG,getResources().getString(R.string.tag_notFinishedLoading));
                    retrieveJoke();
                }
            }
        });
        return root;
    }

    private void retrieveJoke() {
        Log.v(TAG,getResources().getString(R.string.Fetching_Joke));
        progressBar.setVisibility(View.VISIBLE);

        new FetchJoke(new Listener() {
            @Override
            public void onJokeLoaded(String joke) {
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(mContext, JokeViewer.class);
                intent.putExtra(JokeViewer.JOKE_EXTRA, joke);
                startActivity(intent);
            }
        }).execute();
    }

    private void requestNewInterstitial() {
        Log.v(TAG,getResources().getString(R.string.tag_requestAd));
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }


}
