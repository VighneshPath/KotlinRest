function display()
{
  var url1 = document.getElementById("URL").value;
  var content1 = document.getElementById("contentt").value;
 // console.log(content1);

  var xhttp = new XMLHttpRequest();
  xhttp.open('POST', url1);

  xhttp.setRequestHeader('Content-type','application/json');
  xhttp.send(content1);
  
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) 
    {
      //console.log(xhttp.responseText);
      document.getElementById("Response").innerHTML = xhttp.responseText;
    }
    else
    {
      document.getElementById("Response").innerHTML ="Error"
    }
};
}