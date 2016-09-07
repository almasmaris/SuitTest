package com.id.maris.suittest.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.id.maris.suittest.R;
import com.id.maris.suittest.adapter.GuestAdapter;
import com.id.maris.suittest.model.GuestModel;
import com.id.maris.suittest.service.ApiService;
import com.id.maris.suittest.service.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by almas on 06-Sep-16.
 */

public class GuestFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private GuestAdapter guestAdapter;
    private List<GuestModel> guestModels;
    private RecyclerView.LayoutManager mLayoutManager;


    public static GuestFragment newInstance(){
        return new GuestFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_guest, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_Guest);

        mLayoutManager = new GridLayoutManager(getContext(), 2);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("choosedGuest", guestModels.get(position).getName());
                        returnIntent.putExtra("birthdate", guestModels.get(position).getBirthdate());
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

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

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

        ApiService apiService = ServiceGenerator.createService(ApiService.class);
        Call<List<GuestModel>> call = apiService.getGuest("people");
        call.enqueue(new Callback<List<GuestModel>>() {
            @Override
            public void onResponse(Call<List<GuestModel>> call, Response<List<GuestModel>> response) {
                if(response.isSuccess()){
                    guestModels = response.body();
                    guestAdapter = new GuestAdapter(getContext(), guestModels);
                    mRecyclerView.setAdapter(guestAdapter);

                }else{
                    //Header status code
                    Toast.makeText(getContext(), "Error Response", Toast.LENGTH_SHORT).show();
                }
                Log.e("response", "onResponse: " + response.toString());
            }

            @Override
            public void onFailure(Call<List<GuestModel>> call, Throwable t) {
                //Header status code
                Toast.makeText(getContext(), "Error?", Toast.LENGTH_SHORT).show();
            }
        });


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

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}