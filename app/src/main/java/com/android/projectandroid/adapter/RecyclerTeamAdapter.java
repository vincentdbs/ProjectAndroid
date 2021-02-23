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

public class RecyclerTeamAdapter extends RecyclerView.Adapter<RecyclerTeamAdapter.ViewHolder> {
    /**
     * View holder for our adapter
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        //Each UI component of each item is declare in the ViewHolder
        private ImageView logo, star;
        private TextView tvNameTeam;

        public ViewHolder(View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.ivLogoTeamFavorite);
            star = itemView.findViewById(R.id.ivStar);
            tvNameTeam = itemView.findViewById(R.id.tvNameFavoriteTeam);
        }
    }

    // Store the list of teams to display
    private List<Team> teams;
    private Context context;

    public RecyclerTeamAdapter(List<Team> mTeams) {
        this.teams = mTeams;
    }

    public RecyclerTeamAdapter(List<Team> mTeams, Context context) {
        this.teams = mTeams;
        this.context = context;
    }

    /**
     * Create the ViewHolder and tell it which is the layout of each element
     * @param parent the parent
     * @param viewType
     * @return the view holder
     */
    @Override
    public RecyclerTeamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the card team layout
        View contactView = inflater.inflate(R.layout.card_team, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    /**
     * Set the UI element of a given element
     * @param holder the view holder of the element
     * @param position the position of the element in the list of team
     */
    @Override
    public void onBindViewHolder(RecyclerTeamAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Team team = teams.get(position);

        // Set the value of each item
        //Empty or full star depending on if the team is favorite or not
        holder.star.setImageResource(team.isFavorites() ? R.drawable.ic_baseline_star_24 : R.drawable.ic_baseline_star_border_32);
        holder.star.setColorFilter(R.color.black);
        addOnClickStarListener(holder.star, position);

        //Team's logo
        holder.logo.setImageResource(team.getLogo());
        //Teams's name
        holder.tvNameTeam.setText(team.getAbreviation() + " - " + team.getName());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return teams.size();
    }

    /**
     * Add the listener on the star icon that changes the shape of the star and remove or add the team to the databse
     * @param star, the icon to add the click listener
     * @param position, the position of the team in the list
     */
    private void addOnClickStarListener(ImageView star, int position){
        star.setOnClickListener(view -> {
            TeamDml db = new TeamDml(context);
            Team actualTeam = teams.get(position);

            //True if it was false, false if it was true
            actualTeam.flipFavorite();
            //Display full star + add the team to the DB
            if(actualTeam.isFavorites()){
                star.setImageResource(R.drawable.ic_baseline_star_24);
                db.addLine(actualTeam.getAbreviation());
            }else{ //Display border start + remove team from BD
                star.setImageResource(R.drawable.ic_baseline_star_border_32);
                db.deleteFilteredTableContent(actualTeam.getAbreviation());
            }
        });
    }
}