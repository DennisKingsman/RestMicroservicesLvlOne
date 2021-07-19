# Author
## DennisKingsman
# Resources
[youtube-guide-mcs-lvl-1](https://www.youtube.com/playlist?list=PLqq-6Pq4lTTZSKAFG6aCDVDP86Qx4lNas)  
[youtube-guide-mcs-lvl-2](https://www.youtube.com/playlist?list=PLqq-6Pq4lTTbXZY_elyGv7IkKrfkSrX5e)  
# Recomended to study
## RestTemplate
[baeldung.com/rest-template](https://www.baeldung.com/rest-template)  
[spring-restful-client-resttemplate-example](https://howtodoinjava.com/spring-boot2/resttemplate/spring-restful-client-resttemplate-example/)  
[doc](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html)  
## WebClient
[spring-5-webclient](https://www.baeldung.com/spring-5-webclient)  
[webclient-get-post-example](https://howtodoinjava.com/spring-webflux/webclient-get-post-example/)  
[web-reactive-doc](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)  
## Spring Cloud
[spring.io/cloud](https://spring.io/cloud)  
[spring.io/projects/spring-cloud](https://spring.io/projects/spring-cloud) and from there pay attention to [spring-cloud-netflix](https://spring.io/projects/spring-cloud-netflix)  
[spring-cloud-netflix-eureka](https://www.baeldung.com/spring-cloud-netflix-eureka)  
[example](https://betacode.net/11733/understanding-spring-cloud-eureka-server-with-example)  
# Testing
## Order
first statrs discovery-server wich includes euruka server  
then starts other micro-services
## Eureka-ui
[localhost:8761](http://localhost:8761/)
## Mvn
to build jars user `mvn install`  
to demonstrate loadBalanced restTemplate start same app with different instance on different ports using  
```
java -Dserver.port=8201 -jar <app-jar-name>.jar  
java -Dserver.port=8202 -jar <app-jar-name>.jar
```
# Use
RestTemplate  
WebClient (by commented)  
Spring Cloud (to use euruka server and hystrix)  
# Lvls
## Mcs lvl one
Creating three services which is  
movie-catalog-service  
movie-info-service  
rating-data-service  
Creating euruka server and enable euruka-clients to connect services between each other  
Connect movie-info-service to api.themoviedb.org as db  
## Mcs lvl two
Provide fault tolerance and resilience   
Set timeout to restTemplate  
Circuit bracker pattern  
Use Hystrix(because of the proxy two HystrixCommand cannot be in one Spring bean)  
Add Hystrix dashboard  
Bulkhead pattern with thredPool  
#Issues 
## Mcs-lvl-one
-
## Mcs-lvl-two
With adding hystrix dashboar apps getting fallback every time  
Unable to connect to Command Metric Stream. Reason - proxy, no matter how to config app.properties  
Hystrix dashboard commit deleted  
Movie-info-service sometimes falls with timeout while calling ext movie DB  
Hystrix thread pool options not testet, just added to show it  
