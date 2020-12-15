fetch('http://localhost:9092/user/read/')
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }

      // Examine the text in the response
      response.json().then(function(commentData) {
        //console.log("first: " + commentData)
       

        let table = document.querySelector("table");
        let data = Object.keys(commentData[0]); // first record in the array pos 0
        console.log(data)
        console.log (commentData)
        console.log (commentData[0])
        console.log(commentData[0].tasks)
        console.log(commentData[0].tasks[0])
        console.log(commentData[0].tasks[0].body)
        console.log("id of specific tasks: " + commentData[0].tasks[0].id)
        console.log("test " + data)
        //console.log("fulldataintasks1: " + commentData[0].tasks[0])
        Object.keys(data).forEach(function(prop) {
          console.log(data[prop])
          
        });
        Object.keys(commentData).forEach(function(prop) {
          console.log(commentData[prop])
          
        });
        Object.keys(commentData[0]).forEach(function(prop) {
          console.log(commentData[0][prop])
          
        });
        Object.keys(commentData[0].tasks).forEach(function(prop) {//this one!!!!
          console.log(commentData[0].tasks[prop])
          
        });
        Object.keys(commentData[0].tasks[0]).forEach(function(prop) {
          console.log(commentData[0].tasks[0][prop])
          //Object.keys(data[prop]).forEach(function(newprop) {
            //console.log("third: " + data[prop][newprop])

          //});
        });

        for(let i = 0, l = commentData[0].tasks.length; i < l; i++) {
           var obj = commentData[0].tasks[i];
           //console.log("each head: " +obj)
           console.log("each head: " +obj.id)
           console.log("each head: " +obj.name)
           console.log("each head: " +obj.body)
      }
        
        createTableHead(table,data);
        createTableBody(table,commentData);//orgianl commentData
        
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

  function createTableHead(table,data){
    let tableHead= table.createTHead();
    let row = tableHead.insertRow();
    for(let keys of data){
        console
        console.log("data: " , data)
        let th = document.createElement("th");
        let text = document.createTextNode(keys);
        th.appendChild(text);
        row.appendChild(th)  
      }
    }
    let th2 = document.createElement("th")
    let text2 = document.createTextNode("View");
    th2.appendChild(text2);
    row.appendChild(th2);
    let th3 = document.createElement("th")
    let text3 = document.createTextNode("Delete");
    th3.appendChild(text3);
    row.appendChild(th3);

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
          myViewButton.href="readOneuser.html?id="+commentRecord.id
          myViewButton.appendChild(myButtonValue);
          newCell.appendChild(myViewButton)
        let newCellDelete = row.insertCell();
        let myDelButton = document.createElement("button");
        let myButtonValue1 = document.createTextNode("Delete a record")
        myDelButton.className ="btn btn-success";
        myDelButton.onclick = function(){
          deleteByid(commentRecord.id);return false;
        };
        myDelButton.appendChild(myButtonValue1);
        newCellDelete.appendChild(myDelButton)
    }
}

function deleteByid(id){
  fetch("http://localhost:9092/user/delete/"+id, {
      method: 'delete',
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      },
    })
    
    
  }