@echo off

::

:: Gradle start up script for Windows

::



set DIR=%~dp0

set APP_BASE_NAME=%~n0

set APP_HOME=%DIR%



set DEFAULT_JVM_OPTS=



if defined JAVA_HOME goto findJavaFromJavaHome



set JAVA_EXE=java.exe

%JAVA_EXE% -version >NUL 2>&1

if "%ERRORLEVEL%" == "0" goto execute



echo.

echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

echo.

echo Please set the JAVA_HOME variable in your environment to match the location of your Java installation.



goto fail



:findJavaFromJavaHome

set JAVA_HOME=%JAVA_HOME:"=%

set JAVA_EXE=%JAVA_HOME%\bin\java.exe



if exist "%JAVA_EXE%" goto execute



echo.

echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%

echo.

echo Please set the JAVA_HOME variable in your environment to match the location of your Java installation.



goto fail



:execute

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar



set CMD_LINE_ARGS=



set CLASSPATH=%CLASSPATH%



"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% -Dorg.gradle.appname=%APP_BASE_NAME% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %CMD_LINE_ARGS%



:fail

set EXIT_CODE=%ERRORLEVEL%

if "%EXIT_CODE%" == "0" goto exit

set EXIT_CODE=1



:exit

exit /b %EXIT_CODE%

