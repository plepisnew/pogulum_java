Pogulum: Twitch Clip Scraper 📹
-
![tcs](assets/tcs.png)
Pogulum is a free and open-source online Twitch clip scraper, which interacts with the [Twitch API](https://dev.twitch.tv/docs/api/) to fetch clips based on user-selected filters and the [YouTube Data API](https://developers.google.com/youtube/v3) to upload concatenated clips directly to YouTube OR download it as an .mp4 file.

Status 📄
-
- [x] Fetch clips from the Twitch API
- [x] Display clips and move using pagination
- [ ] Drag-and-drop clips to create a final video
- [ ] Download final video locally
- [ ] Upload final clip to YouTube
- [ ] Upload final clip to TikTok
- [ ] Instead of hard-coded games, find top games from Twitch API

Our Stack 📦
-
- Build Tool: Maven
- Back-end: Java Spring Boot
- Front-end: HTML, CSS, JS
- Database: PostgreSQL
- AWS for deployment onto cloud

Additional parameters ⚙️
-

1. To fetch data from the Twitch API, authorization is necessary, for which a Client ID and App Access Token is required. By default we use hardcoded values, but users may provide their own auth credentials.

2. By default the Twitch API returns 20 clips up to a maximum of 100 clips. Our API fetches 50 clips by default; this can be adjusted according to user input.

Endpoints
-
Base URL: `http://localhost:8080/`
Resources:
- `api/user` : GET, POST, PUT, DELETE users to database
- `api/users` : GET all users in the database
- `api/clip` : GET, POST, PUT, DELETE clips to database
- `api/clips` : GET all clips in the database
- `home` : User Interface for fetching and downloading clips


How? 🧠
-

1. User inputs high-level data (Twitch Username and/or category name)
2. A Request is sent to `https://api.twitch.tv/helix/users?login=$channel` and `https://api.twitch.tv/helix/games?name=$game`, returning (among other data) `broadcaster_id` and `game_id` respectively.
3. A Request is sent to `https://api.twitch.tv/helix/clips?game_id=$game&first=$X`, which returns the first X (descending view count) clips, which are then filtered according to a user-provided category (and vice versa) and displayed.
4.  To scroll, a Request is sent to the same endpoint with an additional parameter `after=$cursor`, where `$cursor` is the pagination value from the previous Request body. [Twitch Pagination](https://dev.twitch.tv/docs/api/guide#pagination)
5. Selected clips are combined into a single file and uploaded to YouTube or TikTok [..]

Q&A ❓
-
> Q: My parents disowned me :(  
> A: lol

> Q: I selected a Game and User but it didn't find any clips :(  
> A: The Twitch API, when fetching clips, doesn't allow parameters for both games and users, so our API fetches clips according to whichever input comes first and filters the results based on the second input (if any). If you scroll to the right, you will eventually find a set of clips where at least one matches both filters and will be displayed.

Contact Devs ✉️
-
Kārlis: `val.karlis@gmail.com` and `E-Val#5668`  
Soundharya: `soundhy123@gmail.com` and `Soundharya#1921`  
Ansis:  `plepis.jaunais@gmail.com` and  `ansishihi#8449`  


