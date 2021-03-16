let nowShowing = document.getElementById("nowShowing");
let comingSoon = document.getElementById("comingSoon");
document.getElementById("nowShowing").disabled = true;

const requestObject = {
  method : "GET",
  "content-type" : "application/json",
  redirect : "follow"
}

function fetchMovies() {
  fetch('http://localhost:8080/getAllMovies', requestObject)
      .then(response => response.json())
      .then(movies => showMoviesShowing(movies));
}

fetchMovies();

function showMoviesShowing(movies){
  const movieDiv = document.querySelector('#movieRow');

  movies.forEach(movie => {
    const movieElement = document.createElement('div');
    const pTagTitle = document.createElement('p');
    const pTagReleaseYear = document.createElement('span');
    const imgTagPoster = document.createElement("IMG");
    const genreTag = document.createElement('span');

    pTagTitle.innerText = movie.movieTitle;
    pTagReleaseYear.innerText = movie.releaseYear;
    genreTag.innerText = movie.genre;

    imgTagPoster.setAttribute("src", `../images/${movie.image}`);
    imgTagPoster.setAttribute("width", "80");
    imgTagPoster.setAttribute("height", "120");
    imgTagPoster.style.paddingTop = "5px";

    movieElement.className = "movieElement";
    genreTag.className = "genreTag";
    pTagReleaseYear.className = "releaseYear";


    movieElement.append(imgTagPoster);
    movieElement.append(pTagReleaseYear);
    movieElement.append(genreTag);
    movieElement.append(pTagTitle);
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
  document.querySelector('#movieRow').innerHTML = "";
}



