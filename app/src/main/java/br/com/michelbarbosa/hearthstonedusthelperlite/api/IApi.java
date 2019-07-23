package br.com.michelbarbosa.hearthstonedusthelperlite.api;

import java.util.List;

import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Card;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Cardback;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Info;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface IApi {

    @GET("info")
    Observable<Info> getInfo();

    @GET("cards")
    Observable<List<Card>> getAllCards(@Query("locale") String locale);

    @GET("cards/search/{name}")
    Observable<List<Card>> searchCards(@Path("name") String classes, @Query("locale") String locale);

    @GET("cards/sets/{set}")
    Observable<List<Card>> getCardSet(@Path("set") String classes, @Query("locale") String locale);

    @GET("cards/classes/{class}")
    Observable<List<Card>> getCardsByClass(@Path("class") String classes, @Query("locale") String locale);

    @GET("cards/factions/{faction}")
    Observable<List<Card>> getCardByFaction(@Path("faction") String classes, @Query("locale") String locale);

    @GET("cards/qualities/{quality}")
    Observable<List<Card>> getCardByQuality(@Path("quality") String classes, @Query("locale") String locale);

    @GET("cards/races/{race}")
    Observable<List<Card>> getCardByRace(@Path("race") String classes, @Query("locale") String locale);

    @GET("cards/types/{menuType}")
    Observable<List<Card>> getCardByType(@Path("menuType") String classes, @Query("locale") String locale);

    @GET("cards/{name}")
    Observable<List<Card>> getSingleCard(@Path("name") String classes, @Query("locale") String locale);

    @GET("cards/classes/cardbacks")
    Observable<List<Cardback>> getCardsBacks(@Query("locale") String locale);


}
