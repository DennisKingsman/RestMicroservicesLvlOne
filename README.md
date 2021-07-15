# Author
## DennisKingsman
# Resources
[youtube-guide](https://www.youtube.com/playlist?list=PLqq-6Pq4lTTZSKAFG6aCDVDP86Qx4lNas)
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
[http://localhost:8761/](eureka)
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
Spring Cloud (to use euruka server)  
