package dio.me.soccernews.ui.news;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import java.util.Objects;

import dio.me.soccernews.MainActivity;
import dio.me.soccernews.data.local.AppDatabase;
import dio.me.soccernews.databinding.FragmentNewsBinding;
import dio.me.soccernews.ui.adapters.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsViewModel.getNews().observe(getViewLifecycleOwner(),news -> {
            binding.rvNews.setAdapter(new NewsAdapter(news, updatedNews -> {
                MainActivity activity = (MainActivity) getActivity();
                activity.getDb().newsDao().save(updatedNews);

                AsyncTask.execute(() -> {
                    if (activity != null) {
                        activity.getDb().newsDao().save(updatedNews);
                    }
                });
            }));
        });

        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case DOING:
                    // TODO: Iniciar SwipeRefreshLayout (loding)
                    break;
                case DONE:
                    // TODO: Finalizar SwipeRefreshLayout (loding)
                    break;
                case ERROR:
                    // TODO: Finalizar SwipeRefreshLayout (loding)
                    // TODO: Mostrar um erro genérico.
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}