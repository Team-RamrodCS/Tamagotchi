package cis.ramrodcs.tomagotcha.virtualpenguin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Dylan on 3/12/2015.
 */
public class SettingsDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Virtual Penguin");
        builder.setMessage(R.string.penguin_info_str);
        builder.setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //
                }
            });

        return builder.create();
    }
}
