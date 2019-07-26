package project.kitchen.itc9.fragmentactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */

public class SecondFragment extends Fragment {

    TextView textView;
    public SecondFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_second, container, false);
        textView = view.findViewById(R.id.passed_text);
        textView.setVisibility(View.GONE);
        return view;
    }

    public void updateInfo(String name){
        textView.setText(name);
        textView.setVisibility(View.VISIBLE);
    }
}
