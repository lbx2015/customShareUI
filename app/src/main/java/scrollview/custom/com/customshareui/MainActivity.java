package scrollview.custom.com.customshareui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShare(final View view) {
        showAlertDialog(view);
    }


    private void showAlertDialog(View v) {
        ShareDialog d = new ShareDialog(this);
        d.setTitle("分享到...");
        d.openDialog();
    }
}
