<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>教学管理系统</title>
    <meta name="keywords" content="北京师范大学珠海分校 教学管理系统">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/static/css/lib/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/lib/font-awesome.min.css"/>
    <style>
        body {
            background-color: #eee;
        }

        form {
            max-width: 330px;
            margin: 0 auto;
            padding: 40px 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <form action="/uaa/login" method="POST" id="loginForm">
        <div class="form-group" style="text-align: center;">
            <h1 style="font-size: 1.5rem;">北京师范大学珠海分校</h1>
            <h2 style="font-size: 1.25rem;">教学管理系统</h2>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><span class="fa fa-user fa-fw"></span></span>
                <input class="form-control" name="username" id="username" placeholder="用户名" />
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><span class="fa fa-lock fa-fw"></span></span>
                <input type="password" class="form-control" name="password" id="password" placeholder="密码" />
            </div>
        </div>
        <div class="form-group">
             <button class="btn btn-primary form-control">登录</button>
             <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
        <g:if test="${flash.message}">
        <div class="alert alert-danger">
            ${flash.message}
        </div>
        </g:if>
    </form>
</div>
<script type="application/javascript">
    if (sessionStorage) {
        sessionStorage.clear();
    }
    setTimeout(function() {
        document.getElementById("username").focus();
    }, 0);
</script>
<!--[if IE]>
<style>
    .container {
        display: none;
    }
    .not-support {
        padding: 30px;
        margin: 30px;
    }
</style>
<div class="not-support">
    系统不支持此版本的浏览器，Windows XP或Vista用户请下载
    <a href="http://dl.google.com/release2/h8vnfiy7pvn3lxy9ehfsaxlrnnukgff8jnodrp0y21vrlem4x71lor5zzkliyh8fv3sryayu5uk5zi20ep7dwfnwr143dzxqijv/49.0.2623.112_chrome_installer.exe">Google Chrome v49</a>，
    Windows 7及以上用户请下载
    <a href="http://dl.google.com/release2/G849XZrb8cM_60.0.3112.101/60.0.3112.101_chrome_installer.exe">Google Chrome v60 32位</a>或
    <a href="http://dl.google.com/release2/AI4UQYLNTPy6_60.0.3112.101/60.0.3112.101_chrome_installer.exe">Google Chrome v60 64位</a>。
</div>
<![endif]-->
</body>
</html>
