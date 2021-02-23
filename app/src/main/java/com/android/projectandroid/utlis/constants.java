package com.android.projectandroid.utlis;

import com.android.projectandroid.R;
import com.android.projectandroid.model.Team;

import java.util.HashMap;
import java.util.Map;

public class constants {
    //Constant for logs
    public final static String LOG_TAG = "NBA_APP";
    //List of NBA team
    public final static Map<String, Team> MAP_LOGO_TEAM = new HashMap<String, Team>() {{
        put("ATL", new Team(R.drawable.logo_hawks, "Hawks", "ATL", "Atlanta", 1));
        put("BOS", new Team(R.drawable.logo_celtics, "Celtics", "BOS", "Boston", 2));
        put("BKN", new Team(R.drawable.logo_nets, "Nets", "BKN", "Brooklyn", 3));
        put("CHA", new Team(R.drawable.logo_hornets, "Hornets", "CHA", "Charlotte", 4));
        put("CHI", new Team(R.drawable.logo_bulls, "Bulls", "CHI", "Chicago", 5));
        put("CLE", new Team(R.drawable.logo_cavaliers, "Cavaliers", "CLE", "Cleveland", 6));
        put("DAL", new Team(R.drawable.logo_mavericks, "Mavericks", "DAL", "Dallas", 7));
        put("DEN", new Team(R.drawable.logo_nuggets, "Nuggets", "DEN", "Denver", 8));
        put("DET", new Team(R.drawable.logo_pistons, "Pistons", "DET", "Detroit", 9));
        put("GSW", new Team(R.drawable.logo_golden_states, "Warriors", "GSW", "Golden State", 10));
        put("HOU", new Team(R.drawable.logo_rockets, "Rockets", "HOU", "Houston", 11));
        put("IND", new Team(R.drawable.logo_pacers, "Pacers", "IND", "Indiana", 12));
        put("LAC", new Team(R.drawable.logo_clippers, "Clippers", "LAC", "Los Angeles", 13));
        put("LAL", new Team(R.drawable.logo_lakers, "Lakers", "LAL", "Los Angeles", 14));
        put("MEM", new Team(R.drawable.logo_grizzlies, "Grizzlies", "MEM", "Memphis", 15));
        put("MIA", new Team(R.drawable.logo_heat, "Heat", "MIA", "Miami", 16));
        put("MIL", new Team(R.drawable.logo_bucks, "Bucks", "MIL", "Milwaukee", 17));
        put("MIN", new Team(R.drawable.logo_timberwolves, "Timberwolves", "MIN", "Minnesota", 18));
        put("NOP", new Team(R.drawable.logo_pelicans, "Pelican", "NOP", "New Orleans", 19));
        put("NYK", new Team(R.drawable.logo_knicks, "Knicks", "NYK", "New York", 20));
        put("OKC", new Team(R.drawable.logo_thunder, "Thunder", "OKC", "Oklahoma City", 21));
        put("ORL", new Team(R.drawable.logo_magic, "Magic", "ORL", "Orlando", 22));
        put("PHI", new Team(R.drawable.logo_76ers, "76ers", "PHI", "Philadelphia", 23));
        put("PHX", new Team(R.drawable.logo_suns, "Suns", "PHX", "Phoenix", 24));
        put("POR", new Team(R.drawable.logo_trail_blazers, "Trail Blazers", "POR", "Portland", 25));
        put("SAC", new Team(R.drawable.logo_kings, "Kings", "SAC", "Sacramento", 26));
        put("SAS", new Team(R.drawable.logo_spurs, "Spurs", "SAS", "San Antonio", 27));
        put("TOR", new Team(R.drawable.logo_raptors, "Raptors", "TOR", "Toronto", 28));
        put("UTA", new Team(R.drawable.logo_jazz, "Jazz", "UTA", "Utah", 29));
        put("WAS", new Team(R.drawable.logo_wizards, "Wizards", "WAS", "Washington", 30));
    }};
}
