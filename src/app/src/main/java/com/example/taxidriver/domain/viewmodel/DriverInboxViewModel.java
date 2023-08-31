package com.example.taxidriver.domain.viewmodel;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.dto.MessageDTO;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.repository.DriverRepository;
import com.example.taxidriver.data.repository.PassengerRepository;
import com.example.taxidriver.data.repository.UserRepository;
import com.example.taxidriver.domain.model.User;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverInboxViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final PassengerRepository passengerRepository;

    private MutableLiveData<List<MessageDTO>> messages;

    public DriverInboxViewModel() {
        userRepository = new UserRepository();
        passengerRepository = new PassengerRepository();
        messages = new MutableLiveData<>();
    }
    public void fetchMessages(String id) {
        userRepository.getMessages(new Callback<PaginatedResponse<MessageDTO>>() {
            @Override
            public void onResponse(Call<PaginatedResponse<MessageDTO>> call, Response<PaginatedResponse<MessageDTO>> response) {
                PaginatedResponse<MessageDTO> paginatedResponse = response.body();
                assert paginatedResponse != null;
                Toast.makeText(TaxiDriver.getAppContext(), "Messages load, success", Toast.LENGTH_SHORT).show();
                List<MessageDTO> messageDTOS = paginatedResponse.getResults();
                messageDTOS.sort((m1, m2) -> {
                    LocalDateTime time1 = LocalDateTime.parse(m1.getTimeOfSending());
                    LocalDateTime time2 = LocalDateTime.parse(m2.getTimeOfSending());
                    return time1.compareTo(time2);
                });
                messages.postValue(messageDTOS);
            }

            @Override
            public void onFailure(Call<PaginatedResponse<MessageDTO>> call, Throwable t) {
                Toast.makeText(TaxiDriver.getAppContext(), "Messages load, failure", Toast.LENGTH_SHORT).show();
            }
        }, id);
    }

    public LiveData<List<MessageDTO>> getMessages() {
        return messages;
    }
}
