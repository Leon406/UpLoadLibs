package me.leon.rxbus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * <p>
 * Created by YoKeyword on 2015/6/17.
 * modify to RxJava2 by Leon 2017/9/10
 * support sticky event
 */
public class RxBus2 {
    private static volatile RxBus2 mDefaultInstance;
    private final Subject mBus;

    private final Map<Class<?>, Object> mStickyEventMap;

    public RxBus2() {
        mBus = PublishSubject.create().toSerialized();
        mStickyEventMap = new ConcurrentHashMap<>();
    }

    /**
     *  sigleton
     * @return RxBus2
     */
    public static RxBus2 getDefault() {
        if (mDefaultInstance == null) {
            synchronized (RxBus2.class) {
                if (mDefaultInstance == null) {
                    mDefaultInstance = new RxBus2();
                }
            }
        }
        return mDefaultInstance;
    }

    /**
     * post event
     * @param event 事件
     */

    public void post(Object event) {
        mBus.onNext(event);
    }

    /**
     *  register Event
     * @param eventType 事件类型
     * @param <T> 泛型
     * @return Observable
     */
    public <T> Observable<T> registerEvent(Class<T> eventType) {
        return mBus.ofType(eventType);
    }

    /**
     *  whether has observers
     * @return boolean
     */
    public boolean hasObservers() {
        return mBus.hasObservers();
    }

    public void unregister() {
        mDefaultInstance = null;
    }

    /**
     * @param event 事件
     */
    public void postSticky(Object event) {
        mStickyEventMap.put(event.getClass(), event);
        post(event);
    }



    /**
     * get Observable  by eventType
     * @param eventType 类型
     * @param <T> 泛型
     * @return Observable
     */
    public <T> Observable<T> registerStickyEvent(final Class<T> eventType) {

        Observable<T> observable = mBus.ofType(eventType);
        final Object event = mStickyEventMap.get(eventType);

        if (event != null) {
            return observable.mergeWith(Observable.create(new ObservableOnSubscribe<T>() {
                @Override
                public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                    subscriber.onNext(eventType.cast(event));
                }
            }));
        } else {
            return observable;
        }

    }



    /**
     * get Sticky event by eventType
     * @param eventType 类型
     * @param <T> 泛型
     * @return  eventType
     */
    public <T> T getStickyEvent(Class<T> eventType) {
            return eventType.cast(mStickyEventMap.get(eventType));
    }


    /**
     * remove specific eventType  of sticky event
     * @param eventType 类型
     * @param <T> 泛型
     * @return T eventType
     */
    public <T> T removeStickyEvent(Class<T> eventType) {
            return eventType.cast(mStickyEventMap.remove(eventType));
    }


    /**
     * remove  all sticky events
     */
    public void removeAllStickyEvents() {
            mStickyEventMap.clear();
    }
}
