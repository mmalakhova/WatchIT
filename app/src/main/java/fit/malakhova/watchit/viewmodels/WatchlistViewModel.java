package fit.malakhova.watchit.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import fit.malakhova.watchit.database.TVShowsDatabase;
import fit.malakhova.watchit.models.TVShow;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class WatchlistViewModel extends AndroidViewModel {

    private TVShowsDatabase tvShowsDatabase;

    public WatchlistViewModel(@NonNull Application application) {
        super(application);
        tvShowsDatabase = TVShowsDatabase.getTvShowsDatabase(application);
    }

    public Flowable<List<TVShow>> loadWatchlist() {
        return tvShowsDatabase.tvShowDao().getWatchlist();
    }

    public Completable removeTVShowFromWatchlist(TVShow tvShow) {
        return tvShowsDatabase.tvShowDao().removeFromWatchlist(tvShow);
    }
}
