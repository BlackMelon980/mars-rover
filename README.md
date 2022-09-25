# MARS ROVER 🚀

You’re part of the team that explores Mars by sending remotely controlled vehicles to the surface of the planet. Develop
an API that translates the commands sent from earth to instructions that are understood by the rover.

## Requirements

- You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
- The rover receives a character array of commands.
- Implement commands that move the rover forward/backward (f,b).
- Implement commands that turn the rover left/right (l,r).
- Implement wrapping from one edge of the grid to another. (planets are spheres after all)
- Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle,
  the rover moves up to the last possible point, aborts the sequence and reports the obstacle.

---

## Prerequisites

In order to run this application, you need the following:

- JDK 11+ installed
- Apache Maven 3.8.1+

## Run the application

You can run your application in dev mode that enables live coding using:

```
mvn quarkus:dev
```

## Testing

Use the following command to test the application:

```
mvn test
```

---

## Endpoints

## Planet

### Planet initialization

Initialize planet with width and height and obstacles with random position.

Body:

- width: Integer
- height: Integer
- obstaclesCount: Integer

```
curl --location --request POST 'http://localhost:8080/planet/init' \
--header 'Content-Type: application/json' \
--data-raw '{
    "width": 5,
    "height" : 5,
    "obstaclesCount" : 7
}'
```

### Show planet info

Get current planet, obstacles and rover (if exists).

```
curl --location --request GET 'http://localhost:8080/planet'
```

### Add new obstacle

Add new obstacle to the planet at specified position.

Body:

- x: Integer
- y: Integer

```
curl --location --request POST 'http://localhost:8080/planet/obstacle' \
--header 'Content-Type: application/json' \
--data-raw '{
    "x": 1,
    "y": 2
}'
```

### Delete an obstacle

Delete obstacle at specified position.

Body:

- x: Integer
- y: Integer

```
curl --location --request DELETE 'http://localhost:8080/planet/obstacle' \
--header 'Content-Type: application/json' \
--data-raw '{
    "x": 0,
    "y": 2
}'
```

## Rover

### Update rover

Update rover position and direction.

Body:

- position :
    - x: Integer
    - y: Integer
- direction: String
  ( possible values: [ N, E, S, W ] )

```
curl --location --request PUT 'http://localhost:8080/rover' \
--header 'Content-Type: application/json' \
--data-raw '{
    "position" : {
        "x" : 2,
        "y" : 3
    },
    "direction" : "E"
}'
```

### Move rover

Move rover using a list of commands.

Body: [String]

Possible values: [ F, B, R, L ]

```
curl --location --request PUT 'http://localhost:8080/rover/move' \
--header 'Content-Type: application/json' \
--data-raw '["F","R"]'
```