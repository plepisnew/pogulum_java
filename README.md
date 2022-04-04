# Twitch Clip Scrapper: username -> mp4 of selected clips

Get Clips -> Filter -> Edit -> Upload

REST API endpoints: 

/api/twitch/users (param login_id) - displays all users in db
<br>
/api/twitch/clips (param login_id, game_id, clip_id) - displays all clips in db
<br>
/api/twitch/categories (tbd) - displays all games/categories in db
<br>

Entities are saved to a local DB by sending a POST request to the corresponding endpoint.


