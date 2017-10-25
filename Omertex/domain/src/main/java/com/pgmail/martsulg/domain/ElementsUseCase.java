package com.pgmail.martsulg.domain;


import com.pgmail.martsulg.data.Elements;
import com.pgmail.martsulg.data.ElementsRestService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by AndroidDeveloper on 28.09.17.
 */

public class ElementsUseCase extends UseCase<Void, List<ElementsModel>> {
    @Override
    protected Observable<List<ElementsModel>> buildUseCase(Void param) {


        return ElementsRestService.getInstance().getElements().map(new Function<List<Elements>, List<ElementsModel>>() {
            @Override
            public List<ElementsModel> apply(@NonNull List<Elements> elementsList) throws Exception {
                return convert(elementsList);
            }
        });
    }

    private List<ElementsModel> convert(List<Elements> elementsList){
        List<ElementsModel> list = new ArrayList<>();
        for(int i=0;i<elementsList.size();i++){
            ElementsModel model = new ElementsModel();
            model.setName(elementsList.get(i).getName());
            model.setSurname(elementsList.get(i).getSurname());
            model.setEmail(elementsList.get(i).getEmail());
            model.setPhone(elementsList.get(i).getPhone());
            model.setWebsite(elementsList.get(i).getWebsite());

            list.add(model);
        }

        return list;
    }




}

