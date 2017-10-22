# blogger
Rest Api for mini blogging application developed using Spring Boot with JWT authentication and Hiberanate for Postgresql database.

## Api Endpoint :

<b>Endpoint</b> : /user<br/>
<b>Request Type</b> : POST<br/>
<b>Description </b>	: Register new user<br/>
<br/>
<b>Endpoint</b> : /user/{username}<br/>
<b>Request Type</b> : GET<br/>
<b>Description </b>	: Retrieve user details<br/>
<br/>
<b>Endpoint</b> : /authenticate<br/>
<b>Request Type</b> : POST<br/>
<b>Description </b>	: Authenticates and return JWT Auth Token<br/>
<br/>
<b>Endpoint</b> : /posts<br/>
<b>Request Type</b> : GET<br/>
<b>Description </b>	: Retrieve all posts<br/>
<br/>
<b>Endpoint</b> : /posts<br/>
<b>Request Type</b> : POST<br/>
<b>Description </b>	: Adds New Post<br/>
<br/>
<b>Endpoint</b> : /posts/{username}<br/>
<b>Request Type</b> : GET<br/>
<b>Description </b>	: Retrieves posts of specific user<br/>
<br/>
<<b>Endpoint</b> : /posts/{postId}<br/>
<b>Request Type</b> : GET<br/>
<b>Description </b>	: Retirieves post<br/>
<br/>
<b>Endpoint</b> : /posts/{postId}/comments<br/>
<b>Request Type</b> : GET<br/>
<b>Description </b>	: Retrieve Comments from of the post<br/>
<br/>
<b>Endpoint</b> : /posts/{postId}/comments<br/>
<b>Request Type</b> : POST<br/>
<b>Description </b>	: Add Comments to post<br/>
<br/>
<b>Endpoint</b> : /posts/{postId}/comments/{commentId}<br/>
<b>Request Type</b> : PUT<br/>
<b>Description </b>	: Update Comment<br/>
<br/>
<b>Endpoint</b> : /posts/{postId}/comments<br/>
<b>Request Type</b> : DELETE<br/>
<b>Description </b>	: Delete Comment<br/>
<br/>



