// Selecciona todos los elementos con la clase "input"
const inputs = document.querySelectorAll(".input");

// Función que añade la clase "focus" al contenedor padre
function addcl() {
    // `this` se refiere al input actualmente enfocado
    let parent = this.parentNode.parentNode; // Selecciona el contenedor abuelo del input
    parent.classList.add("focus"); // Añade la clase "focus"
}

// Función que elimina la clase "focus" del contenedor padre si el campo está vacío
function remcl() {
    // `this` se refiere al input actualmente desenfocado
    let parent = this.parentNode.parentNode; // Selecciona el contenedor abuelo del input
    if (this.value == "") { // Verifica si el valor del input está vacío
        parent.classList.remove("focus"); // Elimina la clase "focus"
    }
}

// Añade event listeners a cada input para los eventos "focus" y "blur"
inputs.forEach(input => {
    input.addEventListener("focus", addcl); // Llama a addcl al enfocar el input
    input.addEventListener("blur", remcl); // Llama a remcl al desenfocar el input
});

