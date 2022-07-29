package dio.me.soccernews.data.remote;

import java.util.List;

import dio.me.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SoccerNewsApi {

    @GET("news.json")
    Call<List<News>> getNews();

}
