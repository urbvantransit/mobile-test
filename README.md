# Prueba técnica - Mobile Team
La prueba tiene como objetivo evaluar tus habilidades como desarrollador.

# Descripción
La prueba técnica consiste en desarrollar una pequeña aplicación con las siguientes características. 

* La aplicación (emisora) deberá de enviar las coordenadas de la ubicación en la cual el usuario haya presionado durante 3 segundos sobre el mapa. Como confirmación para la aplicación (emisora) se deberá de emitir un [hapatic feedback](https://material.io/design/platform-guidance/android-haptics.html#usage) como confirmación que se envió la ubicación.
* La aplicación (receptora) deberá de mostrar la coordenada que envió la aplicación (emisora) dentro del mapa con un marcador el cual deberá desaparecer 10 segundos despues de haberse mostrado. 
* Dicha información se deberá enviar en tiempo real usando Firebase Real Time Database.
* Se da por entendido que la función de emisor y receptor es una sola aplicación, la función se ve definida por la selección en el tab superior como se muestra en la siguiente imagen.

![Views](https://github.com/urbvantransit/mobile-test/blob/master/views.png?raw=true)

## Consideraciones generales

- Se deberá escribir un README en donde se describa las tareas que se llevaron a cabo para desarrollar la prueba.
- Se evaluará la calidad del código, uso de git y practicas de programación, por lo que te recomendamos el código de tu proyecto.
- Eres libre de mejorar y/o incluir nuevas funcionalidades que demuestren tus habilidades. Ten en cuenta que también evaluaremos las mejoras y funcionalidades que incluyas.
- El proyecto deberá realizarse en Kotlin y eres libre de agregar dependencias.

## Entrega

Se deberá de hacer un _fork_ a este repositorio, y continuar el desarrollo de la prueba sobre el mismo. Una vez terminada la prueba se deberá hacer un _Pull Request_ al repositorio original.
