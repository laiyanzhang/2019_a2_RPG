package com.example.game;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SkillFragment} interface
 * to handle interaction events.
 * Use the {@link SkillFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textView1,textView2,
            textView3,textView4,textView5,
            textView6, textView7,textView8,textView9;
    private SQLiteDatabase db;
    private CreateActivity.MyHelper myHelper;
    private ImageView imageView1,imageView2,imageView3;
    private ProgressBar progressBar1,progressBar2,progressBar3;


    public SkillFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SkillFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SkillFragment newInstance(String param1, String param2) {
        SkillFragment fragment = new SkillFragment();
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_skill, container, false);
        myHelper=new CreateActivity.MyHelper(getActivity());
        progressBar1=view.findViewById(R.id.pb_1);
        progressBar2=view.findViewById(R.id.pb_2);
        progressBar3=view.findViewById(R.id.pb_3);
        imageView1=view.findViewById(R.id.iv_1);
        imageView2=view.findViewById(R.id.iv_2);
        imageView3=view.findViewById(R.id.iv_3);
        textView1=view.findViewById(R.id.tv_1);
        textView2=view.findViewById(R.id.tv_2);
        textView3=view.findViewById(R.id.tv_3);
        textView4=view.findViewById(R.id.tv_4);
        textView5=view.findViewById(R.id.tv_5);
        textView6=view.findViewById(R.id.tv_6);
        textView7=view.findViewById(R.id.tv_7);
        textView8=view.findViewById(R.id.tv_8);
        textView9=view.findViewById(R.id.tv_9);
        db=myHelper.getReadableDatabase();
        Cursor cursor1=db.query("jineng",null,null,null,null,null,null);
        cursor1.moveToFirst();
        progressBar1.setProgress(cursor1.getInt(4)*100/1000);
        imageView1.setBackgroundResource(cursor1.getInt(1));
        textView1.setText(String.valueOf(cursor1.getInt(2)));
        textView2.setText(String.valueOf(cursor1.getString(3)));
        textView3.setText(String.valueOf(cursor1.getInt(4)));
        cursor1.moveToNext();
        progressBar2.setProgress(cursor1.getInt(4)*100/1000);
        imageView2.setBackgroundResource(cursor1.getInt(1));
        textView4.setText(String.valueOf(cursor1.getInt(2)));
        textView5.setText(String.valueOf(cursor1.getString(3)));
        textView6.setText(String.valueOf(cursor1.getInt(4)));
        cursor1.moveToNext();
        progressBar3.setProgress(cursor1.getInt(4)*100/1000);
        imageView3.setBackgroundResource(cursor1.getInt(1));
        textView7.setText(String.valueOf(cursor1.getInt(2)));
        textView8.setText(String.valueOf(cursor1.getString(3)));
        textView9.setText(String.valueOf(cursor1.getInt(4)));
        cursor1.close();
        db.close();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
