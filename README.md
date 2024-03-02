## For running app there are some env. variable that you can customized it based on your env. they are same as follow:
- server-address --> default valuse is:localhost
- server-port --> default valuse is:8080
- max-albums-number --> default valuse is: 5
- max-books-number --> default valuse is: 5
- google-api-address --> default valuse is: https://www.googleapis.com
- itunes-api-address --> default valuse is:https://itunes.apple.com
- core-pool-size --> default valuse is:https:3
- max-pool-size --> default valuse is:https:8
- queue-size --> default valuse is:https:15

--> you can run the project after build it:
## After build with maven you have 2 approachs to run:

### As a docker container(you have image)--> the deafult port of the application is 8080 but you can change it by using env. variable.
--> the dafult IP address is: localHost but you can change it by using related env. variabale.

to run the docker container, execute following command:
		- docker run -d -p 8080:8080 your-image-name
 you can add any envirenemnt variable by -e command

### As a war file(in java environement)
 for running with following command
		- nohup java -jar "search-service-0.0.1-SNAPSHOT.war" .& tail -f nohup.out 
