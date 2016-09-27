<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>教学管理系统</title>
    <meta name="keywords" content="北京师范大学珠海分校 教学管理系统">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
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
                <input type="text" class="form-control" name="username" id="username" placeholder="用户名" />
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><span class="fa fa-lock fa-fw"></span></span>
                <input type="password" class="form-control" name="password" id="password" placeholder="密码" />
            </div>
        </div>
        <div class="form-group">
             <button class="btn btn-primary form-control" type="submit">登录</button>
        </div>
        <g:if test="${flash.message}">
        <div class="alert alert-danger">
            ${flash.message}
        </div>
        </g:if>
    </form>
</div>
<script type="application/javascript">
    setTimeout(function() {
        document.getElementById("username").focus();
    }, 10);
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
    <a href="https://dl.google.com/release2/11sx7qq3lmncwfwkxnj8si8rq6me2v498iogaovom8062r5g3bwn5s6l64nt9yzjvua2kxq5sdnwbgsab2b569l5ey529uyw5nkv/51.0.2704.84_chrome_installer.exe">Google Chrome v51 32位</a>或
    <a href="https://dl.google.com/release2/rkoforcsrkbke4r1b5s6gdj2a2aonn3m3l2d2l1j4pwgv2lcpjro3vz0ymb6snqur8lfocj6f8erh4ad91qndxeegy5chbkt167/51.0.2704.84_chrome_installer_win64.exe">Google Chrome v51 64位</a>。
</div>
<![endif]-->
</body>
</html>
