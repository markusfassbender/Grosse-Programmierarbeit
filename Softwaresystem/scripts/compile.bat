@echo off

cd ..

ECHO Kompiliere Programm...

dir /s /B src\*.java > src\sources.txt
CALL javac -d bin\ -verbose @src\sources.txt
ECHO Kompilieren abgeschlossen!

cd bin
Echo Erstelle Executable jar File
Call jar cvfm ..\NimGame.jar manifest.txt *
cd ..

ECHO Erstellen Abgeschlossen!

cd scripts
Pause