package anand.productreviews;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;


/**
 * Created by ANIL BALAJI on 20-01-2016.
 */
public class Choice extends DialogFragment {
    String selection;
    final CharSequence[] items={"1","3","5","10"};
    public Dialog onCreateDialog(Bundle savedInstaceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose required number of reviews").setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {



            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                switch (arg1){
                    case 0:
                        selection=(String)items[arg1];
                        break;
                    case 1:
                        selection=(String)items[arg1];
                        break;
                    case 2:
                        selection=(String)items[arg1];
                        break;
                    case 3:
                        selection=(String)items[arg1];
                        break;
                    case 4:
                        selection=(String)items[arg1];
                        break;
                    case 5:
                        selection=(String)items[arg1];
                        break;
                    case 6:
                        selection=(String)items[arg1];
                        break;
                    case 7:
                        selection=(String)items[arg1];
                        break;
                    case 8:
                        selection=(String)items[arg1];
                        break;
                    case 9:
                        selection=(String)items[arg1];
                        break;


                }
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Choice selected is: "+selection,Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }



}
