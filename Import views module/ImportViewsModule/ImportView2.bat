SET applicationName=BricksJaume2
set className=%1
SET zipDir=..\WEB-INF\lib\%applicationName%.zip
SET sourceDir=app\visionx\apps\bricksjaume2\screens
SET targetDir=..\WEB-INF\lib\%applicationName%.jar
SET COPYCMD=/Y


copy %targetDir% %zipDir%
7z a %zipDir% %sourceDir%\%className%WorkScreen.class


:replace1
move %zipDir% %targetDir%
IF exist %zipDir% GOTO replace1
SET targetDir=..\WEB-INF\lib\%applicationName%server.jar
SET zipDir=..\WEB-INF\lib\%applicationName%server.zip
copy %targetDir% %zipDir%
7z a %zipDir% %sourceDir%\%className%.class
GOTO replace2


:replace2
move %zipDir% %targetDir%
IF exist %zipDir% GOTO replace2

EXIT











