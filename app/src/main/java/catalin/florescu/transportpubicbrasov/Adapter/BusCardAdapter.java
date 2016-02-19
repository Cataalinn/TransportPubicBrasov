package catalin.florescu.transportpubicbrasov.Adapter;

/**
 * Created by Florescu George Cătălin on 26.09.2015.
 */

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import catalin.florescu.transportpubicbrasov.R;

public class BusCardAdapter extends RecyclerView.Adapter<BusCardAdapter.ViewHolder> {

    private List<Bus> items;
    private int itemLayout;

    public BusCardAdapter(List<Bus> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Bus item = items.get(position);
        holder.itemView.setTag(item);
        holder.busImage.setImageResource(switchImage(item.getBusType()));
        holder.line.setText(item.getBusLine());
        holder.station.setText(item.getBusStation());
        holder.currentHour.setText(item.getHour() + ": " + item.getCurrentHourBus());
        holder.nextHour.setText(item.getHour() + 1 + ": " + item.getNextHourBus());
        holder.endRouteStation.setText(item.getEndRouteStation());
        holder.direction.setImageResource(switchDirection(item.getDirection()));

        holder.countDown.post(new Runnable() {
            @Override
            public void run() {
                new CountDownTimer(item.getCountDownTime().getTime(), 1000) {
                    public void onTick(long millisUntilFinished) {
                        holder.countDown.setText(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) +
                                ":" +
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
                    }

                    public void onFinish() {
                        holder.countDown.setText("A sosit!");
                    }
                }.start();
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void add(Bus item, int position) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Bus item) {
        int position = items.indexOf(item);
        items.remove(position);
        notifyItemRemoved(position);
    }

    private int switchImage(String type) {
        switch (type) {
            default:
                return R.mipmap.bus_black;
            case Bus.BUS:
                return R.mipmap.bus_black;
            case Bus.MIDI:
                return R.mipmap.bus_black;
            case Bus.TROLLEY:
                return R.mipmap.bus_black;
        }
    }

    private int switchDirection(int type) {
        switch (type) {
            case Bus.GOING:
                return R.mipmap.arrow_up;
            case Bus.RETURN:
                return R.mipmap.arrow_down;
            default:
                return R.mipmap.arrow_down;
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView busImage, direction;
        public TextView line, station, countDown, currentHour, nextHour, endRouteStation;
        public Handler handler;


        public ViewHolder(View itemView) {

            super(itemView);
            busImage = (ImageView) itemView.findViewById(R.id.busImage);
            direction = (ImageView) itemView.findViewById(R.id.direction);
            endRouteStation = (TextView) itemView.findViewById(R.id.endRouteStation);
            line = (TextView) itemView.findViewById(R.id.busLine);
            station = (TextView) itemView.findViewById(R.id.busStation);
            countDown = (TextView) itemView.findViewById(R.id.countDownTextViewID);
            currentHour = (TextView) itemView.findViewById(R.id.currentHourBus);
            nextHour = (TextView) itemView.findViewById(R.id.nextHourBus);
            handler = new Handler();
        }
    }
}
