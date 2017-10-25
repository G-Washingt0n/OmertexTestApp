package com.pgmail.martsulg.domain;


import android.util.Log;

import com.pgmail.martsulg.data.ProfilesRestService;
import com.pgmail.martsulg.data.ResponseProfile;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by AndroidDeveloper on 28.09.17.
 */

public class UrlUseCase extends UseCase<Void, ArrayList<String>> {
    @Override
    protected Observable<ArrayList<String>> buildUseCase(Void param) {


        return ProfilesRestService.getInstance().getProfiles().map(new Function<List<ResponseProfile>, ArrayList<String>>() {
            @Override
            public ArrayList<String> apply(@NonNull List<ResponseProfile> profileList) throws Exception {
                return convert(profileList);
            }
        });
    }

    private ArrayList<String> convert(List<ResponseProfile> profileList){
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<profileList.size();i++){

            list.add(profileList.get(i).getLinks().getDownload());
        }

        return list;
    }




}

