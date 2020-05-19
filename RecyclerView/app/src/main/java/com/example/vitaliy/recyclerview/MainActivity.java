package com.example.vitaliy.recyclerview;

import android.arch.persistence.room.Room;
import android.database.Cursor;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.example.vitaliy.recyclerview.Database.MovieDatabase;
import com.example.vitaliy.recyclerview.Database.Movies;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission_group.SMS;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Products> mData;
    private CoordinatorLayout coordinatorLayout;

    private static final String DATABASE_NAME = "movies.db";
    private MovieDatabase movieDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        mRecyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecorator(this));


        mData = new ArrayList<>();
        mData.add(new Products(1, "the first", "a"));
        mData.add(new Products(2, "the second", "b"));
        mData.add(new Products(3, "the third", "c"));
        mData.add(new Products(4, "the fourth", "d"));
        mData.add(new Products(5, "the fifth", "e"));
        // specify an adapter (see also next example)
        mAdapter = new MyRecyclerViewAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);

        movieDatabase = Room.databaseBuilder(getApplicationContext(),
                MovieDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Movies movie =new Movies();
//                movie.setMovieName("A");
//                movieDatabase.daoAccess ().insertSingleMovie(movie);
//                movie.setMovieName("B");
//                movieDatabase.daoAccess ().insertSingleMovie(movie);
//            }
//        }) .start();

        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

//        SwipeController swipeController = new SwipeController();
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeController);
//        itemTouchHelper.attachToRecyclerView(mRecyclerView);


    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof MyRecyclerViewAdapter.myViewHolder) {
            // get the removed item name to display it in snack bar
            String name = mData.get(viewHolder.getAdapterPosition()).getTitle();
            String description = mData.get(viewHolder.getAdapterPosition()).getDesc();

            // backup of removed item for undo purpose
            final Products deletedItem = mData.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            mAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option

            Snackbar snackbar = Snackbar
//                    .make(findViewById(android.R.id.content), name + " removed from cart!", Snackbar.LENGTH_LONG);
                    .make(coordinatorLayout, name + " removed from the list!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    mAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    public void onUpdateClick(View view) {
        List<Products> productList = new ArrayList<>();
        productList.add(new Products(1, "a", "b"));
        productList.add(new Products(22, "b", "b"));
        productList.add(new Products(3, "c", "b"));
        productList.add(new Products(4, "d", "b"));
        productList.add(new Products(5, "e", "b"));
//
        mAdapter.setmData(productList);
        mAdapter.notifyDataSetChanged();

//        productList.add(new Products(1,"a", "b"));
//        productList.add(new Products(22,"b", "b"));
//        productList.add(new Products(3,"c", "b"));
//        productList.add(new Products(4,"d", "b"));
//        productList.add(new Products(5,"e", "b"));
//
//        mAdapter.setmData(productList);
//        mAdapter.notifyDataSetChanged();

//        productList.add(new Products(1,"a", "b"));
//        productList.add(new Products(22,"b", "b"));
//        productList.add(new Products(3,"c", "b"));
//        productList.add(new Products(4,"d", "b"));
//        productList.add(new Products(5,"e", "b"));
//
//        mAdapter.setmData(productList);
//        mAdapter.notifyItemChanged(1);


//        productList.add(new Products(1,"a", "b"));
//        productList.add(new Products(2,"b", "b"));
//        productList.add(new Products(333,"c", "b"));
//        productList.add(new Products(4,"d", "b"));
//        productList.add(new Products(5,"e", "b"));
//
//        ProductDiffUtilCallback productDiffUtilCallback =
//                new ProductDiffUtilCallback(mAdapter.getmData(), productList);
//        DiffUtil.DiffResult productDiffResult = DiffUtil.calculateDiff(productDiffUtilCallback);
//
//        mAdapter.setmData(productList);
//        productDiffResult.dispatchUpdatesTo(mAdapter);

//        productList.add(new Products(2,"b", "b"));
//        productList.add(new Products(333,"c", "b"));
//        productList.add(new Products(4,"d", "b"));
//        productList.add(new Products(5,"e", "b"));
//        productList.add(new Products(6,"a", "b"));
//
//
//        ProductDiffUtilCallback productDiffUtilCallback =
//                new ProductDiffUtilCallback(mAdapter.getmData(), productList);
//        DiffUtil.DiffResult productDiffResult = DiffUtil.calculateDiff(productDiffUtilCallback);
//
//        mAdapter.setmData(productList);
//        productDiffResult.dispatchUpdatesTo(mAdapter);


//        productList.add(new Products(5,"the first", "a")); //1
//        productList.add(new Products(4,"the second", "b")); //2
//        productList.add(new Products(3,"the third", "c")); //3
//        productList.add(new Products(2,"the fourth", "d")); //4
//        productList.add(new Products(1,"the fifth", "e")); //5

//        productList.add(new Products(5, "the fifth", "e")); //1
//        productList.add(new Products(4, "the fourth", "d")); //2
//        productList.add(new Products(3, "the third", "c")); //3
//        productList.add(new Products(2, "the second", "b")); //4
//        productList.add(new Products(1, "the first try", "rrrrr")); //5

        ArrayList<Movies> movies;
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Movies> movies =
                        (ArrayList<Movies>) movieDatabase.daoAccess().fetchAllMovies();
                Log.i("mymovie", movies.get(0).getMovieName());
            }
        }).start();


        ProductDiffUtilCallback productDiffUtilCallback =
                new ProductDiffUtilCallback(mAdapter.getmData(), productList);
        DiffUtil.DiffResult productDiffResult =
                DiffUtil.calculateDiff(productDiffUtilCallback, true);

        mAdapter.setmData(productList);
        productDiffResult.dispatchUpdatesTo(mAdapter);

//        productList.add(new Products(5,"a", "b"));
//        productList.add(new Products(4,"b", "b"));
//        productList.add(new Products(3,"c", "b"));
//        productList.add(new Products(2,"d", "b"));
//        productList.add(new Products(1,"e", "b"));
//
//
//        ProductDiffUtilCallback productDiffUtilCallback =
//                new ProductDiffUtilCallback(mAdapter.getmData(), productList);
//        DiffUtil.DiffResult productDiffResult =
//                DiffUtil.calculateDiff(productDiffUtilCallback, true);
//
//        mAdapter.setmData(productList);
//        productDiffResult.dispatchUpdatesTo(mAdapter);


    }
}
