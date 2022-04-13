// CONSTANTS & ADJUSTABLES

const DEFAULT_TOKEN = 'fyu0aj0hexqkl66qukyr3rr4jthft4'
const DEFAULT_ID = '0kvjan2jt8lf8qkhjolubt5ggih7ip'

const BASE_URL = "https://api.twitch.tv/"
const CLIP_RES = "helix/clips"
const PROXY = "";
var TOKEN = 'fyu0aj0hexqkl66qukyr3rr4jthft4';
var CLIENT_ID = '0kvjan2jt8lf8qkhjolubt5ggih7ip';
let clip_count = 10;

const DB_URL = "";

// ELEMENTS

const categoryInput = document.getElementById("category-input");
const usernameInput = document.getElementById("username-input")
const clipInput = document.getElementById("clip-input")

const search_button = document.getElementById("find-clips")
const left_button = document.getElementById("move-left")
const right_button = document.getElementById("move-right")

const twitch_token_input = document.querySelector(".twitch-token")
const twitch_id_input = document.querySelector(".twitch-id")
const youtube_token_input = document.querySelector(".youtube-token")
const youtube_id_input = document.querySelector(".youtube-id")
// REST API 

const REST_URL = "http://localhost:5000"

const USER_RESOURCE = "/api/user"
const GAME_RESOURCE = "/api/game"
const CLIP_RESOURCE = "/api/clip"

const USER_URL = REST_URL + USER_RESOURCE
const GAME_URL = REST_URL + GAME_RESOURCE
const CLIP_URL = REST_URL + CLIP_RESOURCE

// ACTIONS

twitch_token_input.oninput = () => {
    TOKEN = twitch_token_input.value;
}

twitch_id_input.oninput = () => {
    CLIENT_ID = twitch_id_input.value;
}

// USER ARGUMENTS AND PAGINATION

const page = {
    current_cursor : '',
    cursors : [''],
    current_pointer : 0
}

const user_args = {
    game_id : '',
    broadcaster_id : '',
    clip_id : ''
}

const fetchClipsOnClick = function (){

    TOKEN = TOKEN == "" ? DEFAULT_TOKEN : TOKEN;
    CLIENT_ID = CLIENT_ID == "" ? DEFAULT_ID : CLIENT_ID;

    user_args.game_id = ''
    user_args.broadcaster_id = ''
    user_args.clip_id = ''

    let category = categoryInput.value;
    let username = usernameInput.value;
    let clip = clipInput.value;
    user_args.clip_id = clip;

    console.log("Searching for clip with category:", category, "streamed by:", username,"and clip ID:", clip);

    document.getElementById('clip-scroller').replaceChildren()

    getCategory(category)
        .then(() => getUser(username))
        .then(() => getClipSuffix(user_args))
        .then(params => getData(CLIP_RES, params, 'normal'))
        .then(arr => arr.filter(matchesInput))
        .then(arr => appendClips(arr))
    
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

const twitchGetRequest = function(resource, params, type) {

    let suffix;
    if(type == 'right') {
        suffix = page.cursors[page.current_pointer]
    }else if (type == 'left') {
        suffix = page.cursors[page.current_pointer >= 2 ? page.current_pointer-2 : 0]
    }else if (type == 'normal') {
        suffix = ""
        page.current_pointer = 0
        page.current_cursor = ""
        page.cursors = [""]
    }

    let url = BASE_URL + resource + parameterSuffix(params) + "&first=" + clip_count + "&after=" + suffix;
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

const getData = (resource, params, type) => {
    return new Promise((resolve, reject) => {
        twitchGetRequest(resource, params, type)
        .then(res => {
            const { data, pagination } = res

            // console.log(data)
            if(type == 'right') {
                page.current_cursor = pagination.cursor
                if(page.current_pointer + 1 == page.cursors.length) {
                    console.log("Pushing to array")
                    page.cursors.push(pagination.cursor)
                }
                page.current_pointer++
            }else if (type == 'left') {
                page.current_pointer = page.current_pointer >= 2 ? page.current_pointer-1 : 1;
                page.current_cursor = pagination.cursor
            }else if (type == 'normal') {
                page.current_pointer = 1
                page.current_cursor = pagination.cursor
                page.cursors.push(pagination.cursor)
            }
            console.log("Pagination:")
            console.log(page)

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
        twitchGetRequest('helix/games', queryParams('name', name), null)
        .then(res => {
            const { data } = res
            try{
                user_args.game_id = data[0].id
                httpPost(GAME_URL, data[0])
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
        twitchGetRequest('helix/users', queryParams('login', login), null)
        .then(res => {
            const { data } = res
            user_args.broadcaster_id = data[0].id
            httpPost(USER_URL, data[0])
            resolve()
        })
    })
}

const getClipSuffix = (args) => {
    return new Promise((resolve, reject) => {
        console.log("User args:")
        console.log(args)
        if(args.clip_id != ''){
            resolve(queryParams('id', args.clip_id))
        }else if(args.broadcaster_id != ''){
            resolve(queryParams('broadcaster_id', args.broadcaster_id))
        }else if(args.game_id != ''){
            resolve(queryParams('game_id', args.game_id))
        }
    })
}

const appendClip = (clip) => {

    let div = document.createElement('div')
    div.innerHTML = 
    
    `
    <div class="object draggable" draggable="true">
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
					</div>

					<style>
						.object {
							overflow: hidden;
							background: rgb(13, 71, 161);
							margin-bottom: 10px;
							border-radius: 7px;
							box-shadow: 0px 0px 3px rgb(0, 0, 0, 0.9);
                            width: 95%;
                            transition: width 0.5s;
                            display: grid;
                            grid-template-columns: auto 1fr;
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
                            border-right: 1px solid black;
						}
						.clip-metadata {
							padding-top: 3px;
							padding-bottom: 3px;
							height: 60px;
							margin-left: 5px;
							display: inline-block;
							vertical-align: top;
                            
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

left_button.onclick = () => {
    console.log("Moving pagination up (left)")
    document.getElementById('clip-scroller').replaceChildren()
    getClipSuffix(user_args)
        .then(params => getData(CLIP_RES, params, 'left'))
        .then(arr => arr.filter(matchesInput))
        .then(arr => appendClips(arr))
}

right_button.onclick = () => {
    console.log("Moving pagination down (right)")
    document.getElementById('clip-scroller').replaceChildren()
    getClipSuffix(user_args)
        .then(params => getData(CLIP_RES, params, 'right'))
        .then(arr => arr.filter(matchesInput))
        .then(arr => appendClips(arr))
}

document.querySelector(".download.video").onclick = () => {
    const stringedClips = [...document.querySelectorAll(".stringed-clips")]
    stringedClips.forEach(clip => {
        // clip.children[1].children[3].innerText.split('Clip ID: ')[1]
        let source_image = clip.querySelector('.thumbnail').getAttribute('src')
        let source_id = source_image.split('.tv/')[1].split('-preview')[0]
        let source = `https://clips-media-assets2.twitch.tv/${source_id}.mp4`
        console.log(source)
    })
}

const httpPost = (url, body) => {
    console.log("POST " + url)
    console.log(body)
    fetch(url, {
        headers: {
            'Content-Type' : 'application/json',
            'Access-Control-Allow-Origin' : '*',
            'mode' : 'no-cors'
        },
        method: 'POST',
        body : JSON.stringify(body)
    })
    .then(res => res.json())
    .then(res => {
        console.log("Response:")
        console.log(res)
    })
}

const setDraggables = () => {
    const draggables = document.querySelectorAll(".draggable")
    const containers = document.querySelectorAll(".drag-container")

    draggables.forEach(draggable => {
        draggable.addEventListener('dragstart', () => {
            draggable.classList.add('dragging')
        })
        draggable.addEventListener('dragend', () => {
            draggable.classList.remove('dragging')
        })
    })

    containers.forEach(container => {
        container.addEventListener('dragover', e => {
            e.preventDefault()
            const afterElement = getDragAfterElement(container, e.clientY)
            const draggable = document.querySelector('.dragging')
            if(afterElement == null) {
                container.appendChild(draggable)
            } else {
                if(container.contains(afterElement)){
                    container.insertBefore(draggable, afterElement)
                }
                
            }
            if(container == containers[0]) { 
                draggable.classList.remove('stringed-clips')
            }else {
                draggable.classList.add('stringed-clips')
            }
            
        })
    })

    function getDragAfterElement(container, y) {
        const draggableElements = [...container.querySelectorAll('.draggable:not(.dragging)')]
        return draggableElements.reduce((closest, child) => {
            const box = child.getBoundingClientRect()
            const offset = y - box.top - box.height/2
            if(offset < 0 && offset > closest.offset) {
                return { offset: offset, element: child }
            } else {
                return closest
            }
        }, { offset: Number.NEGATIVE_INFINITY }).element

    }
}

const appendClips = (arr) => {
    arr.forEach(clip => appendClip(clip))
    setDraggables()
}

appendClip({
    title : "Totally legit clip",
    url : "https://github.com/plepisnew/pogulum",
    thumbnail_url : "https://i.natgeofe.com/k/7ce14b7f-df35-4881-95ae-650bce0adf4d/mallard-male-standing_3x2.jpg",
    broadcaster_name : "ansishihi & E-Val & Soundharya",
    duration : 1209600,
    view_count : 1337,
    id : "twitch-clip-scraper"
})
