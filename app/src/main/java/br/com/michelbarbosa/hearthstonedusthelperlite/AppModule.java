package br.com.michelbarbosa.hearthstonedusthelperlite;

import android.content.Context;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@Module
public class AppModule {

    private final MainApp mainApp;
    private final Context mContext;

    public AppModule(MainApp mainApp, Context mContext) {
        this.mainApp = mainApp;
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    public MainApp getMainApp() {
        return mainApp;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return mContext;
    }

    @Provides
    @Singleton
    public Picasso providePicasso(Context context) {
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(new OkHttpClient()))
                .loggingEnabled(BuildConfig.DEBUG)
                .build();
    }

    @Provides
    public Cache provideCache() {
        return new Cache(mContext.getCacheDir(), 10240*1024);
    }

}
