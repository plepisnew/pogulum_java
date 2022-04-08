# Twitch Clip Scrapper: username -> mp4 of selected clips

Get Clips -> Filter -> Edit -> Upload

# REST API endpoints: 

/api/twitch/users (param login_id) - displays all users in db
<br>
/api/twitch/clips (param login_id, game_id, clip_id) - displays all clips in db
<br>
/api/twitch/categories (tbd) - displays all games/categories in db
<br>
Entities are saved to a local DB by sending a POST request to the corresponding endpoint.

# Web endpoints:
/home - HTML template: clip link -> video source

# FAQ:

Q: I selected a Game and User but it didn't find any clips :( <br>
A: The Twitch API, when fetching clips, doesn't allow parameters for both games and users, so our API fetches clips according to whichever input comes first and filters the results based on the second input (if any). If you scroll to the right, you will eventually find a set of clips where at least one matches both filters and will be displayed.
