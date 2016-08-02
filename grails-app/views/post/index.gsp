
<html>

<head></head>

<body>



<g:if test="${tags}">
	Tags: 
	<g:each in="${tags}" var="tag">
		<g:link action="filter-by-tag" id="${tag[0]}">
			<button class="btn btn-primary" type="button">${tag[0]} <span class="badge">${tag[1]}</span></button>
		</g:link>
	</g:each>
</g:if>

<br/>

<g:if test="${searchTag}">
<br/>
<div class="alert alert-warning" role="alert">Results filtered by tag: <b>${searchTag}</b></div>
</g:if>


<hr>

<g:each in="${posts}" var="post">

    <h1>${post.title}</h1>
    <p class="lead">By <span class="text-info">${post.email}</span> on <g:formatDate format="dd/MM/yyyy @ HH:mm" date="${post.dateCreated}"/></p>

    ${post.content}

    <br/>

    <g:if test="${post.tag?.name}">
    Tag: <g:link action="filter-by-tag" id="${post.tag?.name}"><span class="label label-success">${post.tag?.name}</span></g:link>    
	</g:if>

    <hr/>

</g:each>

<g:paginate total="${total}" max="5"/>

</body>

</html>