package br.com.michelbarbosa.hearthstonedusthelperlite;


import javax.inject.Singleton;

import br.com.michelbarbosa.hearthstonedusthelperlite.api.ApiModule;
import br.com.michelbarbosa.hearthstonedusthelperlite.api.NetworkModule;
import br.com.michelbarbosa.hearthstonedusthelperlite.ui.activitys.BaseActivity;
import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ApiModule.class, AppModule.class})
public interface AppComponent {

    void inject(MainApp app);

    void inject(BaseActivity baseActivity);

    //todo: verificar a viabilidade de utilizar um baseFragment
//    void inject(BaseFragment baseFragment);

}
