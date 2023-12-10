package fit.malakhova.watchit.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import fit.malakhova.watchit.repositories.SearchTVShowRepository;
import fit.malakhova.watchit.responses.TVShowsResponse;

public class SearchViewModel extends ViewModel {

    private SearchTVShowRepository searchTVShowRepository;

    public SearchViewModel() {
        searchTVShowRepository = new SearchTVShowRepository();
    }

    public LiveData<TVShowsResponse> searchTVShows(String query, int page) {
        return searchTVShowRepository.searchTVShow(query, page);
    }
}
