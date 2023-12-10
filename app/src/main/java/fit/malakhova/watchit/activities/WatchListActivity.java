package fit.malakhova.watchit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import fit.malakhova.watchit.R;
import fit.malakhova.watchit.adapters.WatchlistAdapter;
import fit.malakhova.watchit.databinding.ActivityWatchListBinding;
import fit.malakhova.watchit.listeners.WatchlistListener;
import fit.malakhova.watchit.models.TVShow;
import fit.malakhova.watchit.viewmodels.WatchlistViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WatchListActivity extends AppCompatActivity implements WatchlistListener {

    private ActivityWatchListBinding activityWatchListBinding;
    private WatchlistViewModel viewModel;
    private WatchlistAdapter watchlistAdapter;
    private List<TVShow> watchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWatchListBinding = DataBindingUtil.setContentView(this, R.layout.activity_watch_list);
        doInitialization();
    }

    private void doInitialization() {
        viewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
        activityWatchListBinding.imageBack.setOnClickListener(view -> onBackPressed());
        watchList = new ArrayList<>();
    }

    private void loadWatchlist() {
        activityWatchListBinding.setIsLoading(true);
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(viewModel.loadWatchlist().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShows -> {
                    activityWatchListBinding.setIsLoading(false);
                    if (watchList.size() > 0) {
                        watchList.clear();
                    }
                    watchList.addAll(tvShows);
                    watchlistAdapter = new WatchlistAdapter(watchList, this);
                    activityWatchListBinding.watchlistRecyclerView.setAdapter(watchlistAdapter);
                    activityWatchListBinding.watchlistRecyclerView.setVisibility(View.VISIBLE);
                    compositeDisposable.dispose();
                }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWatchlist();
    }

    @Override
    public void onTVShowClicked(TVShow tvShow) {
        Intent intent = new Intent(getApplicationContext(), TVShowDetailsActivity.class);
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);
    }

    @Override
    public void removeTVShowFromWatchlist(TVShow tvShow, int position) {
        CompositeDisposable compositeDisposableForDelete = new CompositeDisposable();
        compositeDisposableForDelete.add(viewModel.removeTVShowFromWatchlist(tvShow)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    watchList.remove(position);
                    watchlistAdapter.notifyItemRemoved(position);
                    watchlistAdapter.notifyItemRangeChanged(position, watchlistAdapter.getItemCount());
                    compositeDisposableForDelete.dispose();
                }));
    }

}