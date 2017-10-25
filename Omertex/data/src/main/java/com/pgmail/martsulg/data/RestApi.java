package com.pgmail.martsulg.data;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by lenovo on 16.08.2017.
 */

public interface RestApi {
    @GET("photos/?client_id=51cd375f21286b915a01ccc309c246b0f9b606df2f7d0f9d0df28eb7cf676b49")
    Observable<List<ResponseProfile>> getProfiles();

    @GET("users")
    Observable<List<Elements>> getElements();

}
