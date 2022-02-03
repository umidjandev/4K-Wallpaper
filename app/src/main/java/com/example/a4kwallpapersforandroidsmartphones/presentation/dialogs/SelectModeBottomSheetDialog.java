package com.example.a4kwallpapersforandroidsmartphones.presentation.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.a4kwallpapersforandroidsmartphones.databinding.FragmentSelectModeBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SelectModeBottomSheetDialog extends BottomSheetDialogFragment {

    private FragmentSelectModeBinding binding;
    private Callback callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentSelectModeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            callback = (Callback) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.modeHome.setOnClickListener(v -> {
            if (callback != null) {
                callback.modeSelected(Mode.HOME);
                dismiss();
            }
        });
        binding.modeLock.setOnClickListener(v -> {
            if (callback != null) {
                callback.modeSelected(Mode.LOCK);
                dismiss();
            }
        });
        binding.modeHomeLock.setOnClickListener(v -> {
            if (callback != null) {
                callback.modeSelected(Mode.HOME_LOCK);
                dismiss();
            }
        });
    }

    @FunctionalInterface
    public interface Callback {
        void modeSelected(Mode mode);
    }

    public enum Mode {
        HOME,
        LOCK,
        HOME_LOCK,
    }
}
