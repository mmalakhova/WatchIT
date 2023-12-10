package fit.malakhova.watchit.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fit.malakhova.watchit.R;
import fit.malakhova.watchit.databinding.ItemContainerTvShowBinding;
import fit.malakhova.watchit.listeners.TVShowListener;
import fit.malakhova.watchit.models.TVShow;

public class TVShowsAdapter extends RecyclerView.Adapter<TVShowsAdapter.TVShowViewHolder> {

    private List<TVShow> tvShows;
    private LayoutInflater layoutInflater;
    private TVShowListener tvShowListener;

    public TVShowsAdapter(List<TVShow> tvShows, TVShowListener tvShowListener) {
        this.tvShows = tvShows;
        this.tvShowListener = tvShowListener;
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerTvShowBinding tvShowBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_tv_show, parent, false
        );
        return new TVShowViewHolder(tvShowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder holder, int position) {
        holder.bindTVShow(tvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }


    class TVShowViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerTvShowBinding itemContainerTvShowBinding;

        public TVShowViewHolder(ItemContainerTvShowBinding itemContainerTvShowBinding) {
            super(itemContainerTvShowBinding.getRoot());
            this.itemContainerTvShowBinding = itemContainerTvShowBinding;
        }

        public void bindTVShow(TVShow tvShow) {
            itemContainerTvShowBinding.setTvShow(tvShow);
            //против мерцания
            itemContainerTvShowBinding.executePendingBindings();
            itemContainerTvShowBinding.getRoot().setOnClickListener(view -> tvShowListener.onTVShowClicked(tvShow));
        }
    }
}
