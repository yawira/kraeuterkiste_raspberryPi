# Kraeuterkiste - Raspberry Pi module

This is Raspberry Pi module of our "Kraeuterkiste" project. 
It serves as a server as well as a client to the Java-Backend, enabling data exchange and handling HTTP-requests.

## Getting Started
### Prerequisites

- IDE
- Raspberry Pi & Kraeuterkisten-Set (Pump, Moisture Sensor, PiCamera, Breadboard...)
- MariaDB Relational Database
- Web Browser

### Application Properties

server.port=4567 <br>

backend.ip=192.168.43.26 <br>
backend.port=6060 <br>
backend.url=http://${backend.ip}:${backend.port} 

LED_PIN_NAME=GPIO 4 <br>
PUMP_PIN_NAME=GPIO 5 <br>
MOIST_CHANNEL_NAME=ANALOG INPUT 2 <br>
SPI_CHANNEL=0

IMG_WIDTH=800 <br>
IMG_HEIGHT=600 <br>
IMG_FORMAT=png <br>

## Technologies

- Java
- Maven - Dependency Management
- Pi4J Library
- Spring Boot - Microservice Framework
- RESTful Webservices
- Mockito
- Lombok

## Authors

- Ralf Jackels
- Lorenz Josten
- Evgeni Kozyr
- Laura Yawira Lewinski

![Image](./src/main/resources/Projektstruktur.JPG?view=true)
