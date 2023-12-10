package fit.malakhova.watchit.responses;

import com.google.gson.annotations.SerializedName;

import fit.malakhova.watchit.models.TVShowDetails;

public class TVShowDetailsResponse {

    @SerializedName("tvShow")
    private TVShowDetails tvShowDetails;

    public TVShowDetails getTvShowDetails() {
        return tvShowDetails;
    }
}
