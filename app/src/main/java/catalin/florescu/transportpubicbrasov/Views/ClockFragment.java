package catalin.florescu.transportpubicbrasov.Views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import catalin.florescu.transportpubicbrasov.Adapter.Bus;
import catalin.florescu.transportpubicbrasov.Adapter.BusCardAdapter;
import catalin.florescu.transportpubicbrasov.Adapter.DividerItemDecoration;
import catalin.florescu.transportpubicbrasov.Helpers.TimeUtils;
import catalin.florescu.transportpubicbrasov.Interfaces.IServerResponse;
import catalin.florescu.transportpubicbrasov.Network.GetTimesAsyncTask;
import catalin.florescu.transportpubicbrasov.Objects.StationCardData;
import catalin.florescu.transportpubicbrasov.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClockFragment extends Fragment implements IServerResponse, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ClockFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_clock, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
            }
        });

        return view;
    }


    @Override
    public void onResponse(StationCardData serverObject) {
        recyclerView.setAdapter(new BusCardAdapter(getList(serverObject), R.layout.bus_card));
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onNoInternetConnection() {
        Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        String URL = "http://www.urbus.ro/display/get_station_view/593/all/12/6";
        GetTimesAsyncTask getTimesAsyncTask = new GetTimesAsyncTask(getActivity(), this);
        getTimesAsyncTask.execute(URL);
    }

    private ArrayList<Bus> getList(StationCardData stationCardData) {

        ArrayList<Bus> list = new ArrayList<>();

        for (int i = 0; i < stationCardData.timesObject.routes.length; i++) {
            Bus bus = new Bus();
            bus.setBusStation(stationCardData.timesObject.name);
            bus.setBusLine(String.valueOf(stationCardData.timesObject.routes[i].busNumber));
            bus.setCountDownTime(new TimeUtils(0, i));
            bus.setBusType(Bus.BUS);
            bus.setDirection(stationCardData.timesObject.routes[i].directionIndicator);
            bus.setEndRouteStation(stationCardData.timesObject.routes[i].routeEndStation);
            bus.setHour(stationCardData.currentHour);

            String currentHour = "";
            for (String item : stationCardData.timesObject.routes[i].currentHourMinutes) {

                if (item.contains("h"))
                    currentHour = currentHour.concat(item.substring(0, 2)).concat("*").concat(", ");
                else if (item.contains("b"))
                    currentHour = currentHour.concat(item.substring(0, 2)).concat("**").concat(", ");
                else
                    currentHour = currentHour.concat(item.substring(0, 2)).concat(", ");
            }

            bus.setCurrentHourBus(currentHour);

            String nextHour = "";
            for (String item : stationCardData.timesObject.routes[i].nextHourMinutes) {
                if (item.contains("h"))
                    nextHour = nextHour.concat(item.substring(0, 2)).concat("*").concat(", ");
                else if (item.contains("b"))
                    nextHour = nextHour.concat(item.substring(0, 2)).concat("**").concat(", ");
                else
                    nextHour = nextHour.concat(item.substring(0, 2)).concat(", ");
            }

            bus.setNextHourBus(nextHour);

            list.add(bus);
        }

        return list;
    }


}
