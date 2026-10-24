package com.orixvisiontechnologies.polynexa.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.orixvisiontechnologies.polynexa.R;
import com.orixvisiontechnologies.polynexa.model.Notice;

public class NoticeAdapter extends ListAdapter<Notice, NoticeAdapter.NoticeViewHolder> {

    public interface OnPdfClickListener {
        void onPdfClick(Notice notice);
    }

    private final OnPdfClickListener pdfClickListener;

    public NoticeAdapter(OnPdfClickListener pdfClickListener) {
        super(DIFF_CALLBACK);
        this.pdfClickListener = pdfClickListener;
    }

    private static final DiffUtil.ItemCallback<Notice> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Notice>() {
                @Override
                public boolean areItemsTheSame(@NonNull Notice oldItem, @NonNull Notice newItem) {
                    return oldItem.getId() != null && oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Notice oldItem, @NonNull Notice newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_notice, parent, false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        Notice notice = getItem(position);

        holder.tvSchoolName.setText(notice.getSchoolName());
        holder.tvPostedDate.setText(notice.getPostedDate());
        holder.tvTitle.setText(notice.getTitle());
        holder.tvDescription.setText(notice.getDescription());

        if (notice.hasEventDate()) {
            holder.tvEventDate.setText("Date: " + notice.getEventDate());
            holder.tvEventDate.setVisibility(View.VISIBLE);
        } else {
            holder.tvEventDate.setVisibility(View.GONE);
        }

        if (notice.hasEventTime()) {
            holder.tvEventTime.setText("Time: " + notice.getEventTime());
            holder.tvEventTime.setVisibility(View.VISIBLE);
        } else {
            holder.tvEventTime.setVisibility(View.GONE);
        }


        if (notice.hasPdf()) {
            holder.cardPdf.setVisibility(View.VISIBLE);
            holder.cardPdf.setOnClickListener(v -> {
                if (pdfClickListener != null) {
                    pdfClickListener.onPdfClick(notice);
                }
            });
        } else {
            holder.cardPdf.setVisibility(View.GONE);
            holder.cardPdf.setOnClickListener(null);
        }
    }

    static class NoticeViewHolder extends RecyclerView.ViewHolder {
        final TextView tvSchoolName;
        final TextView tvPostedDate;
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvEventDate;
        final TextView tvEventTime;
        final MaterialCardView cardPdf;

        NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSchoolName = itemView.findViewById(R.id.tvSchoolName);
            tvPostedDate = itemView.findViewById(R.id.tvPostedDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvEventDate = itemView.findViewById(R.id.tvEventDate);
            tvEventTime = itemView.findViewById(R.id.tvEventTime);
            cardPdf = itemView.findViewById(R.id.cardPdf);
        }
    }
}
