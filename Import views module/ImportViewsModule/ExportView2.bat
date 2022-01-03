SET applicationName=BricksJaume2
set className=%1
SET jarDir=..\WEB-INF\lib\%applicationName%.jar
SET sourceDir=app\visionx\apps\bricksjaume2\screens
SET COPYCMD=/Y



7z e %jarDir% %sourceDir%\%className%WorkScreen.class -oExportedScreens -y
SET jarDir=..\WEB-INF\lib\%applicationName%server.jar
7z e %jarDir% %sourceDir%\%className%.class -oExportedScreens -y




