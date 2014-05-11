@echo off
SET prog=..\NimGame.jar
SET inputDir=..\tests\input
SET outputDir=..\tests\output

REM Test ob executable existiert
IF NOT EXIST %prog% GOTO NOEXE

REM Durchtesten ob die Verzeichnistruktur stimmt
REM Hauptverzeichnis
IF NOT EXIST %inputDir% GOTO NOINPUT
IF NOT EXIST %outputDir% GOTO NOOUTPUT

REM laufen über alle Dateien
FOR %%f IN (%inputDir%\*.in) DO (
	REM abspalten des Pfades von der Datei
	echo -------------- Eingabedatei: %%f --------------
	echo -------------- Ausgabedatei: %outputDir%\%%~nf.out ------------
	REM Ausführen des Beispiels
	CALL %prog% %%f %outputDir%\%%~nf.out
	ECHO.
)

GOTO END

:NOEXE
ECHO ************** Skriptfehler **************
ECHO Die ausführbare Datei %prog% fehlt.
ECHO Bitte kompilieren Sie das Programm oder
ECHO ueberpruefen Sie ob sich diese Skript im richtigen Ordner befindet.
GOTO END

:NOINPUT
ECHO ************** Skriptfehler **************
ECHO Der Ordner %inputDir% fehlt.
ECHO Somit kann kein Test ausgefuehrt werden.
ECHO.
GOTO END

:NOOUTPUT
ECHO ************** Skriptfehler **************
ECHO Der Ordner %outputDir% fehlt.
ECHO Somit kann kein Test ausgefuehrt werden.
ECHO.
GOTO END

:END
Pause