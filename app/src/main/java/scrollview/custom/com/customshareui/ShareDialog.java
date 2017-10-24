package scrollview.custom.com.customshareui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

/**
 * Created by zw.zhang on 2017/10/24.
 */

public class ShareDialog extends BottomSheetDialog {
    Activity a;

    public ShareDialog(@NonNull Activity context) {
        super(context);
        a = context;
        setContentView(R.layout.share_dialog);
    }

    public ShareDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }

    protected ShareDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void openDialog() {
        //set title
        setTitle("Share to...");
        GridView gridView = findViewById(R.id.share_grid_view);

        //create share intent
        final Intent intent = new Intent(Intent.ACTION_SEND, null);
        intent.putExtra(Intent.EXTRA_TEXT, "http://www.baidu.com");
        intent.setType("text/plain");

        //get PackageManger
        PackageManager pm = getContext().getPackageManager();
        List<ResolveInfo> launchables = pm.queryIntentActivities(intent, 0);

        final AppAdapter adapter = new AppAdapter(a, pm, launchables);

        //set adapter for the gridview
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ResolveInfo lauchable = adapter.getItem(position);
                ActivityInfo activityInfo = lauchable.activityInfo;
                ComponentName name = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                intent.setComponent(name);
                getContext().startActivity(intent);
            }
        });

        //show the dialog
        show();
    }
}
