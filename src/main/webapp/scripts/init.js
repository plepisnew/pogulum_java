
const BASE_URL = "http://api.twitch.tv/"
const TOKEN = "kvk7oozv0j604jbsshrfryfpj70s7e";
const CLIENT_ID = "rclrsw7p5m3o88wqpzrknhcz1y2bgl"

const categoryInput = document.getElementById("category-input");
const usernameInput = document.getElementById("username-input")
const clipInput = document.getElementById("clip-input")


const search_button = document.getElementById("find-clips")

search_button.onclick = function (){
    let category = categoryInput.value;
    let username = usernameInput.value;
    let clip = clipInput.value;



    console.log("Searching for clip with category:", category, "streamed by", username,"and clip ID", clip);
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

const twitchGetRequest = function(resource, params){
    let target = BASE_URL + resource + parameterSuffix(params);
    console.log(target);
    const promise = new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", target);
        xhr.setRequestHeader("Authentication", "Bearer " + TOKEN)
        xhr.setRequestHeader("Client-Id", CLIENT_ID)
        xhr.setRequestHeader("Access-Control-Allow-Origin", "*")
        xhr.responseType = "json";

        xhr.onload = () => {
            resolve(xhr.response);
        };
        xhr.send();
    })
    return promise;
}

const twitchGetRequestFetch = function (resource, params){
    let target = BASE_URL + resource + parameterSuffix(params);
    console.log(target);
    fetch(target , {
        method: 'GET',
        headers: {
            'Client-ID': CLIENT_ID,
            'Authorization': 'Bearer ' + TOKEN
        }
    })
        .then(res => res.json())
        .then(res => {
            console.log(res)
        });
}

twitchGetRequestFetch("helix/games", queryParams("name", "Fortnite")).then((data) => { console.log(data) });



