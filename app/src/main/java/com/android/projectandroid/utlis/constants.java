package com.android.projectandroid.utlis;

import com.android.projectandroid.R;
import com.android.projectandroid.model.Team;

import java.util.HashMap;
import java.util.Map;

public class constants {
    public final static String LOG_TAG = "NBA_APP";
    public final static Map<String, Team> MAP_LOGO_TEAM = new HashMap<String, Team>() {{
        put("ATL", new Team(R.drawable.logo_hawks, "Hawks", "ATL", "Atlanta"));
        put("BOS", new Team(R.drawable.logo_celtics, "Celtics", "BOS", "Boston"));
        put("BKN", new Team(R.drawable.logo_nets, "Nets", "BKN", "Brooklyn" ));
        put("CHA", new Team(R.drawable.logo_hornets, "Hornets", "CHA", "Charlotte"));
        put("CHI", new Team(R.drawable.logo_bulls, "Bulls", "CHI", "Chicago"));
        put("CLE", new Team(R.drawable.logo_cavaliers, "Cavaliers", "CLE", "Cleveland"));
        put("DAL", new Team(R.drawable.logo_mavericks, "Mavericks", "DAL", "Dallas"));
        put("DEN", new Team(R.drawable.logo_nuggets, "Nuggets", "DEN", "Denver"));
        put("DET", new Team(R.drawable.logo_pistons, "Pistons", "DET", "Detroit"));
        put("GSW", new Team(R.drawable.logo_golden_states, "Warriors", "GSW", "Golden State"));
        put("HOU", new Team(R.drawable.logo_rockets, "Rockets", "HOU", "Houston"));
        put("IND", new Team(R.drawable.logo_pacers, "Pacers", "IND", "Indiana"));
        put("LAC", new Team(R.drawable.logo_clippers, "Clippers", "LAC", "Los Angeles"));
        put("LAL", new Team(R.drawable.logo_lakers, "Lakers", "LAL", "Los Angeles"));
        put("MEM", new Team(R.drawable.logo_grizzlies, "Grizzlies", "MEM", "Memphis"));
        put("MIA", new Team(R.drawable.logo_heat, "Heat", "MIA", "Miami"));
        put("MIL", new Team(R.drawable.logo_bucks, "Bucks", "MIL", "Milwaukeee"));
        put("MIN", new Team(R.drawable.logo_timberwolves, "Timberwolves", "MIN", "Minnesota"));
        put("NOP", new Team(R.drawable.logo_pelicans, "Pelican", "NOP", "New Orleans"));
        put("NYK", new Team(R.drawable.logo_knicks, "Knicks", "NYK", "New York"));
        put("OKC", new Team(R.drawable.logo_thunder, "Thunder", "OKC", "Oklahoma City"));
        put("ORL", new Team(R.drawable.logo_magic, "Magic", "ORL", "Orlando"));
        put("PHI", new Team(R.drawable.logo_76ers, "76ers", "PHI", "Philadelphia"));
        put("PHX", new Team(R.drawable.logo_suns, "Suns", "PHX", "Phoenix"));
        put("POR", new Team(R.drawable.logo_trail_blazers, "Trail Blazers", "POR", "Portland"));
        put("SAC", new Team(R.drawable.logo_kings, "Kings", "SAC", "Sacramento"));
        put("SAS", new Team(R.drawable.logo_spurs, "Spurs", "SAS", "San Antonio"));
        put("TOR", new Team(R.drawable.logo_raptors, "Raptors", "TOR", "Toronto"));
        put("UTA", new Team(R.drawable.logo_jazz, "Jazz", "UTA", "Utah"));
        put("WAS", new Team(R.drawable.logo_wizards, "Wizards", "WAS", "Washington"));
    }};
}
