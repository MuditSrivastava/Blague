package com.example.DELL.myapplication.backend;

import com.example.JokesClass;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.DELL.example.com",
                ownerName = "backend.myapplication.DELL.example.com",
                packagePath=""
        )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }
    @ApiMethod(name ="TellaJoke")
    public MyBean TellaJoke(){
        MyBean response = new MyBean();
        response.setData(JokesClass.getJoke());
        return response;
    }

}
