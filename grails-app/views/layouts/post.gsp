<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:layoutTitle default="Blagger" /></title>
    <g:layoutHead />

    <r:require module="blog"/>

    <r:layoutResources/>
</head>
<body>

<header class="jumbotron subhead" id="overview">
    <div class="container">
        <g:link controller="post" action="index"><h1>Blagger</h1></g:link>
        <p class="lead">Blogging for blaggers; it's what people think you know that matters</p>

        <g:link controller="post" action="create" class="btn btn-lg btn-primary">Blag something...</g:link>
    </div>
</header>

<div class="container">

    <g:layoutBody />

</div>

<r:layoutResources/>
</body>
</html>