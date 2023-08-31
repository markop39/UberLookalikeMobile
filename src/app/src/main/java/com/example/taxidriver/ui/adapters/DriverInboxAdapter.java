package com.example.taxidriver.ui.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.taxidriver.R;
import com.example.taxidriver.data.dto.MessageDTO;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverInboxAdapter extends BaseAdapter {
    private final Activity activity ;
    private final List<MessageDTO> messageDTOS;
    private final boolean panic;
    private final boolean ride;
    private final boolean support;

    public DriverInboxAdapter(Activity activity, List<MessageDTO> messageDTOS, boolean panic, boolean ride, boolean support) {
        this.activity = activity;
        this.messageDTOS = messageDTOS;
        this.panic = panic;
        this.ride = ride;
        this.support = support;
    }

    @Override
    public int getCount() {
        return messageDTOS.size();
    }

    @Override
    public MessageDTO getItem(int i) {
        return messageDTOS.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final Map<String, Object> colors = new HashMap<>();
        colors.put("PANIC", activity.getResources().getColor(R.color.purple_500));
        colors.put("RIDE", activity.getResources().getColor(R.color.teal_200));
        colors.put("SUPPORT", activity.getResources().getColor(R.color.purple_200));
        MessageDTO messageDTO = messageDTOS.get(i);
        if ( (messageDTO.getType().equals("SUPPORT") && !support)
                || (messageDTO.getType().equals("PANIC") && !panic)
                || (messageDTO.getType().equals("RIDE") && !ride)
        ){
            return new View(activity);
        }
        @SuppressLint("ViewHolder")
        View view = activity.getLayoutInflater().inflate(R.layout.chat_element, null);
        TextView sender = view.findViewById(R.id.sender);
        TextView content = view.findViewById(R.id.messageContent);
        TextView timeOfSending = view.findViewById(R.id.timeOfSending);
        TextView dateOfSending = view.findViewById(R.id.dateOfSending);
        dateOfSending.setText(messageDTO.getTimeOfSending().split("T")[0]);
        sender.setText(messageDTO.getType());
        view.setBackgroundColor((Integer) colors.get(messageDTO.getType()));
        content.setText(messageDTO.getMessage());
        timeOfSending.setText(messageDTO.getTimeOfSending().split("T")[1].substring(0, 5));

        return view;
    }
}
