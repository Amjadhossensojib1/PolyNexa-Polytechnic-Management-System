
package com.orixvisiontechnologies.polynexa.ui.studenthomeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.orixvisiontechnologies.polynexa.R;

import java.util.ArrayList;

public class StudentHomeFragment extends Fragment {

    private ImageSlider imageSlider;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_student_home,
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

        // Initialize Image Slider
        imageSlider = view.findViewById(R.id.image_slider);

        setupImageSlider();
    }

    private void setupImageSlider() {
        if (imageSlider == null) {
            return;
        }

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTS2vKL_ddq8BYiB79liojK2q-rkhK7C49_8Q&s",
                ScaleTypes.FIT
        ));

        slideModels.add(new SlideModel(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKQ__P5hDqP9u9EmVbjmSd63IgLfGxrUUcOA&s",
                ScaleTypes.FIT
        ));

        slideModels.add(new SlideModel(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSN72y5xQWBMCNTQJmZ1KXjW9KPgKEyo3zdmQ&s",
                ScaleTypes.FIT
        ));

        slideModels.add(new SlideModel(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRz0Fw8l1RhghVYwFRgyQnddtyKG4hlHrFD5Q&s",
                ScaleTypes.FIT
        ));

        slideModels.add(new SlideModel(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTCwn3jLBXrqQlmRkCGKNSU688XUiNKdsuKkg&s",
                ScaleTypes.FIT
        ));

        imageSlider.setImageList(slideModels);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        imageSlider = null;
    }
}
