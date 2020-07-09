package id.my.avmmartin.goldexperience.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.Plan;

public class DeletePlanDialog extends DialogFragment {
    public interface Listener {
        void btnSubmitOnClick(DeletePlanDialog dialog);
    }

    private DeletePlanDialog.Listener listener;
    private Plan plan;

    public void bindData(Plan plan) {
        this.plan = plan;
    }

    public Plan getPlan() {
        return plan;
    }

    // overridden method

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DeletePlanDialog.Listener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                getActivity().toString() + " must implement DeletePlanDialog.Listener"
            );
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage(getString(R.string.delete) + " " + plan.getName() + "?");
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.btnSubmitOnClick(DeletePlanDialog.this);
            }
        });

        return builder.create();
    }
}
