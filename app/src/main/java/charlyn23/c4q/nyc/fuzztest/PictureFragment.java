package charlyn23.c4q.nyc.fuzztest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by charlynbuchanan on 10/3/15.
 */
public class PictureFragment extends Fragment {
    public static View rootView;
    ListView picListView;
    ArrayList<JSONObject> items;
    JSONObject item;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<JSONObject> items = DataGetter.getItems();

        rootView = inflater.inflate(R.layout.fragment_picture, container, false);
        picListView = (ListView)rootView.findViewById(R.id.picListView);
        PictureContentAdapter adapter = new PictureContentAdapter(getActivity(), R.layout.pic_list, items);
        picListView.setAdapter(adapter);


        return rootView;
    }
}
