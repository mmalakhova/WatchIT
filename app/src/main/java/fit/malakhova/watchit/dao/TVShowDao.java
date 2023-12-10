package fit.malakhova.watchit.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fit.malakhova.watchit.models.TVShow;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface TVShowDao {

    @Query("SELECT * FROM tvShows")
    Flowable<List<TVShow>> getWatchlist();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToWatchList(TVShow tvShow);

    @Delete
    Completable removeFromWatchlist(TVShow tvShow);

    @Query("select * from tvShows where id =:tvShowId")
    Flowable<TVShow> getTvShowFromWatchlist(String tvShowId);
}
