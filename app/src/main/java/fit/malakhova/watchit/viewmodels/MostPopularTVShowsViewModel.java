package fit.malakhova.watchit.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import fit.malakhova.watchit.repositories.MostPopularTVShowsRepository;
import fit.malakhova.watchit.responses.TVShowsResponse;

public class MostPopularTVShowsViewModel extends ViewModel {

    private MostPopularTVShowsRepository mostPopularTVShowsRepository;

    public MostPopularTVShowsViewModel() {
        mostPopularTVShowsRepository = new MostPopularTVShowsRepository();
    }

    public LiveData<TVShowsResponse> getMostPopularTVShows(int page) {
        return mostPopularTVShowsRepository.getMostPopularTVShows(page);
    }
}
