package com.example.basicnavigationbase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class FlowStepFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        int flowStepNumber;

        //TODO STEP 5 - Get arguments from autogenerated clases.
        flowStepNumber = getArguments().getInt("flowStepNumber");
        //END STEP 5

        //TODO STEP 6  - Use type-safe arguments - remove previous line!
        if (flowStepNumber == 1) {
            return inflater.inflate(R.layout.flow_step_one_fragment, container, false);
        } else if (flowStepNumber == 2) {
            return inflater.inflate(R.layout.flow_step_two_fragment, container, false);
        } else {
            throw new IllegalStateException("Invalid step number");
        }
        //END STEP 6

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getView().findViewById(R.id.next_button).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.next_action));
    }
}
