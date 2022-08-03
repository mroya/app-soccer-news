package dio.me.soccernews.data;

import androidx.room.Room;

import dio.me.soccernews.App;
import dio.me.soccernews.data.local.SoccerNewsDb;
import dio.me.soccernews.data.remote.SoccerNewsApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoccerNewsRepository {

    //region Constantes
    private static final String REMOTE_API_URL = "https://mroya.github.io/soccer-news-api/";
    private static final String LOCAL_DB_NAME = "soccer-news";
    //endregion

    //region atributos: encapsulam o acesso a nossa API (Retrofit) e banco de dados local(Room).
    private final SoccerNewsApi remoteApi;
    private final SoccerNewsDb localDb;

    public SoccerNewsApi getRemoteApi() {
        return remoteApi;
    }

    public SoccerNewsDb getLocalDb() {
        return localDb;
    }
    //endregion

    //region Singleton: garante uma instância única dos atributos relacionados ao Retrofit e Room.
    private SoccerNewsRepository() {
        remoteApi = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SoccerNewsApi.class);
        localDb = Room.databaseBuilder(App.getInstance(), SoccerNewsDb.class, LOCAL_DB_NAME).build();
    }

    public static SoccerNewsRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final SoccerNewsRepository INSTANCE = new SoccerNewsRepository();
    }
    //endregion
}
