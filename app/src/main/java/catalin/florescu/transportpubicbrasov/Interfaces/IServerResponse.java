package catalin.florescu.transportpubicbrasov.Interfaces;

import catalin.florescu.transportpubicbrasov.Objects.StationCardData;

/**
 * Created by Florescu George Cătălin on 24.09.2015.
 */
public interface IServerResponse {
    void onResponse(StationCardData serverObject);

    void onNoInternetConnection();
}
