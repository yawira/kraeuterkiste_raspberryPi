# Kraeuterkiste - Raspberry Pi module

This is Raspberry Pi module of our "Kraeuterkiste" project. 
It serves as a server as well as a client to the Java-Backend, enabling data exchange and handling HTTP-requests.

Project Kraeuterkiste is a REST-Application consisting of three parts - Frontend, Backend and Raspberry Pi module. It is build to enable users to take care of their plants via the internet. Let's say you are on holiday and want to check on your greens at home. Simply visit a website and push a button to take a photo of the current state or water it in case it looks dry. Check the charts monitoring the moisture of your plant's soil or the previous watering/lighting details. If your plant doesn't get enough light - no problem! Push a button and a 6500K LED light will help it grow.  

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
