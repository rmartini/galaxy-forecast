# Galaxy Forecast

# Considerations & Premises

- Weather forecast for the next 10 years
- A Year has 365 days, therefore the days go from 0 until 3649
- The planets start aligned with the sun from the day 0
- The following climatic conditions are considered:
  - DROUGHT: When the planets and the sun are aligned
  - OPTIMUM: When the planets are aligned but not relative to the sun
  - RAIN: When the planets form a triangle area and the sun is inside that area
  - UNKNOWN: When the planets form a triangle area and the sun is not inside that area

# Running the application

```sh
./mvnw spring-boot:run
```
o
```sh
./mvnw clean package
java -jar target/galaxy-0.0.1-SNAPSHOT.jar
```

# API Endpoints

- http[s]://[domain]:[port]/weather/{day}
- https[s]://[domain]:[port]/periods/{weather}
- http[s]://[domain]:[port]/peakrain

### Documentation

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* http://mathworld.wolfram.com/Collinear.html
* http://www.ambrsoft.com/TrigoCalc/PointsInOut/PointInOut_.htm
