const requestObject = {
  method : "GET",
  "conent-type" : "application/json",
  redirect : "follow"
}

window.onload = function fetchData(){
  let urlstr = `http://localhost:8080/getAllMovies`;
  fetch(urlstr, requestObject)
    .then(response => response.json())
    .then((data) => gotMovies(data));

};

function gotMovies(data){
  const printMovies = data.map(movieobj => movieobj.movieTitle)
  document.getElementById("printMovies").innerHTML = '<li>' + printMovies.join('</li><li>') + '</li>';
}



