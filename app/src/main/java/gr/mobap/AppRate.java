package gr.mobap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AppRate extends Activity {

    private final static String APP_TITLE = "Βουλή των Ελλήνων";
    private final static String APP_PNAME = "gr.mobap";

    private final static int DAYS_UNTIL_PROMPT = 3;
    private final static int LAUNCHES_UNTIL_PROMPT = 7;

    public static void app_launched(Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("apprater", 0);
        if (prefs.getBoolean("dontshowagain", false)) {
            return;
        }

        SharedPreferences.Editor editor = prefs.edit();

        // Increment launch counter
        long launch_count = prefs.getLong("launch_count", 0) + 1;
        editor.putLong("launch_count", launch_count);

        // Get date of first launch
        Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }

        // Wait at least n days before opening
        if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
            if (System.currentTimeMillis() >= date_firstLaunch
                    + (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
                showRateDialog(mContext, editor);
            }
        }

        editor.apply();
    }

    public static void showRateDialog(final Context mContext,
                                      final SharedPreferences.Editor editor) {
        final Dialog dialog = new Dialog(mContext);
        dialog.setTitle("Βαθμολογήστε την εφαρμογή!");
        LinearLayout ll = new LinearLayout(mContext);
        ll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 2, 2, 2);


        TextView tv = new TextView(mContext);
        tv.setText("Παρακαλώ βαθμολογήστε την εφαρμογή για τη\n"
                + APP_TITLE + "\n"
                + "Ευχαριστώ για την συμπαράσταση σας!");
        tv.setWidth(240);
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(14, 14, 14, 14);
        ll.addView(tv);

        Button b1 = new Button(mContext, null, R.style.ButtonStyle);
        b1.setText("Πάμε στο Google Play");
        b1.setBackgroundResource(R.drawable.action_bar);
        b1.setTextColor(Color.rgb(255, 255, 255));
        b1.setLayoutParams(params);
        b1.setPadding(12, 12, 12, 12);
        b1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse("market://details?id=" + APP_PNAME)));
                dialog.dismiss();
            }
        });
        ll.addView(b1);

        Button b2 = new Button(mContext, null, R.style.ButtonStyle);
        b2.setText("Θύμισε το μου αργότερα!");
        b2.setBackgroundResource(R.drawable.action_bar);
        b2.setTextColor(Color.rgb(255, 255, 255));
        b2.setLayoutParams(params);
        b2.setPadding(12, 12, 12, 12);
        b2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ll.addView(b2);

        Button b3 = new Button(mContext, null, R.style.ButtonStyle);
        b3.setText("Όχι, ευχαριστώ!");
        b3.setBackgroundResource(R.drawable.action_bar);
        b3.setTextColor(Color.rgb(255, 255, 255));
        b3.setLayoutParams(params);
        b3.setPadding(12, 12, 12, 12);
        b3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (editor != null) {
                    editor.putBoolean("dontshowagain", true);
                    editor.commit();
                }
                dialog.dismiss();
            }
        });
        ll.addView(b3);

        dialog.setContentView(ll);
        dialog.show();
    }
}
// http://androidsnippets.com/prompt-engaged-users-to-rate-your-app-in-the-android-market-appirater