package charlyn23.c4q.nyc.fuzztest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by charlynbuchanan on 10/3/15.
 */
public class TextFragment extends Fragment {

    public static View rootView;
    ListView textListView;
    ArrayList<JSONObject> items;
    JSONObject item;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<JSONObject> items = DataGetter.getItems();

        rootView = inflater.inflate(R.layout.fragment_text, container, false);
        textListView = (ListView)rootView.findViewById(R.id.textListView);
        TextContentAdapter adapter = new TextContentAdapter(getActivity(), R.layout.pic_and_text_list, items );
        textListView.setAdapter(adapter);


        return rootView;
    }
}
