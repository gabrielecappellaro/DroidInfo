package it.k4ppaj.droidinfo.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import it.k4ppaj.droidinfo.R;
import it.k4ppaj.droidinfo.adapter.ClassicAdapter;
import it.k4ppaj.droidinfo.helper.DeviceHelper;
import it.k4ppaj.droidinfo.helper.SoCHelper;

public class SoCFragment extends Fragment {

    private Activity activity;
    private Context context;
    private String GPU_VENDOR = "GPU_VENDOR";
    private String GPU_RENDERER = "GPU_RENDERER";

    public SoCFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_soc, container, false);
        ListView listView = layoutView.findViewById(R.id.listViewSoC);

        SharedPreferences sharedPreferences = activity.getSharedPreferences("DroidInfo", Context.MODE_PRIVATE);

        String[] stringInformation = new String[] {
                getString(R.string.CPUModel),
                getString(R.string.CPUCores),
                getString(R.string.CPUFreq),
                getString(R.string.GPUVendor),
                getString(R.string.GPURenderer),
                getString(R.string.OpenGLVersion)
        };

        String[] stringValues = new String[] {
                SoCHelper.getCPUModel(),
                SoCHelper.getCPUCores(),
                SoCHelper.getCPUFreq(),
                sharedPreferences.getString(GPU_VENDOR, getString(R.string.Unknown)),
                sharedPreferences.getString(GPU_RENDERER, getString(R.string.Unknown)),
                SoCHelper.getOpenGLVersion(context)
        };

        ClassicAdapter classicAdapter = new ClassicAdapter(activity, stringInformation, stringValues);
        listView.setAdapter(classicAdapter);

        return layoutView;
    }
}
