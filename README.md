Pogulum: Twitch Clip Scraper
-
Pogulum is a free and open-source online Twitch clip scraper, which interacts with the [Twitch API](https://dev.twitch.tv/docs/api/) to fetch clips based on user-selected filters and the [YouTube Data API](https://developers.google.com/youtube/v3) to upload concatenated clips directly to YouTube OR download it as an .mp4 file.

Endpoints
-
Base URL: `http://localhost:8080/`
Resources:
- `api/user` : GET, POST, PUT, DELETE users to database
- `api/users` : GET all users in the database
- `api/clip` : GET, POST, PUT, DELETE clips to database
- `api/clips` : GET all clips in the database
- `home` : User Interface for fetching and downloading clips

Our Stack
-
- Build Tool: Maven
- Back-end: Java Spring Boot
- Front-end: HTML, CSS, JS
- Database: PostgreSQL
- AWS for deployment onto cloud

Status
-
- [x] Fetch clips from the Twitch API
- [ ] Display clips and move using pagination
- [ ] Drag-and-drop clips to create a final video
- [ ] Download final video locally
- [ ] Upload final clip to YouTube
- [ ] Upload final clip to TikTok

Additional parameters
-

1. To fetch data from the Twitch API, authorization is necessary, for which a Client ID and App Access Token is required. By default we use hardcoded values, but users may provide their own auth credentials.

2. By default the Twitch API returns 20 clips up to a maximum of 100 clips. Our API fetches 50 clips by default; this can be adjusted according to user input.

How?
-
magic :P

Q&A
-
> Q: My parents disowned me :(
> A: lol

> Q: I selected a Game and User but it didn't find any clips :(  
> A: The Twitch API, when fetching clips, doesn't allow parameters for both games and users, so our API fetches clips according to whichever input comes first and filters the results based on the second input (if any). If you scroll to the right, you will eventually find a set of clips where at least one matches both filters and will be displayed.

Contact Devs
-
Kārlis: `val.karlis@gmail.com` and `E-Val#5668`
Soundharya: `soundhy123@gmail.com` and `Soundharya#1921`
Ansis:  `plepis.jaunais@gmail.com` and  `ansishihi#8449`


