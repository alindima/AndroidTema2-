package com.example.tema2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tema2.Db.Entity.User;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    private List<User> mDataset;

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView gradeView;

        public UserViewHolder(View v) {
            super(v);

            nameView = v.findViewById(R.id.nameView);
            gradeView = v.findViewById(R.id.gradeView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public UserListAdapter(List<User> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public UserListAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_view, parent, false);

        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.nameView.setText(mDataset.get(position).name);
        holder.gradeView.setText(Integer.toString(mDataset.get(position).grade));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

