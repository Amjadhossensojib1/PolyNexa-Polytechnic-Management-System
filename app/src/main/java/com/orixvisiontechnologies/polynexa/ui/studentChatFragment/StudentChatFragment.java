package com.orixvisiontechnologies.polynexa.ui.studentChatFragment;

import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orixvisiontechnologies.polynexa.R;
import com.orixvisiontechnologies.polynexa.utils.ChatAdapter;
import com.orixvisiontechnologies.polynexa.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class StudentChatFragment extends Fragment {

    private RecyclerView recyclerDeletedMessages;
    private ChatAdapter adapter;
    private final List<ChatMessage> messageList = new ArrayList<>();

    public StudentChatFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_chat, container, false);

        setupToolbarInsets(view);

        recyclerDeletedMessages = view.findViewById(R.id.recyclerDeletedMessages);

        adapter = new ChatAdapter(messageList);
        recyclerDeletedMessages.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerDeletedMessages.setAdapter(adapter);

        adapter.setOnChatClickListener((message, position) ->
                Toast.makeText(requireContext(), message.getName(), Toast.LENGTH_SHORT).show());

        loadMessages();

        return view;
    }

    private void setupToolbarInsets(View root) {
        final View toolbar = root.findViewById(R.id.toolbar);

        final int originalHeight = toolbar.getLayoutParams().height;
        final int paddingLeft = toolbar.getPaddingLeft();
        final int paddingRight = toolbar.getPaddingRight();
        final int paddingBottom = toolbar.getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(root, (v, windowInsets) -> {
            Insets bars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());

            ViewGroup.LayoutParams lp = toolbar.getLayoutParams();
            lp.height = originalHeight + bars.top;
            toolbar.setLayoutParams(lp);
            toolbar.setPadding(paddingLeft, bars.top, paddingRight, paddingBottom);

            return windowInsets;
        });

        ViewCompat.requestApplyInsets(root);
    }

    private void loadMessages() {
        List<ChatMessage> list = new ArrayList<>();
        list.add(new ChatMessage("1", "Ryan", "Stop, you are not me", "08:05 PM"));
        list.add(new ChatMessage("2", "Sita Sharma", "Homework ta kobe submit korbo?", "07:42 PM"));
        list.add(new ChatMessage("3", "Ram Thapa", "Tomorrow class cancel hoyeche", "06:30 PM"));
        list.add(new ChatMessage("4", "Hari Karki", "Notes gulo pathiye dao", "05:15 PM"));

        adapter.updateList(list);
    }
}