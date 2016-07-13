package com.udacity.gradle.builditbigger;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dell.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by DELL on 7/26/2016.
 */
public class FetchJoke extends AsyncTask<Void, Void, String> {

    public static String TAG = FetchJoke.class.getSimpleName();
    private Listener mListener;
    private static MyApi myApiService = null;

    public FetchJoke(Listener listener) {
        mListener = listener;
    }
    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {
            /*MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?>
                                                       abstractGoogleClientRequest)
                                throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });*/
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://spry-device-138519.appspot.com/_ah/api/");
            // end options for devappserver
            myApiService = builder.build();
        }

        try {
            return myApiService.tellaJoke().execute().getData();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return "";
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        mListener.onJokeLoaded(joke);
    }

}
