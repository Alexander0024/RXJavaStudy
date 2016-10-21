package com.lirenkj.rxjavastudy.study;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * BasicRxJava
 * <p>
 * Created by Alexander on 2016/10/21.
 */
@SuppressWarnings("unused")
public class BasicRxJava {
    private static String TAG = "BasicRxJava";

    /**
     * Observer 即观察者，它决定事件触发的时候将有怎样的行为。
     */
    private static Observer<String> BasicObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        };
        return observer;
    }

    /**
     * Subscriber 对 Observer 接口进行了一些扩展，但他们的基本使用方式是完全一样的
     */
    private static Subscriber<String> BasicSubscriber() {
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        };
        return subscriber;
    }

    /**
     * Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件。
     * RxJava 使用 create() 方法来创建一个 Observable ，并为它定义事件触发规则
     */
    private static Observable<String> BasicObservable() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Alex");
                subscriber.onCompleted();
            }
        });

        // equals
        Observable<String> observable2 = Observable.just("Hello", "Hi", "Alex");

        // equals
        String[] words = {"Hello", "Hi", "Alex"};
        Observable<String> observable3 = Observable.from(words);

        return observable3;
    }

    /**
     * 创建了 Observable 和 Observer 之后，再用 subscribe() 方法将它们联结起来，整条链子就可以工作了。
     */
    private void Subscribe() {
        BasicObservable().subscribe(BasicObserver());
        // OR
        BasicObservable().subscribe(BasicSubscriber());
    }
}
