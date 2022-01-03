SET applicationName=BricksJaume2
set className=%1
SET sourceDir=app\visionx\apps\bricksjaume2\screens
SET targetDir=..\WEB-INF\lib\%applicationName%.jar
SET zipDir=..\WEB-INF\lib\%applicationName%.zip
SET COPYCMD=/Y


copy %targetDir% %zipDir%
7z d %zipDir% %sourceDir%\%className%WorkScreen.class

:replace1
move %zipDir% %targetDir%
IF exist %zipDir% GOTO replace1
SET targetDir=..\WEB-INF\lib\%applicationName%server.jar
SET zipDir=..\WEB-INF\lib\%applicationName%server.zip
copy %targetDir% %zipDir%
GOTO replace2



:replace2
7z d %zipDir% %sourceDir%\%className%.class
move %zipDir% %targetDir%
IF exist %zipDir% GOTO replace2
EXIT












