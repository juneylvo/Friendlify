package interface_adapter.editpreferences;
import api.SpotifyInteractor;
import interface_adapter.editpreferences.SpotifyDataGateway;
import org.json.JSONObject;

public class SpotifyGatewayImplementation extends SpotifyDataGateway {
    private final SpotifyInteractor interactor;

    public SpotifyGatewayImplementation(SpotifyInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public JSONObject getCurrentUserPlaylists(int limit, int offset) {
        return interactor.getCurrentUserPlaylists(limit, offset);
    }
}

