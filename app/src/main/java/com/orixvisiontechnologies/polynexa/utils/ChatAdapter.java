package com.orixvisiontechnologies.polynexa.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.orixvisiontechnologies.polynexa.R;
import com.orixvisiontechnologies.polynexa.model.ChatMessage;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    public interface OnChatClickListener {
        void onChatClick(ChatMessage message, int position);
    }

    private final List<ChatMessage> messageList;
    private OnChatClickListener clickListener;

    public ChatAdapter(List<ChatMessage> messageList) {
        this.messageList = messageList;
    }

    public void setOnChatClickListener(OnChatClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public void updateList(List<ChatMessage> newList) {
        messageList.clear();
        if (newList != null) {
            messageList.addAll(newList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_chart_id, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMessage item = messageList.get(position);

        holder.tvName.setText(item.getName());
        holder.tvMessage.setText(item.getMessage());
        holder.tvTime.setText(item.getTime());

        if (item.getAvatarRes() != 0) {
            holder.imgAvatar.setImageResource(item.getAvatarRes());
        } else {
            holder.imgAvatar.setImageResource(R.drawable.ic_default_avatar);
        }

        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (clickListener != null && pos != RecyclerView.NO_POSITION) {
                clickListener.onChatClick(messageList.get(pos), pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final CircleImageView imgAvatar;
        final TextView tvName;
        final TextView tvMessage;
        final TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvName = itemView.findViewById(R.id.tvName);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}