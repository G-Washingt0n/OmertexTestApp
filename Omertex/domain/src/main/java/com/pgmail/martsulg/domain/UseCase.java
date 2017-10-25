package com.pgmail.martsulg.domain;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 11.08.2017.
 */

public abstract class UseCase<InParam, OutParam> {

    private Disposable disposable;

    protected abstract Observable<OutParam> buildUseCase(InParam param);

    public void execute(InParam param, DisposableObserver<OutParam> disposableObserver){

disposable = buildUseCase(param) //сдесь происходит только созжание
        .observeOn(AndroidSchedulers.mainThread()) //где будет получен ответ (в указанном потоке)
        .subscribeOn(Schedulers.newThread())   //где будет выполнен код (указан поток)
        .subscribeWith(disposableObserver);  //observable выполнится только после <--- этой строчки,
        // до этого он просто будет создан
    }

    public void dispose(){ //отписка получателя от observable

        if(!disposable.isDisposed()){
            disposable.dispose();
        }

    }


}
