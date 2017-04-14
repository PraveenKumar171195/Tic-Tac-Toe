<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<style>
            .square{
                width:100px;
                height:100px;
            }

            .v{
                border-left:3px solid #000;
                border-right:3px solid #000;
            }

            .h{
                border-top:3px solid #000;
                border-bottom:3px solid #000;
            }
            .box{margin-left:40%}
            body {
    background-image: url("images/1.jpg");
    background-color: #cccccc;
                background-repeat: no-repeat;
                
                min-height: 400px;
                background-position: center;
                background-size: cover;
}
@media (max-width: 991px) { .box{margin-left:30%}}
@media  (max-width: 768px) { .box{margin-left:20%}}
@media  (max-width: 480px) { .box{margin-left:0%}}
       .button{
       width:100px;
       height: 100px;
       background-color: white;
       
       border: none;} 
        </style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script language="JavaScript">
var req;

var e;
var f;
function func(cname) 
{	
    if (window.XMLHttpRequest) 
{
 req = new XMLHttpRequest();
} 
else if (window.ActiveXObject) 
{
	req = new ActiveXObject("Microsoft.XMLHTTP");
}
    e=cname;
if(document.getElementById(cname).value==null)
document.getElementById(cname).value="X";
var url ="servlet?val="+cname;
req.onreadystatechange = getResponse; 
req.open("POST", url, true); 
req.send(null); 
}
function getResponse() 
{
if (req.readyState==4) 
{ 
	if (req.status == 200)  
		{	
			var t=req.responseText;
			f=t;
			if(t.substring(1,t.length)=="USER WON"||t.substring(1,t.length)=="DRAW") 
				document.getElementById(e).value="X";
			if(t.length==1||t.substring(1,t.length)=="CPU WON")
				{
				document.getElementById(e).value="X";
			    document.getElementById(t.charAt(0)).value="O";
				}if(t.length>1)
				{
				window.alert(t.substring(1,t.length));
				}
			if(t.length==1)
				window.location.href="compiler.jsp";
        } 
}
} 
//if(e!=0&&f!=0)
	//window.location("compiler.jsp");
</script>
</head>
<body background="E:/1.jpg">
<% String h1="",l1="",a="",b="",c="",d="",e="",f="",g="",h="",i="";
h1=(String)session.getAttribute("ss1");
l1=(String)session.getAttribute("ss2");
if(h1!=null)
{if(h1.contains("1"))
	a="X";
 if(h1.contains("2"))
	b="X";
if(h1.contains("3"))
	c="X";
 if(h1.contains("4"))
	d="X";
 if(h1.contains("5"))
	e="X";
if(h1.contains("6"))
	f="X";
 if(h1.contains("7"))
	g="X";
 if(h1.contains("8"))
	h="X";
 if(h1.contains("9"))
	i="X";
}
if(l1!=null)
{if(l1.contains("1"))
	a="O";
 if(l1.contains("2"))
	b="O";
 if(l1.contains("3"))
	c="O";
 if(l1.contains("4"))
	d="O";
 if(l1.contains("5"))
	e="O";
 if(l1.contains("6"))
	f="O";
 if(l1.contains("7"))
	g="O";
 if(l1.contains("8"))
	h="O";
 if(l1.contains("9"))
	i="O";
}
%>
 <p align="right"><a href="home.html"><input type="submit" name="log" value="Logout" style="font-weight:bold;font-size:14px;margin-top:40px;padding:10px;width:100px;border-radius:2px;border:solid thin #454545;background-color:#676767;color:#fff;"></a></p>
<form name="button" method="post" action="servlet">
<div class="container">

  <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top:190px;margin-bottom:10px;">
    <div class="box" style="">      
        <table style="background-color:#fff;opacity:0.3;border:solid 10px #000;box-shadow:0 0 10px #454545;border-radius:10px;margin-top:30px;" >
          <tr id="row1">
            <td class="square"><input type="button" style="font-size:xx-large;" name="but1" class="button"  id="1"  onclick="func(1)"  value=<%=a%>></td>
            <td class="square v"><input type="button" style="font-size:xx-large; name="but2" class="button" id="2" onclick="func(2)" value=<%=b %>></td>
            <td class="square"><input type="button" style="font-size:xx-large; name="but3" class="button" id="3" onclick="func(3)" value=<%=c %>></td>
          </tr>
          <tr id="row2">
            <td class="square h"><input type="button" style="font-size:xx-large; name="but4" class="button" id="4" onclick="func(4)" value=<%=d %>></td>
            <td class="square v h"><input type="button" style="font-size:xx-large; name="but5" class="button" id="5" onclick="func(5)" value=<%=e %>></td>
            <td class="square h"><input type="button" style="font-size:xx-large; name="but6" class="button" id="6" onclick="func(6)" value=<%=f %>></td>
          </tr>
          <tr id="row3">
            <td class="square"><input type="button" style="font-size:xx-large; name="but7" class="button" id="7" onclick="func(7)" value=<%=g %>></td>
            <td class="square v"><input type="button" style="font-size:xx-large; name="but8" class="button" id="8" onclick="func(8)" value=<%=h %>></td>
            <td class="square"><input type="button" style="font-size:xx-large; name="but9" class="button" id="9" onclick="func(9)" value=<%=i %>></td>
          </tr>
        </table>

         <input type="submit" name="log" value="reset" style="font-weight:bold;font-size:14px;margin-top:40px;padding:10px;width:100px;border-radius:2px;border:solid thin #454545;background-color:#676767;color:#fff;">
    </div>
        
</div>
</div>
</form>
</body>
</html>