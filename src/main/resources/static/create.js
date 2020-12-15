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
          "id": userid
        }


    }
    console.log("Data to post",data)
    sendData(data)
  
      // postData(noteTitle,noteBody)
    });


function sendData(data){
    fetch("http://localhost:9092/task/create", {
        method: 'post',
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