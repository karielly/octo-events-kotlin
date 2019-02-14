# Octo Events

### Requirements:

<ul>
	<li>Java 8</li>
	<li>Postgresql</li>
	<li>ngrok</li>
  <li>Gradle</li>
</ul>

### How to start?

<ol>
  <li>Clone the project  
		<blockquote>
      git clone https://github.com/karielly/octo-events-kotlin.git
		</blockquote>
	</li>
  <li>Set JAVA_HOME to a Java 8 JDK</li>
  <li>Open the project folder in the terminal</li>
  <li>Execute
	  </br>
	  <ul>
		  <li>gradle run</li>
	  </ul>
  </li>
</ol>

<p>The project runs on the port 7000. The port can be changed in application.conf</p>

<p>The database configuration properties can be changed in: application.conf</p>

## Routes:

<p>POST / Listen to github webhook.</p>

<p>GET /issues/:number/events Lists all events of the issue.</p>


END..
