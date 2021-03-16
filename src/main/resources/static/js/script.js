let nowShowing = document.getElementById("nowShowing");
let comingSoon = document.getElementById("comingSoon");
document.getElementById("nowShowing").disabled = true;


const requestObject = {
  method : "GET",
  "conent-type" : "application/json",
  redirect : "follow"
}

function fetchMovies() {
  fetch('http://localhost:8080/getAllMovies', requestObject)
      .then(response => response.json())
      .then(movies => showMovies(movies));
}

window.onload = fetchMovies();

function showMovies(movies){
  const movieDiv = document.querySelector('#test');
  movies.forEach(movie => {
    const movieElement = document.createElement('div');
    const pTagTitle = document.createElement('p');
    const pTagReleaseYear = document.createElement('p');

    pTagTitle.innerText = movie.movieTitle;
    pTagReleaseYear.innerText = movie.releaseYear;

    movieElement.style.paddingRight = '100px';
    movieElement.append(pTagTitle);
    movieElement.append(pTagReleaseYear);
    movieDiv.append(movieElement);
  });
}



nowShowing.onclick = function (){
  document.getElementById("nowShowing").disabled = true;
  document.getElementById("comingSoon").disabled = false;
  fetchMovies();
}

comingSoon.onclick = function (){
  document.getElementById("nowShowing").disabled = false;
  document.getElementById("comingSoon").disabled = true;
  document.querySelector('#test').innerHTML = "";
}



