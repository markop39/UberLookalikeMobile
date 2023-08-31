package com.example.taxidriver.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.taxidriver.R;
import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.repository.DriverRepository;
import com.example.taxidriver.data.repository.PassengerRepository;
import com.example.taxidriver.data.repository.UserRepository;
import com.example.taxidriver.domain.model.DriveReport;
import com.example.taxidriver.ui.adapters.DriveReportAdapter;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class DriverAccountReports extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<DriveReport> items;
    private ListView driveReportsListView;
    int ridesSum = 0;
    int kmSum = 0;
    int moneySum = 0;
    double averageRides = 0;
    LocalDateTime fromDate;
    LocalDateTime toDate;
    double averageKm = 0;
    double averageMoney = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DriverRepository driverRepository = new DriverRepository();
    UserRepository userRepository = new UserRepository();
    SharedPreferences prefs = TaxiDriver.getAppContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);

    public DriverAccountReports() {
        // Required empty public constructor
    }

    public static DriverAccountReports newInstance(String param1, String param2) {
        DriverAccountReports fragment = new DriverAccountReports();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment newInstance() {
        return new DriverAccountReports();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        T
//        ArrayList<DriveReport> items = getReportRides(LocalDateTime.of(2022, 11, 14, 0, 0), LocalDateTime.of(2022, 11, 19, 0, 0));
        View view = inflater.inflate(R.layout.fragment_passenger_account_reports, container, false);
        driveReportsListView = view.findViewById(R.id.listViewReport);

        items = new ArrayList<DriveReport>();
        DriveReportAdapter adapter = new DriveReportAdapter(getActivity(), items);
        driveReportsListView.setAdapter(adapter);
        CalendarView from = view.findViewById(R.id.dateStarting);
        CalendarView to = view.findViewById(R.id.dateEnding);
        getReportRides(LocalDateTime.now(), LocalDateTime.now().plusWeeks(1), adapter, view);
        from.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                fromDate = LocalDateTime.of(year, month + 1, day, 0, 0);
                if (toDate != null) {
                    if (fromDate.isBefore(toDate) && toDate != null) {
//                        TextView totalRides = view.findViewById(R.id.totalRides);
//                        TextView totalKm = view.findViewById(R.id.totalKm);
//                        TextView totalMoney = view.findViewById(R.id.totalSpent);
//                        TextView averageRidesText = view.findViewById(R.id.averageRides);
//                        TextView averageKmText = view.findViewById(R.id.averageKm);
//                        TextView averageMoneyText = view.findViewById(R.id.averageSpent);

                        getReportRides(fromDate, toDate, adapter, view);
//                        adapter.items = items;
//                        adapter.notifyDataSetChanged();
//                        totalRides.setText(String.valueOf(ridesSum));
//                        totalKm.setText(String.valueOf(kmSum));
//                        totalMoney.setText(String.valueOf(moneySum));
//                        averageRidesText.setText(String.valueOf(averageRides));
//                        averageKmText.setText(String.valueOf(averageKm));
//                        averageMoneyText.setText(String.valueOf(averageMoney));


                    }
                }

            }
        });
        to.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                toDate = LocalDateTime.of(year, month + 1, day, 0, 0);
                if (fromDate != null) {
                    if (fromDate.isBefore(toDate)) {
//                        TextView totalRides = view.findViewById(R.id.totalRides);
//                        TextView totalKm = view.findViewById(R.id.totalKm);
//                        TextView totalMoney = view.findViewById(R.id.totalSpent);
//                        TextView averageRidesText = view.findViewById(R.id.averageRides);
//                        TextView averageKmText = view.findViewById(R.id.averageKm);
//                        TextView averageMoneyText = view.findViewById(R.id.averageSpent);

                        getReportRides(fromDate, toDate, adapter, view);
//                        adapter.items = items;
//                        adapter.notifyDataSetChanged();
//                        totalRides.setText(String.valueOf(ridesSum));
//                        totalKm.setText(String.valueOf(kmSum));
//                        totalMoney.setText(String.valueOf(moneySum));
//                        averageRidesText.setText(String.valueOf(averageRides));
//                        averageKmText.setText(String.valueOf(averageKm));
//                        averageMoneyText.setText(String.valueOf(averageMoney));
//
//                        adapter.notifyDataSetChanged();
                    }
                }
            }

        });
        adapter.notifyDataSetChanged();
        return view;
    }

    public class dateChangeListener implements CalendarView.OnDateChangeListener {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

        }
    }

    public void getReportRides(LocalDateTime from, LocalDateTime to, DriveReportAdapter adapter, View view ) {
        final ArrayList<DriveReport> reports = new ArrayList<DriveReport>();
//        ArrayList<Drive> rides = Mockup.getDrives();
        final ArrayList<RideDTO> rides = new ArrayList<>();
        driverRepository.getDriverRides(new retrofit2.Callback<PaginatedResponse<RideDTO>>() {
            @Override
            public void onResponse(Call<PaginatedResponse<RideDTO>> call, Response<PaginatedResponse<RideDTO>> response) {
                for(RideDTO r: response.body().getResults()){
                    if ( r.getStatus().equals("FINSHED")){
                        rides.add(r);
                    }
                }
                ridesSum = 0;
                kmSum = 0;
                moneySum = 0;
                int i = 0;
                for (LocalDateTime date = from; date.isBefore(to); date = date.plusDays(1)) {
                    DriveReport dr = new DriveReport(date, 0, 0, 0);
                    while (i < rides.size()) {
                        LocalDateTime ldt = LocalDateTime.parse(rides.get(i).getStartTime());
                        if (date.toLocalDate().equals(ldt.toLocalDate())) {
                            dr.rides += 1;
                            ridesSum += 1;
                            dr.mileage += rides.get(i).getMileage();
                            kmSum += rides.get(i).getMileage();
                            dr.spent += rides.get(i).getTotalCost();
                            moneySum += rides.get(i).getTotalCost();
                            i++;
                        } else {
                            if (reports.isEmpty()) {
                                i++;
                                if (i == rides.size()) {
                                    i = 0;
                                    break;
                                }
                            } else
                                break;
                        }

                    }
                    reports.add(dr);
                }
                DecimalFormat format = new DecimalFormat("#.00");
                averageRides = Double.parseDouble(format.format((double) ridesSum / reports.size()));
                averageKm = Double.parseDouble(format.format((double) kmSum / reports.size()));
                averageMoney = Double.parseDouble(format.format((double) moneySum / reports.size()));



                TextView totalRides = view.findViewById(R.id.totalRides);
                TextView totalKm = view.findViewById(R.id.totalKm);
                TextView totalMoney = view.findViewById(R.id.totalSpent);
                TextView averageRidesText = view.findViewById(R.id.averageRides);
                TextView averageKmText = view.findViewById(R.id.averageKm);
                TextView averageMoneyText = view.findViewById(R.id.averageSpent);

                adapter.items = items;
                adapter.notifyDataSetChanged();
                totalRides.setText(String.valueOf(ridesSum));
                totalKm.setText(String.valueOf(kmSum));
                totalMoney.setText(String.valueOf(moneySum));
                averageRidesText.setText(String.valueOf(averageRides));
                averageKmText.setText(String.valueOf(averageKm));
                averageMoneyText.setText(String.valueOf(averageMoney));

                adapter.notifyDataSetChanged();
                items = reports;

            }


            @Override
            public void onFailure(Call<PaginatedResponse<RideDTO>> call, Throwable t) {

            }
        }, prefs.getString("userId", null));
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}