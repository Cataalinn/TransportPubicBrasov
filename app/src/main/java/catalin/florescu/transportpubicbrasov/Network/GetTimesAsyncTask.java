package catalin.florescu.transportpubicbrasov.Network;

import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import catalin.florescu.transportpubicbrasov.Interfaces.IServerResponse;
import catalin.florescu.transportpubicbrasov.Objects.StationCardData;
import catalin.florescu.transportpubicbrasov.Objects.TimesObject;

/**
 * Created by Florescu George Cătălin on 24.09.2015.
 */
public class GetTimesAsyncTask extends AsyncTask<String, Void, StationCardData> {

    private IServerResponse serverResponse;
    private Context context;

    public GetTimesAsyncTask(Context context, IServerResponse senderController) {
        this.serverResponse = senderController;
        this.context = context;
    }

    @Override
    protected final StationCardData doInBackground(String... params) {

        StationCardData stationCardData = new StationCardData();

        if (InternetConnectionChecker.isNetworkAvailable(context)) {

            stationCardData.timesObject = new TimesObject();

            try {
                //response from server
                Response response = getResponse(params[0]);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                String re = response.body().string();
                stationCardData = objectMapper.readValue(re, StationCardData.class);

            } catch (IOException e) {
                e.printStackTrace();
                //something was wrong here
            }
        }
        return stationCardData;
    }

    @Override
    protected void onPostExecute(StationCardData stationCardData) {
        super.onPostExecute(stationCardData);

        if (stationCardData.currentHour != null)
            serverResponse.onResponse(stationCardData);
        else
            serverResponse.onNoInternetConnection();
    }

    private Response getResponse(String url) {
        OkHttpClient client = new OkHttpClient();

        // after 10 seconds with no response, throw SocketTimeOutException
        int CONNECTION_TIMEOUT = 10;
        client.setConnectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);

        Request request = new Request.Builder().url(url).build();
        Response response = null;

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

}

