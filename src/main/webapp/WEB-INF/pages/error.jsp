<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
    <head>
        <title>${exception.getClass().getSimpleName()}</title>
        <style type="text/css">h1 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:22px;} h2 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:16px;} h3 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:14px;} body {font-family:Tahoma,Arial,sans-serif;color:black;background-color:white;} b {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;} p {font-family:Tahoma,Arial,sans-serif;background:white;color:black;font-size:12px;} a {color:black;} a.name {color:black;} .line {height:1px;background-color:#525D76;border:none;}</style>
    </head>
    <body>
        <h1>${exception.getClass().getSimpleName()}</h1>
        <hr class="line" />
            <p><b>Error Code</b> ${exception.errorCode}</p>
            <p><b>Message</b> ${exception.message}</p>
        <hr class="line" /><h3>Apache Tomcat/8.5.13</h3>
    </body>
</html>
