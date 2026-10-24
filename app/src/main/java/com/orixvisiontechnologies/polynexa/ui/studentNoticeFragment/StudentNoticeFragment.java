
package com.orixvisiontechnologies.polynexa.ui.studentNoticeFragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orixvisiontechnologies.polynexa.R;
import com.orixvisiontechnologies.polynexa.model.Notice;
import com.orixvisiontechnologies.polynexa.utils.NoticeAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentNoticeFragment extends Fragment {

    private RecyclerView rvNotices;
    private ProgressBar progressNotice;
    private TextView tvEmptyNotice;
    private NoticeAdapter adapter;

    public StudentNoticeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_student_notice,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        final int initialLeft = view.getPaddingLeft();
        final int initialTop = view.getPaddingTop();
        final int initialRight = view.getPaddingRight();
        final int initialBottom = view.getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
            Insets systemBars = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
            );

            v.setPadding(
                    initialLeft + systemBars.left,
                    initialTop + systemBars.top,
                    initialRight + systemBars.right,
                    initialBottom + systemBars.bottom
            );

            return insets;
        });

        ViewCompat.requestApplyInsets(view);

        rvNotices = view.findViewById(R.id.rvNotices);
        progressNotice = view.findViewById(R.id.progressNotice);
        tvEmptyNotice = view.findViewById(R.id.tvEmptyNotice);

        adapter = new NoticeAdapter(this::openPdf);

        rvNotices.setLayoutManager(
                new LinearLayoutManager(requireContext())
        );
        rvNotices.setAdapter(adapter);

        loadNotices();
    }

    private void loadNotices() {
        showLoading(true);

        List<Notice> list = new ArrayList<>();

        list.add(new Notice(
                "1",
                "Digital Nepal School",
                "6 Chaitra, 2080",
                "Notice Regarding Upcoming Parent-Teacher Meeting",
                "Informing parents about an upcoming parent-teacher meeting to discuss students' academic progress and other important matters.",
                "2024-04-08",
                "11 AM",
                "https://example.com/sample.pdf"
        ));

        list.add(new Notice(
                "2",
                "Digital Nepal School",
                "4 Baishakh, 2081",
                "Notice Regarding School Holiday",
                "Notifying students, parents, and staff about a scheduled school holiday due to Ram Navami!",
                "2024-04-17",
                null,
                null
        ));

        list.add(new Notice(
                "3",
                "Digital Nepal School",
                "11 Ashwin, 2080",
                "Indra Jatra 2023",
                "Our office will be closed in observance of the Indra Jatra Festival.",
                null,
                null,
                null
        ));

        showNotices(list);
    }

    private void showNotices(List<Notice> list) {
        showLoading(false);

        adapter.submitList(list);

        if (tvEmptyNotice != null) {
            tvEmptyNotice.setVisibility(
                    list == null || list.isEmpty()
                            ? View.VISIBLE
                            : View.GONE
            );
        }
    }

    private void showLoading(boolean loading) {
        if (progressNotice != null) {
            progressNotice.setVisibility(
                    loading ? View.VISIBLE : View.GONE
            );
        }

        if (loading && tvEmptyNotice != null) {
            tvEmptyNotice.setVisibility(View.GONE);
        }
    }

    private void openPdf(Notice notice) {
        if (notice == null || notice.getPdfUrl() == null
                || notice.getPdfUrl().trim().isEmpty()) {
            Toast.makeText(
                    requireContext(),
                    "PDF link available nei",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        try {
            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(notice.getPdfUrl())
            );
            startActivity(intent);

        } catch (ActivityNotFoundException e) {
            Toast.makeText(
                    requireContext(),
                    "PDF open korar app pawa jayni",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        rvNotices = null;
        progressNotice = null;
        tvEmptyNotice = null;
        adapter = null;
    }
}
