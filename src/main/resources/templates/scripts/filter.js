/*
    Filters:

    element.innerHTML.indexOf(title_input_button.value)
*/

const caseSensitive = false

const title_input = document.querySelector(".title-filter")

title_input.oninput = () => {
    const objectElements = document.querySelectorAll(".object:not(.stringed-clips)")
    var filterables = [...objectElements]
    filterables.forEach(element => {
        element.classList.remove("hide")

        let inputContent = title_input.value
        inputContent = caseSensitive ? inputContent : inputContent.toLowerCase()

        let title = element.children[1].children[0].textContent
        title = caseSensitive ? title : title.toLowerCase();

        if(title.indexOf(inputContent) == -1){
            element.classList.add("hide")
        }
    })
}


