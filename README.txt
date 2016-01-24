These examples are purely meant to illustrate the posibilities of Akka. We tried to make these examples as simple as possible. The disadvantage was that we had to use some less nice constructions. If you want some more real life examples and best practices than we advice you to look at the official Akka examples.

Some of the things we did in these examples, but what you should not do:
- Don't use Strings directly to send messages between actors, use objects.
- Don't duplicate code, use shared projects like the messageprotocol project.
- Don't use println, use log statements.


- In general
* Most applications can be started with 'sbt run'.
* Do not forget to change ports, ip addresses etc.

- Remote actors messageprotol
This is used to store the messages used by 'Remote actors coordinator' and 'Remote actors worker' to prevent code duplication.
To use this project simple publish it in the local repo with the command 'sbt publishLocal'

- Some examples are meant to run on one machine. Other examples are meant to run on multiple machines. We're working on making the examples support both options.

- Akka cluster with routing and other examples that you want to deploy
Create fat jar with the command 'sbt assembly'.
Start the fat jar with the command 'java -jar *.jar'.
For the cluster start the fat jar with the command 'java -jar *.jar [IP_ADDRESS]', please enter the IP address of the machine on which the application is running.





