# Project Android

## Goal of the App

Provide a simple user interface to display the list of all matches of the NBA at a given date and their results.

## API used

I used the free and public API : [balldontlie.io](www.balldontlie.io)  

## Features

- Display all the NBA matches for a given day  
- Display all the NBA matches of the user favourite's team for a given day  
- Select the day to display  
- Choose a player and display his statistics  
- Follow or unfollow a team  
- Receive a notification at 8:30AM that display the results of the night  

## Criteria

- Minimum Android version : 6
- Graphical element used :
    - Button
    - Images
    - Icon
    - Switch
    - SearchView
    - Textview
    - Toolbar with menu item
    - Recycler view
    - List view
    - Tablayout
- AyncTasks object to retrieve JSON response from the API
- Service to trigger the notification
- Persitance of the data 
    - Favourite teams are stored in a SQLite database