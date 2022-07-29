package dio.me.soccernews.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dio.me.soccernews.databinding.NewsItemBinding;
import dio.me.soccernews.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.viewHolder> {

    private List<News> news;

    public NewsAdapter(List<News> news){
        this.news = news;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        News news = this.news.get(position);
        holder.binding.tvTitle.setText(news.getTitle());
        holder.binding.tvDescription.setText(news.getDescription());
    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        private final NewsItemBinding binding;

        public viewHolder(NewsItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
