const BASE_URL = "https://api.twitch.tv/"
const CLIP_RES = "helix/clips"
const PROXY = "";
const TOKEN = 'fyu0aj0hexqkl66qukyr3rr4jthft4';
const CLIENT_ID = '0kvjan2jt8lf8qkhjolubt5ggih7ip';

const categoryInput = document.getElementById("category-input");
const usernameInput = document.getElementById("username-input")
const clipInput = document.getElementById("clip-input")
const search_button = document.getElementById("find-clips")

let clip_count = 100;
let pagination = ""

const user_args = {
    game_id : '',
    broadcaster_id : '',
    clip_id : ''
}

const fetchClipsOnClick = function (){

    user_args.game_id = ''
    user_args.broadcaster_id = ''
    user_args.clip_id = ''

    let category = categoryInput.value;
    let username = usernameInput.value;
    let clip = clipInput.value;
    console.log("Searching for clip with category:", category, "streamed by:", username,"and clip ID:", clip);

    document.getElementById('clip-scroller').replaceChildren()

    getCategory(category)
        .then(() => getUser(username))
        .then(() => getClipSuffix(user_args))
        .then(params => getData(CLIP_RES, params))
        .then(arr => arr.filter(matchesInput))
        .then(arr => arr.forEach(el => {
            appendClip(el)
        }
            ))
    
}

const matchesInput = function (clip) {
    return (clip.broadcaster_id == user_args.broadcaster_id || user_args.broadcaster_id == "") && 
            (clip.game_id == user_args.game_id || user_args.game_id == "")
}

const queryParams = function(){
    let args = Array.from(arguments);
    if(args.length % 2 != 0) throw "Query Parameters must be key-value pairs!"
    return args
}

const parameterSuffix = function(arr){
    let suffix = "?" + arr[0] + "=" + arr[1]
    for(let i = 2; i < arr.length; i += 2){
        suffix += "&" + arr[i] + "=" + arr[i+1];
    }
    return suffix;
}

const twitchGetRequest = function(resource, params) {
    let url = BASE_URL + resource + parameterSuffix(params) + "&first=" + clip_count + "&after=" + pagination;
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest()
        xhr.open('GET', url)
        xhr.responseType = 'json'
        xhr.setRequestHeader('Client-Id', CLIENT_ID)
        xhr.setRequestHeader('Authorization', 'Bearer ' + TOKEN)
        xhr.onload = () => {
            resolve(xhr.response)
        }
        xhr.send()
    })

}

const getData = (resource, params) => {
    return new Promise((resolve, reject) => {
        twitchGetRequest(resource, params)
        .then(res => {
            const { data, pagination } = res
            console.log(pagination.cursor)
            resolve(data)
        })
    })
}

const getCategory = (name) => {
    return new Promise((resolve, reject) => {
        if(name == ''){
            resolve()
            return
        }
        twitchGetRequest('helix/games', queryParams('name', name))
        .then(res => {
            const { data } = res
            try{
                user_args.game_id = data[0].id
            }catch(err){
                console.log("Invalid category")
            }
            resolve()
        })
    })

}

const getUser = (login) => {
    return new Promise((resolve, reject) => {
        if(login == ''){
            resolve()
            return
        }
        twitchGetRequest('helix/users', queryParams('login', login))
        .then(res => {
            const { data } = res
            user_args.broadcaster_id = data[0].id
            resolve()
        })
    })
}

// const getClip = (clip) => {
//     twitchGetRequest('helix/clips', queryParams('id', clip))
//     .then(res => {
//         const [] = res
//         console.log(data)
//     })
// }

const getClipSuffix = (args) => {
    return new Promise((resolve, reject) => {
        if(args.game_id != ''){
            resolve(queryParams('game_id', args.game_id))
        }else if(args.broadcaster_id != ''){
            resolve(queryParams('broadcaster_id', args.broadcaster_id))
        }
    })
}

const appendClip = (clip) => {

    let div = document.createElement('div')
    div.innerHTML = 
    
    `
    <div class="object">
					<div class="thumbnail-div">
						<a href="${clip.url}" target="_blank">
                        <img src="${clip.thumbnail_url}" alt="" class="thumbnail"></a>
					</div>
					<div class="clip-metadata">
						<p class="clip-title">${clip.title}</p>
						<p class="view-count">&middot; ${clip.view_count} views
						</p>
						<p class="clip-author">Streamed by ${clip.broadcaster_name} &middot; Duration: ${clip.duration} s</p>
						<p class="clip-id">Clip ID: ${clip.id}</p>
                        <p class="clip-author">Game</p>
					</div>

					<style>
						.object {
							overflow: hidden;
							background: black;
							margin-bottom: 10px;
							border-radius: 7px;
							box-shadow: 0px 0px 3px rgb(0, 0, 0, 0.9);
                            width: 95%;
                            transition: width 0.5s;
						}
                        .object:hover {
                            width: 100%;
                        }
						.thumbnail {
							height: 66px;
							vertical-align: top;
						}
						.thumbnail-div {
							display: inline-block;
							overflow: hidden;
							vertical-align: top;
						}
						.clip-metadata {
							margin-left: -4px;
							padding-left: 4px;
							padding-top: 3px;
							padding-bottom: 3px;
							height: 60px;
							border-left: 1px solid black;
							display: inline-block;
							vertical-align: top;
                            width: 80%;
                            overflow-y: scroll;
						}
                        .clip-metadata::-webkit-scrollbar {
                            display: none;
                        }

						.clip-title,
						.view-count,
						.clip-author,
						.clip-id {
							font-family: Rubik;
							color: white;
							font-weight: 200;
						}

						.clip-title {
							display: inline-block;
							font-weight: 500;
						}
						.view-count {
							display: inline-block;
						}
						.clip-author {
						}
						.clip-id {
						}
					</style>
				</div>
    `
    document.getElementById('clip-scroller').appendChild(div)
}

search_button.onclick = fetchClipsOnClick;

left_button.onclick = fetchClipsOnClick; // before=pagination

right_button.onclick = fetchClipsOnClick; // after=pagination