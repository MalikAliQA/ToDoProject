// we set a constant to find the values from the search bar
const params = new URLSearchParams(window.location.search);

for(let param of params ){
    console.log("here i am",param)
    let id = param[1];
    console.log(id);
    getData(id)
}

function getData(id){
    fetch('http://localhost:9092/task/read/'+id)
      .then(
        function(response) {
          if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
          // Examine the text in the response
          response.json().then(function(data) {
             console.log("MY DATA OBJ",data)

             //document.querySelector("input#commentPostId").value = data.colour
             document.querySelector("input#commentUserId").value = data.userid
             document.querySelector("input#commentId").value = data.id
             document.querySelector("input#commentName").value = data.name
             document.querySelector("input#commentBody").value = data.body
    
          });
        }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });
    }


    document
    .querySelector("form.viewRecord")
    .addEventListener("submit", function (stop) {
      stop.preventDefault();
      let formElements = document.querySelector("form.viewRecord").elements;
      console.log(formElements)
      let userid=formElements["commentUserId"].value;
      let id =formElements["commentId"].value;
      let name=formElements["commentName"].value;
      let body =formElements["commentBody"].value;

  
      let data = {
        "body": body,
        "name": name,
        "user": {
          "id": 2
        }


    }
    console.log("Data to post",data)
    console.log(id)
    sendData(data,id)
  
      // postData(noteTitle,noteBody)
    });

  
  function sendData(data,id){
    fetch("http://localhost:9092/task/update/"+id, {
        method: 'put',
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        },
        body:JSON.stringify(data)
      })
      .then(function (data) {
        console.log('Request succeeded with JSON response', data);
      })
      .catch(function (error) {
        console.log('Request failed', error);
      });
    }