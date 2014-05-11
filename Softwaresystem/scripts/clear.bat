@echo off

SET thePath=..\tests\output

FOR %%f IN (%thePath%\*.out) DO (
	del %%f
	echo --- Zu loeschende Datei: %%f ---
)

ECHO Erfolgreich aufgeraeumt!

Pause