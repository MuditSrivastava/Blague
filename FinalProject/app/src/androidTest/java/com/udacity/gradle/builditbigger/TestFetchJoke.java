package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

/**
 * Created by DELL on 7/30/2016.
 */
public class TestFetchJoke extends AndroidTestCase {
    public void test(){
        new FetchJoke(new Listener() {
            @Override
            public void onJokeLoaded(String joke) {
                assertNotNull(joke);
                assertNotSame(joke,"");
            }
        }).execute();
    }
}
