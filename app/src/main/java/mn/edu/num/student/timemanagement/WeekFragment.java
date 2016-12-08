package mn.edu.num.student.timemanagement;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class WeekFragment extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextView textView;

    public WeekFragment() {
        // Required empty public constructor
    }

    public static WeekFragment newInstance(String param1, String param2) {
        WeekFragment fragment = new WeekFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View myView = inflater.inflate(R.layout.fragment_week, container, false);
        TableLayout tableLayout =(TableLayout) myView.findViewById(R.id.tl_week);
        int len = tableLayout.getChildCount();

        for(int i = 1; i < len; i++){
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            int rowLen = tableRow.getChildCount();
            for(int j = 1; j < rowLen; j++){
                final TextView text = (TextView) tableRow.getChildAt(j);
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        text.setBackgroundColor(Color.CYAN);
                        text.setTextColor(Color.WHITE);
                        text.setText("+");
                    }
                });

                text.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        return false;
                    }
                });
            }
        }
        return myView;
    }

    @Nullable
    public View getView(int position, View converView, ViewGroup parent) {

        return converView;
    }
}
