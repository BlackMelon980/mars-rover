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

## Space

### Initialize

Values:

- width
- height
- obstaclesCount

```
curl --location --request POST 'http://localhost:8080/space/init' \
--header 'Content-Type: application/json' \
--data-raw '{
    "width": 5,
    "height" : 5,
    "obstaclesCount" : 7
}'
```

### Show space info

```
curl --location --request GET 'http://localhost:8080/space'
```

### Add a new obstacle

Values:

- x
- y

```
curl --location --request POST 'http://localhost:8080/space/obstacle' \
--header 'Content-Type: application/json' \
--data-raw '{
    "x": 1,
    "y": 2
}'
```

### Delete an obstacle

Values:

- x
- y

```
curl --location --request DELETE 'http://localhost:8080/space/obstacle' \
--header 'Content-Type: application/json' \
--data-raw '{
    "x": 0,
    "y": 2
}'
```

## Rover

### Update rover

Values:

- position : { x, y }
- direction: [ N, E, S, W ]

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

Possible values: [ F, B, R, L ]

```
curl --location --request PUT 'http://localhost:8080/rover/move' \
--header 'Content-Type: application/json' \
--data-raw '["F","R"]'
```