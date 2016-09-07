package com.id.maris.suittest.fragment;

import android.app.Activity;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.id.maris.suittest.R;
import com.id.maris.suittest.adapter.EventAdapter;
import com.id.maris.suittest.model.EventModel;
import com.id.maris.suittest.viewholder.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class EventFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private EventAdapter eventAdapter;
    private List<EventModel> eventModels;


    public static EventFragment newInstance(){
        return new EventFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_event, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null));

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("choosedEvent", eventModels.get(position).getName());
                        getActivity().setResult(Activity.RESULT_OK, returnIntent);
                        getActivity().finish();

                    }
                })
        );

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadData();
    }

    @Override
    public void onDestroy(){

        super.onDestroy();
    }

    public void loadData(){
        callingData();

    }

    public void callingData(){


        //Dummy data
        eventModels = new ArrayList<>();
        for(int i=0; i < 4; i++){
            EventModel model = new EventModel();
            model.setName("Event " + (i+1));
            model.setDate("12-0" + (i+1) + "-2016");
            eventModels.add(model);
        }

        eventAdapter = new EventAdapter(getContext(), eventModels);
        mRecyclerView.setAdapter(eventAdapter);


    }


    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;
        private GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final OnItemClickListener listener){
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent e) {
            View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
