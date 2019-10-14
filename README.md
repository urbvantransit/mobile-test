# Prueba técnica - Mobile Team
#### Aplicación para compartir ubicaciones en mapa 
Al dejar pulsado sobre en punto en el mapa de la tab "Enviar", se envían las coordenadas a la base de datos remota. Se pueden enviar multiples ubicaciones a la vez.
Las ubicaciones enviadas desde la tab "Enviar" podran ser vistas en la tab "Mostrar" de esta app, cada ubicación tiene un ciclo de vida de 10 segundos. Al transcurrir este tiempo, la ubicación desaparecerá.

#### Bitacora de desarrollo

  - Fork al proyecto original
  - Inicialización del proyecto en Android Studio
  - Analisis de requerimientos
  - Creación de estructura de carpetas
  - Creación de fragments y navegación (tabs)
  - Integración de mapas en los fragments
  - Definición de modelo Ubicacion
  - Integración de Firebase y creación de base de datos
  - Implementación de envio de coordenadas a base de datos
  - Pintado de ubicaciones guardadas en base de datos
  - Desarrollo de algoritmo de actualización y borrado de ubicaciones para evitar casos de ConcurrentModificationException
  - Implementación de Hapatic feedback (vibración)
  
#### Dependencias y tecnologías

  - Material components
  - Google maps
  - Firebase core
  - Firebase database
  - Kotlin <3
