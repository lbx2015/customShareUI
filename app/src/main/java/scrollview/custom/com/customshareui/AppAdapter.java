package scrollview.custom.com.customshareui;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zw.zhang on 2017/10/24.
 */

public class AppAdapter extends ArrayAdapter<ResolveInfo> {
    private PackageManager pm = null;
    private Activity a;

    public AppAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public AppAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public AppAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ResolveInfo[] objects) {
        super(context, resource, objects);
    }

    public AppAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull ResolveInfo[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public AppAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ResolveInfo> objects) {
        super(context, resource, objects);
    }
    public AppAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ResolveInfo> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    AppAdapter(Activity context, PackageManager pm, List<ResolveInfo> apps) {
        super(context, R.layout.row, apps);
        a = context;
        this.pm = pm;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = newView(parent);
        }

        bindView(position, convertView);

        return (convertView);
    }

    private View newView(ViewGroup parent) {
        return (a.getLayoutInflater().inflate(R.layout.row, parent, false));
    }

    private void bindView(int position, View row) {
        TextView label = (TextView) row.findViewById(R.id.label);

        label.setText(getItem(position).loadLabel(pm));

        ImageView icon = (ImageView) row.findViewById(R.id.icon);

        icon.setImageDrawable(getItem(position).loadIcon(pm));
    }
}
