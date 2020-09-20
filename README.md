#Football Tournament

##Business Overview
```
The main aim of this project is to develop, test and deploy a microservices which will 
finds the standings of a team playing in league football match tournament using country name, 
league name & team name.
```

###Technical Overview
```
This is a spring boot based Microservices Java application which will create a jar and 
run as a standlaone application or run as docker container application or run on EC2 instance.
and find the standings of a team playing in league football match tournament using country name, 
league name & team name. 
```

###API Docs
Api to use
```
Request Method: Get

Request Url:
http://localhost:48080/football-tournament/standings/league/<league_name>/country/<country_name>/team/<team_name>?APIkey=<api-key>

Path variables:-
league_name
country_name
team_name

Request Params:
APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978

e.x:-
Request :-
----------
HTTP Method = GET
Request URI = /football-tournament/standings/league/Championship/country/England/team/Luton
Parameters = {APIkey=[9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978]}
Headers = []
Body = null


Response:-
---------
Status = 200
Headers = [Content-Type:"application/json"]
Content type = application/json
Body = {
         "Country Id": 41,
         "Country Name": "England",
         "League Id": 149,
         "League Name": "Championship",
         "Team Id": 2695,
         "Team Name": "Luton",
         "Overall League Position": 3
       }
```

###Logic behind the API
To find the standings of a team playing in league football match tournament using country name, 
league name & team name.
There are following methods follows:
```
Step-1 : Get all countries list which using football api - https://apiv2.apifootball.com/?action=get_countries
and find the country Object which equals to given country_name in football-tournament api.
e.x:-
https://apiv2.apifootball.com/?action=get_countries&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978
response :-
[
  {
    "country_id": "41",
    "country_name": "England",
    "country_logo": "https:\/\/apiv2.apifootball.com\/badges\/logo_country\/41_england.png"
  },
  {
    "country_id": "46",
    "country_name": "France",
    "country_logo": "https:\/\/apiv2.apifootball.com\/badges\/logo_country\/46_france.png"
  }
]

Step-2 : Get Competitions  based upon country_id using API https://apiv2.apifootball.com/?action=get_leagues&country_id=41
and find the League object which is equals to league_name in football-tournament api.
e.x:-
https://apiv2.apifootball.com/?action=get_leagues&country_id=41&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978
response :-
[
  {
    "country_id": "41",
    "country_name": "England",
    "league_id": "149",
    "league_name": "Championship",
    "league_season": "2020\/2021",
    "league_logo": "https:\/\/apiv2.apifootball.com\/badges\/logo_leagues\/149_championship.png",
    "country_logo": "https:\/\/apiv2.apifootball.com\/badges\/logo_country\/41_england.png"
  }
]

Step-3 : Get standings based upon league_id using API https://apiv2.apifootball.com/?action=get_standings&league_id=149
and get the Standing Object which is eqauls to team_name in football-tournament api.
e.x:-
https://apiv2.apifootball.com/?action=get_standings&league_id=149&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978
[
  .
  .
  .
  {
    "country_name": "England",
    "league_id": "149",
    "league_name": "Championship",
    "team_id": "2695",
    "team_name": "Luton",
    "overall_promotion": "Promotion - Championship (Play Offs)",
    "overall_league_position": "3",
    "overall_league_payed": "2",
    "overall_league_W": "2",
    "overall_league_D": "0",
    "overall_league_L": "0",
    "overall_league_GF": "3",
    "overall_league_GA": "1",
    "overall_league_PTS": "6",
    "home_league_position": "8",
    "home_promotion": "",
    "home_league_payed": "1",
    "home_league_W": "1",
    "home_league_D": "0",
    "home_league_L": "0",
    "home_league_GF": "2",
    "home_league_GA": "1",
    "home_league_PTS": "3",
    "away_league_position": "6",
    "away_promotion": "",
    "away_league_payed": "1",
    "away_league_W": "1",
    "away_league_D": "0",
    "away_league_L": "0",
    "away_league_GF": "1",
    "away_league_GA": "0",
    "away_league_PTS": "3",
    "league_round": "",
    "team_badge": "https:\/\/apiv2.apifootball.com\/badges\/2695_luton.png"
  }
  .
  .
  .
]
```

##How to run
##Run using jar
```
./gradlew clean build
java -jar build/libs/football-tournament-0.0.1-SNAPSHOT.jar --server.port=48080 
football-tournament rishi.raushan$ java -jar build/libs/football-tournament-0.0.1-SNAPSHOT.jar --server.port=48080 

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.3.3.RELEASE)

2020-09-21 01:16:16.277  INFO 83884 --- [           main] c.f.t.FootballTournamentApplication      : Starting FootballTournamentApplication on IN-GN-52228.local with PID 83884 (/Users/rishi.raushan/Downloads/workspace/football-tournament/build/libs/football-tournament-0.0.1-SNAPSHOT.jar started by rishi.raushan in /Users/rishi.raushan/Downloads/workspace/football-tournament)
2020-09-21 01:16:16.281  INFO 83884 --- [           main] c.f.t.FootballTournamentApplication      : No active profile set, falling back to default profiles: default
2020-09-21 01:16:17.514  INFO 83884 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 48080 (http)
2020-09-21 01:16:17.529  INFO 83884 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2020-09-21 01:16:17.529  INFO 83884 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.38]
2020-09-21 01:16:17.637  INFO 83884 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2020-09-21 01:16:17.637  INFO 83884 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1264 ms
2020-09-21 01:16:17.876  INFO 83884 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2020-09-21 01:16:18.179  INFO 83884 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 48080 (http) with context path ''
2020-09-21 01:16:18.192  INFO 83884 --- [           main] c.f.t.FootballTournamentApplication      : Started FootballTournamentApplication in 2.384 seconds (JVM running for 2.936)
2020-09-21 01:16:25.270  INFO 83884 --- [io-48080-exec-2] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2020-09-21 01:16:25.273  INFO 83884 --- [io-48080-exec-2] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2020-09-21 01:16:25.305  INFO 83884 --- [io-48080-exec-2] o.s.web.servlet.DispatcherServlet        : Completed initialization in 32 ms


Go to web browser or postman and do the get call.
e.x:-
http://localhost:48080/football-tournament/standings/league/Championship/country/England/team/Luton?APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978
```

##Run using docker container
```
./gradlew clean build
cp build/libs/football-tournament-0.0.1-SNAPSHOT.jar docker/baseImage/
docker-compose build
docker-compose up
football-tournament rishi.raushan$ docker-compose up
Creating football-tournament ... done
Attaching to football-tournament
football-tournament    | ################################
football-tournament    | Initializing service infra
football-tournament    | ################################
football-tournament    | 
football-tournament    |   .   ____          _            __ _ _
football-tournament    |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
football-tournament    | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
football-tournament    |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
football-tournament    |   '  |____| .__|_| |_|_| |_\__, | / / / /
football-tournament    |  =========|_|==============|___/=/_/_/_/
football-tournament    |  :: Spring Boot ::        (v2.3.3.RELEASE)
football-tournament    | 
football-tournament    | 2020-09-20 19:50:32.351  INFO 6 --- [           main] c.f.t.FootballTournamentApplication      : Starting FootballTournamentApplication on 04a2351520e8 with PID 6 (/football-tournament.jar started by root in /)
football-tournament    | 2020-09-20 19:50:32.368  INFO 6 --- [           main] c.f.t.FootballTournamentApplication      : The following profiles are active: local
football-tournament    | 2020-09-20 19:50:35.635  INFO 6 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
football-tournament    | 2020-09-20 19:50:35.670  INFO 6 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
football-tournament    | 2020-09-20 19:50:35.670  INFO 6 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.38]
football-tournament    | 2020-09-20 19:50:35.889  INFO 6 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
football-tournament    | 2020-09-20 19:50:35.890  INFO 6 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 3300 ms
football-tournament    | 2020-09-20 19:50:36.476  INFO 6 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
football-tournament    | 2020-09-20 19:50:37.164  INFO 6 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
football-tournament    | 2020-09-20 19:50:37.182  INFO 6 --- [           main] c.f.t.FootballTournamentApplication      : Started FootballTournamentApplication in 6.466 seconds (JVM running for 8.441)

Go to web browser or postman and do the get call.
e.x:-
http://localhost:48080/football-tournament/standings/league/Championship/country/England/team/Luton?APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978
```

##docker images
```
docker-microservices rishi.raushan$ docker images
REPOSITORY                                           TAG                 IMAGE ID            CREATED              SIZE
football-tournament                                  latest              fbef99ac8cd9        About a minute ago   258MB
```
