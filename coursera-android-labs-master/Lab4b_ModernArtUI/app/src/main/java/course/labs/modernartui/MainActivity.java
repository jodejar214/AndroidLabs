package course.labs.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.SeekBar;

public class MainActivity extends Activity {

    DialogFragment mDialog;
    TextView top_left;
    TextView bottom_left;
    TextView top_right;
    TextView mid_right;
    TextView bottom_right;
    SeekBar col_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        top_left = (TextView) findViewById(R.id.top_left);
        bottom_left = (TextView) findViewById(R.id.bottom_left);
        top_right = (TextView) findViewById(R.id.top_right);
        mid_right = (TextView) findViewById(R.id.mid_right);
        bottom_right = (TextView) findViewById(R.id.bottom_right);
        col_change = (SeekBar) findViewById(R.id.col_change);

        changeColor();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.info:
                //show dialog
                mDialog = AlertDialogFragment.newInstance();
                mDialog.show(getFragmentManager(), "Info");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //change color according to seekbar progress
    private void changeColor() {
        col_change.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //get background colors of textviews that are not white
            ColorDrawable tlc = (ColorDrawable) top_left.getBackground();
            ColorDrawable blc = (ColorDrawable) bottom_left.getBackground();
            ColorDrawable trc = (ColorDrawable) top_right.getBackground();
            ColorDrawable brc = (ColorDrawable) bottom_right.getBackground();

            //change the background colors of the textviews that are not originally white
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress < 15){
                    top_left.setBackground(null);
                    bottom_left.setBackground(null);
                    bottom_right.setBackground(null);
                    top_right.setBackground(null);

                    top_left.setBackground(tlc);
                    bottom_left.setBackground(blc);
                    bottom_right.setBackground(brc);
                    top_right.setBackground(trc);
                }
                else if(progress>=15 && progress<30){
                    top_left.setBackground(null);
                    bottom_left.setBackground(null);
                    bottom_right.setBackground(null);
                    top_right.setBackground(null);

                    top_left.setBackgroundColor(Color.rgb(255, 111, 0));
                    bottom_left.setBackgroundColor(Color.RED);
                    bottom_right.setBackgroundColor(Color.BLUE);
                    top_right.setBackgroundColor(Color.MAGENTA);
                }
                else if(progress>=30 && progress<45){
                    top_left.setBackground(null);
                    bottom_left.setBackground(null);
                    bottom_right.setBackground(null);
                    top_right.setBackground(null);

                    top_left.setBackgroundColor(Color.CYAN);
                    bottom_left.setBackgroundColor(Color.rgb(255,205,0));
                    bottom_right.setBackgroundColor(Color.rgb(87,152,12));
                    top_right.setBackgroundColor(Color.rgb(255,117,200));
                }
                else if(progress>=45 && progress<60){
                    top_left.setBackground(null);
                    bottom_left.setBackground(null);
                    bottom_right.setBackground(null);
                    top_right.setBackground(null);

                    top_left.setBackgroundColor(Color.YELLOW);
                    bottom_left.setBackgroundColor(Color.rgb(95,38,88));
                    bottom_right.setBackgroundColor(Color.rgb(0,179,255));
                    top_right.setBackgroundColor(Color.rgb(196,255,0));
                }
                else if(progress>=60 && progress<75){
                    top_left.setBackground(null);
                    bottom_left.setBackground(null);
                    bottom_right.setBackground(null);
                    top_right.setBackground(null);

                    top_left.setBackground(blc);
                    bottom_left.setBackground(tlc);
                    bottom_right.setBackground(trc);
                    top_right.setBackground(brc);
                }
                else if (progress>=75 && progress<90){
                    top_left.setBackground(null);
                    bottom_left.setBackground(null);
                    bottom_right.setBackground(null);
                    top_right.setBackground(null);

                    top_left.setBackgroundColor(Color.rgb(118, 61, 95));
                    bottom_left.setBackgroundColor(Color.rgb(0,247,255));
                    bottom_right.setBackgroundColor(Color.rgb(9,135,85));
                    top_right.setBackgroundColor(Color.rgb(255,102,251));
                }
                else{
                    top_left.setBackground(null);
                    bottom_left.setBackground(null);
                    bottom_right.setBackground(null);
                    top_right.setBackground(null);

                    top_left.setBackgroundColor(Color.rgb(40,255,119));
                    bottom_left.setBackgroundColor(Color.rgb(255,240,97));
                    bottom_right.setBackgroundColor(Color.rgb(255,190,115));
                    top_right.setBackgroundColor(Color.rgb(6,255,156));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    // Class that creates the AlertDialog
    public static class AlertDialogFragment extends DialogFragment {

        public static AlertDialogFragment newInstance() {
            return new AlertDialogFragment();
        }

        // Build AlertDialog using AlertDialog.Builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage("Inspired by the works of artists such as Piet Mondrian and Ben Nicholson.\n\nClick below to learn more!")

                    .setCancelable(false)

                    //Clicking this button goes to a website
                    .setPositiveButton("Visit MOMA",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog, int id) {
                                    Intent site = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                                    startActivity(site);
                                }
                            })

                    //Stay on app and close dialog
                    .setNegativeButton("Not Now",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dismiss();
                                }
                            })

                    .create();
        }
    }
}
