package id.my.avmmartin.goldexperience.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import id.my.avmmartin.goldexperience.R;

public class RegisterConfirmDialog extends DialogFragment {
    public interface Listener {
        void btnCancelOnClick(RegisterConfirmDialog dialog);
        void btnSubmitOnClick(RegisterConfirmDialog dialog);
    }

    private TextView etCode;

    private RegisterConfirmDialog.Listener listener;

    // overridden method

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (RegisterConfirmDialog.Listener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                getActivity().toString() + " must implement RegisterConfirmDialog.Listener"
            );
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_register_confirm, null);

        etCode = view.findViewById(R.id.registerconfirm_et_code);

        builder.setView(view);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                listener.btnCancelOnClick(RegisterConfirmDialog.this);
            }
        });
        builder.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.btnSubmitOnClick(RegisterConfirmDialog.this);
            }
        });

        return builder.create();
    }

    // getter

    public TextView getEtCode() {
        return etCode;
    }
}
