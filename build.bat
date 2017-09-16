@echo off
set /p build=< build.number
set /A build=build+1

echo Build number is: %build%

echo %build% > build.number
 
mvn clean install -Dbuild.number=%build% -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dpso.vco.allowedMask=vef -Dmaven.test.skip=true