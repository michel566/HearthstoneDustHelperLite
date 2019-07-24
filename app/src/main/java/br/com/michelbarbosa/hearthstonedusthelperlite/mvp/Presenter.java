package br.com.michelbarbosa.hearthstonedusthelperlite.mvp;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import br.com.michelbarbosa.hearthstonedusthelperlite.api.IApi;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Card;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Cardback;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Info;
import br.com.michelbarbosa.hearthstonedusthelperlite.utils.CLogger;
import br.com.michelbarbosa.hearthstonedusthelperlite.utils.Util;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Presenter {

    private IView mView;
    private static Info mInfo;
    @Inject
    IApi mIApi;

    @Inject
    public Presenter() {
    }

    public void setmView(IView mView) {
        this.mView = mView;
    }

    public void getInfo() {
        if (mInfo == null) {
            mView.showProgress();
            Observable<Info> observable = mIApi.getInfo();
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SelfDefineSubscriber<Info>() {
                        @Override
                        public void onNext(Info info) {
                            mInfo = info;
                            mView.onUpdate(mInfo);
                            mView.hideProgress();

                        }
                    });
        } else {
            mView.hideProgress();
            mView.onUpdate(mInfo);
        }
    }

    public void getAllCards() {
        mView.showProgress();
        Observable<List<Card>> observable = mIApi.getAllCards(getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardBacks() {
        mView.showProgress();
        Observable<List<Cardback>> observable = mIApi.getCardsBacks(getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Cardback>>() {
                    @Override
                    public void onNext(List<Cardback> cards) {
                        mView.hideProgress();

                    }
                });
    }

    public void searchCard(String name) {
        mView.showProgress();
        Observable<List<Card>> observable = mIApi.searchCards(name, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardSet(String set) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardSet(set, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardByClass(String classes) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardsByClass(classes, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardByFaction(String faction) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardByFaction(faction, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardByQuality(String quality) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardByQuality(quality, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardByRace(String Race) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardByRace(Race, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardByType(String type) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardByType(type, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    /**
     * get the nine basic heros and classes
     */
    public void getCardByClassType() {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardByType("Hero", getLocal());
        observable.subscribeOn(Schedulers.io())
                .flatMap(new Func1<List<Card>, Observable<Card>>() {
                    @Override
                    public Observable<Card> call(List<Card> cards) {
                        return Observable.from(cards);
                    }
                })
                .filter(new Func1<Card, Boolean>() {
                    @Override
                    public Boolean call(Card card) {
                        return (card.cardId.startsWith("HERO") && !card.cardId.endsWith("a"));
                    }
                })
                .buffer(18)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        CLogger.i("onnext:" + cards.size());
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getSingleCard(String name) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getSingleCard(name, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards.get(0));
                        mView.hideProgress();

                    }
                });
    }

    private String getLocal() {
        if (mInfo != null && mInfo.locales != null) {
            String local = Util.getCurrentLauguage();
            for (String s : mInfo.locales) {
                if (s.contains(local)) {
                    return s;
                }
            }
        }

        return "enUS";
    }

    class SelfDefineSubscriber<T> extends Subscriber<T> {
        @Override
        public void onCompleted() {
            mView.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            CLogger.e(e);
            mView.onError(e.getMessage());
        }

        @Override
        public void onNext(T t) {

        }
    }


}
