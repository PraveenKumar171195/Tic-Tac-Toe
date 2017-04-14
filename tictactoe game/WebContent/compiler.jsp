<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="tic.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
	<script src="jquery-linedtextarea.js"></script>
	<link href="jquery-linedtextarea.css" type="text/css" rel="stylesheet"/>
	<script>
$(function() {
	$(".lined").linedtextarea(
		{selectedLine: 1}
	);
});
</script>
        <style>
            .square{
              
            }

            .v{
                border-left:3px solid #000;
                border-right:3px solid #000;
            }

            .h{
                border-top:3px solid #000;
                border-bottom:3px solid #000;
            }
            body 
              {
                background-image: url("images/3.jpg");
                background-color: #cccccc;
                background-repeat: no-repeat;
                width: 100%;
                min-height: 500px;
                background-position: center;
                background-size: cover;
              }
        </style>
           
<%String h=(String)request.getSession().getAttribute("ans");
System.out.println("disp1"+h);%>
<script language="javascript">
function funct()  
{
    var d=document.getElementById("opt").value;
    if(d=="JAVA")
        {
		document.getElementById("text").value="import java.util.*; \nimport java.math.*; \nclass save \n{\n public static void main(String args[])\n  {\n\n  }\n}";
	    //document.getElementById("text").value="class ss";
        }
	else if(d=="C")
		{
		document.getElementById("text").value="#include<stdio.h>\n#include<conio.h>";
		}
	else if(d=="C++")
		document.getElementById("text").value="#include<iostream.h>";
}
</script>
    </head>
<body>
<form method="post" action="servlet"> 
<%servlet st=new servlet(); 
String c=st.question();
System.out.println(c);%>
<div class="container" style="margin-top:60px;opacity:0.9">
    <div class="row" style="border:solid 2px #ddd;margin-top:50px;box-shadow:0 0 10px #000;">
       <div class="col-lg-12" style="background-color:#fff;margin-top:0px;">
         <p style="text-align:center;color:#000;font-size:26px;padding:10px;"> Code to Play<br><%=c.substring(1)%></p>
       </div>
<input type="hidden" name="hid" value=<%=c.charAt(0) %>>
       <div class="col-lg-12" style="background-color:#fff;">
        <div class="col-lg-4" style="margin-bottom:20px;padding:0px">
          <!--<div class="col-lg-12" style="padding:0px">!-->
            <h2></h2>
              <select id="opt" name="lang" style="height:30px;width:200px;" onclick="funct()"> 
               <option>Select</option>
               <option value="JAVA">JAVA</option>
               <!--  <option value="C">C</option>
               <option value="C++">C++</option>--> 
              </select>  
         <!-- </div>!-->
      <%String df="";
      df=(String)session.getAttribute("code");
      if(df.isEmpty())
        df="";%> 
       <textarea name="txt" id="text" class="lined" rows="20" cols="60"><%=df %></textarea>
        </div> 
  <div class="col-lg-4" style="margin-top:200px;margin-bottom:20px;">
     <input type="file"  name="file"><br>
  </div>
  <div class="col-lg-4" style="margin-bottom:20px;"><br><br><br><br><br><br><br>
<textarea rows="5" cols="40"><%=h %></textarea>  
  </div>  
   <div class="col-lg-1" style="padding:0px;margin-bottom:10px;margin-left:0px;">
         &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="log" value="Run">
  </div>
  </div>                            
</div>
</div>

<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
 </form>
 <input type="hidden" id="as" value=<%=h%> >
 </body>
</html>