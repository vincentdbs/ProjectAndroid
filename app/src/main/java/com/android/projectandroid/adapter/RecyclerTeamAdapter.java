package com.android.projectandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.projectandroid.R;
import com.android.projectandroid.database.TeamDml;
import com.android.projectandroid.model.Team;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class RecyclerTeamAdapter extends RecyclerView.Adapter<RecyclerTeamAdapter.ViewHolder> {



    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row

        private ImageView logo, star;
        private TextView tvNameTeam;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            logo = itemView.findViewById(R.id.ivLogoTeamFavorite);
            star = itemView.findViewById(R.id.ivStar);
            tvNameTeam = itemView.findViewById(R.id.tvNameFavoriteTeam);
        }
    }

    // Store a member variable for the contacts
    private List<Team> mTeams;
    private Context context;

    // Pass in the contact array into the constructor
    public RecyclerTeamAdapter(List<Team> mTeams) {
        this.mTeams = mTeams;
    }

    public RecyclerTeamAdapter(List<Team> mTeams, Context context) {
        this.mTeams = mTeams;
        this.context = context;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public RecyclerTeamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.card_team, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RecyclerTeamAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Team team = mTeams.get(position);

        // Set item views based on your views and data model

        holder.star.setImageResource(team.isFavorites() ? R.drawable.ic_baseline_star_24 : R.drawable.ic_baseline_star_border_32);

        holder.star.setOnClickListener(v->{
            TeamDml db = new TeamDml(context);
            Team actualTeam = mTeams.get(position);

            actualTeam.flipFavorite();
            //Display full star + add the team to the DB
            if(actualTeam.isFavorites()){
                holder.star.setImageResource(R.drawable.ic_baseline_star_24);
                db.addLine(actualTeam.getAbreviation());
            }else{ //Display border start + remove team from BD
                holder.star.setImageResource(R.drawable.ic_baseline_star_border_32);
                db.deleteFilteredTableContent(actualTeam.getAbreviation());
            }
        });

        //todo add listner
//        addOnClickStarListener(holder.star, position);
        holder.logo.setImageResource(team.getLogo());
        holder.star.setColorFilter(R.color.black);
        holder.tvNameTeam.setText(team.getName());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mTeams.size();
    }

//    private void addOnClickStarListener(ImageView star, int position){
//        star.setOnClickListener(view -> {
//            TeamDml db = new TeamDml(context);
//            Team actualTeam = teams.get(position);
//
//            actualTeam.flipFavorite();
//            //Display full star + add the team to the DB
//            if(actualTeam.isFavorites()){
//                star.setImageResource(R.drawable.ic_baseline_star_24);
//                db.addLine(actualTeam.getAbreviation());
//            }else{ //Display border start + remove team from BD
//                star.setImageResource(R.drawable.ic_baseline_star_border_32);
//                db.deleteFilteredTableContent(actualTeam.getAbreviation());
//            }
//        });
//    }
}