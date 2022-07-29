package dio.me.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import dio.me.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        //TODO Remover Mock de Noticias
        List<News> news = new ArrayList<>();
        news.add(new News("Grêmio Tem Desfalque Importante", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit.."));
        news.add(new News("Grêmio Joga No Sábado", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit.."));
        news.add(new News("Copa Do Mundo Feminina Está Terminando", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit.."));

        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
}