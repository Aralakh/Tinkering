package com.example.lawrenjuip.tinkering.Adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {
    GridLayoutManager mGridLayoutManager;
    public PaginationScrollListener(GridLayoutManager gridLayoutManager){
        mGridLayoutManager = gridLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy){
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = mGridLayoutManager.getChildCount();
        int totalItemCount = mGridLayoutManager.getItemCount();
        int firstVisibleItem = mGridLayoutManager.findFirstVisibleItemPosition();
        if(!isLoading() && !isLastPage()) {
            if (((visibleItemCount + firstVisibleItem) >= totalItemCount) && (firstVisibleItem >= 0)) {
                loadMoreItems();
            }
        }
    }

    protected abstract void loadMoreItems();

    public abstract int getTotalPageCount();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}
