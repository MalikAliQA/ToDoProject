// we set a constant to find the values from the search bar
const params = new URLSearchParams(window.location.search);

for(let param of params ){
    console.log("here i am",param)
    let id = param[1];
    console.log(id);
    getData(id)
    gettaskdata(id)
}


function getData(id){
    fetch('http://localhost:9092/user/read/'+id)
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

             document.querySelector("input#commentUserId").value = data.id
             document.querySelector("input#commentName").value = data.name       
          });
        }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });

      document
      .querySelector("form.viewRecord")
      .addEventListener("submit", function (stop) {
        stop.preventDefault();
        let formElements = document.querySelector("form.viewRecord").elements;
        console.log(formElements)
        let id=formElements["commentUserId"].value;
        let name=formElements["commentName"].value;
    
        let data = {

          "name": name,

      }
      console.log("Data to post",data)
      console.log(id)
      sendData(data,id)
    
      });
  
    
    function sendData(data,id){
      fetch("http://localhost:9092/user/update/"+id, {
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

    }

    function gettaskdata(id){
      fetch('http://localhost:9092/user/read/'+id)
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
    
          let table = document.querySelector("table");
          let newdata = Object.keys(data); // first record in the array pos 0
          // console.log(newdata)
          // console.log (data)
          // console.log (data[0])
          // console.log(data.tasks)
          // console.log(data.tasks[0])
          // console.log(data.tasks[0].body)
          // console.log("id of specific tasks: " + data.tasks[1].id)
          // console.log("test " + newdata)
          //console.log("fulldataintasks1: " + commentData[0].tasks[0])
          // Object.keys(newdata).forEach(function(prop) {
          //   console.log(newdata[prop])
            
          // });
          // Object.keys(data).forEach(function(prop) {
          //   console.log(data[prop])
            
          // });
          // Object.keys(data.tasks).forEach(function(prop) {//this one!!!!
          //   console.log(data.tasks[prop])
            
          // });
          // Object.keys(data.tasks[0]).forEach(function(prop) {
          //   console.log(data.tasks[0][prop])
          //   //Object.keys(data[prop]).forEach(function(newprop) {
          //     //console.log("third: " + data[prop][newprop])
    
          //   //});
          // });
    
        //   for(let i = 0, l = data.tasks.length; i < l; i++) {
        //      var obj = data.tasks[i];
        //      //console.log("each head: " +obj)
        //      console.log("each head: " +obj.id)//new
        //      console.log("each head: " +obj.name)
        //      console.log("each head: " +obj.body)
        // }
          
          createTableHead(table,newdata);
          createTableBody(table,data.tasks);//orgianl commentData
      
            });
          }
        )
        .catch(function(err) {
          console.log('Fetch Error :-S', err);
        });
      }
    
    function createTableHead(table,data){
      let tableHead= table.createTHead();
      let row = tableHead.insertRow();
      for(let keys of data){
          // console.log("data",data)
          let th = document.createElement("th");
          let text = document.createTextNode(keys);
          th.appendChild(text);
          row.appendChild(th)
        }
      }
      let th2 = document.createElement("th")
      let text2 = document.createTextNode("View");
      th2.appendChild(text2);
      //row.appendChild(th2);
      // let th3 = document.createElement("th")
      // let text3 = document.createTextNode("Delete");
      // th3.appendChild(text3);
      // row.appendChild(th3);
    
    function createTableBody(table,commentData){
      for(let commentRecord of commentData){
          let row = table.insertRow();
          for(let values in commentRecord){
              let cell = row.insertCell();
              let text = document.createTextNode(commentRecord[values]);
              console.log("values: " + commentRecord[values]);
              //console.log("tasks :" + commentRecord[values].tasks[0].body);
              cell.appendChild(text);
            }
            let newCell = row.insertCell();
            let myViewButton = document.createElement("a");
            let myButtonValue = document.createTextNode("View one")
            myViewButton.className ="btn btn-warning";
            myViewButton.href="readOne.html?id="+commentRecord.id
            myViewButton.appendChild(myButtonValue);
            newCell.appendChild(myViewButton)
          let newCellDelete = row.insertCell();
          // let myDelButton = document.createElement("button");
          // let myButtonValue1 = document.createTextNode("Delete a record")
          // myDelButton.className ="btn btn-success";
          // myDelButton.onclick = function(){
          //   deleteByid(commentRecord.id);return false;
          // };
          // myDelButton.appendChild(myButtonValue1);
          // newCellDelete.appendChild(myDelButton)
      }
    }