Si volem modificar algo de la clase importada:

-------------Vision X-----------------------
Pas 1:

-Modificar el parametre applicationName a la classe ImportWorkViews.java i ImportWorkViewsWorkSceen.java  amb el nom de la applicacio
- Copiar ImportWorkViews.java en apps\BricksJaume2\src.server\com\sibvisions\module\user\screens
- Copiar ImportWorkViewsWorkSceen.java en apps\BricksJaume2\src.client\com\sibvisions\module\user\screens
Pas 2:

- Modificar la primera linea al script 'ImportWorkViewsConfig' ' USE [BricksJaume]' i posar el nom de la applicacio fer lo mateix a la linea 32
- Executar el script a la BD 'ImportWorkViewsConfig'
Pas 3:
- Crear la carpeta 'webapps'  'C:\Users\jaume.lopez\VisionX\VisionX 1\rad\apps\APPLICATION NAME'
- Crear la carpeta 'APPLICATION NAME' a webapps  
- Copiar la carpeta 'ImportViewsModule' a la carpeta root de la app per exemple 'C:\Users\jaume.lopez\VisionX\VisionX 1\rad\apps\APPLICATION NAME\webapps\bricksAEMES'
- la ruta final per exemple serie 'C:\Users\jaume.lopez\VisionX\VisionX 1\rad\apps\APPLICATION NAME\webapps\bricksAEMES\ImportWorkViewsConfig'


-------------------TOMCAT----------------------

Pas 4:
- Copiar la carpeta 'ImportViewsModule' a la carpeta root de la app per exemple C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps\APPLICATION NAME

