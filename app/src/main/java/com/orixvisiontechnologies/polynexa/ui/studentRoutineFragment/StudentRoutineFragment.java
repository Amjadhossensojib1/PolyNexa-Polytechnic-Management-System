
package com.orixvisiontechnologies.polynexa.ui.studentRoutineFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.orixvisiontechnologies.polynexa.R;

public class StudentRoutineFragment extends Fragment {

    private static final int COLOR_SELECTED_BG = Color.parseColor("#3B6FB6");
    private static final int COLOR_NORMAL_BG = Color.parseColor("#FFFFFF");

    private static final int COLOR_SELECTED_TEXT = Color.parseColor("#FFFFFF");
    private static final int COLOR_NORMAL_TEXT = Color.parseColor("#111111");
    private static final int COLOR_NORMAL_WEEKDAY = Color.parseColor("#555555");

    private LinearLayout dateContainer;

    public StudentRoutineFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_student_routine,
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

        dateContainer = view.findViewById(R.id.dateContainer);

        if (dateContainer != null && dateContainer.getChildCount() > 0) {

            for (int i = 0; i < dateContainer.getChildCount(); i++) {
                final int index = i;

                dateContainer.getChildAt(i).setOnClickListener(
                        v -> selectDate(index)
                );
            }

            selectDate(0);
        }
    }

    private void selectDate(int selectedIndex) {

        if (dateContainer == null) {
            return;
        }

        for (int i = 0; i < dateContainer.getChildCount(); i++) {

            View child = dateContainer.getChildAt(i);

            if (!(child instanceof MaterialCardView)) {
                continue;
            }

            MaterialCardView card = (MaterialCardView) child;

            if (!(card.getChildAt(0) instanceof LinearLayout)) {
                continue;
            }

            LinearLayout inner = (LinearLayout) card.getChildAt(0);

            if (inner.getChildCount() < 3) {
                continue;
            }

            if (!(inner.getChildAt(0) instanceof TextView)
                    || !(inner.getChildAt(1) instanceof TextView)
                    || !(inner.getChildAt(2) instanceof TextView)) {
                continue;
            }

            TextView tvMonth = (TextView) inner.getChildAt(0);
            TextView tvDay = (TextView) inner.getChildAt(1);
            TextView tvWeekday = (TextView) inner.getChildAt(2);

            if (i == selectedIndex) {

                card.setCardBackgroundColor(COLOR_SELECTED_BG);
                tvMonth.setTextColor(COLOR_SELECTED_TEXT);
                tvDay.setTextColor(COLOR_SELECTED_TEXT);
                tvWeekday.setTextColor(COLOR_SELECTED_TEXT);

            } else {

                card.setCardBackgroundColor(COLOR_NORMAL_BG);
                tvMonth.setTextColor(COLOR_NORMAL_TEXT);
                tvDay.setTextColor(COLOR_NORMAL_TEXT);
                tvWeekday.setTextColor(COLOR_NORMAL_WEEKDAY);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dateContainer = null;
    }
}
