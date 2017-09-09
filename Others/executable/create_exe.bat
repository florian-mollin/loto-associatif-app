@echo off

rem variables
set launch4jXml=.\launch4j.xml
set innoSetupIss=.\innoSetup.iss
set icon=.\icon.ico
set jre=C:\Program Files (x86)\Java\jre1.8.0_131
set lotojar=..\..\Loto\build\libs\Loto-all.jar
set launch4jExe=C:\Program Files (x86)\Launch4j\launch4jc.exe
set innoSetupExe=C:\Program Files (x86)\Inno Setup 5\Compil32.exe

rem verification variables
for %%f in (
	"%launch4jXml%"
	"%innoSetupIss%"
	"%icon%"
	"%jre%"
	"%lotojar%"
	"%launch4jExe%"
	"%innoSetupExe%"
) do (
	if not exist "%%f" (
		echo ERR : %%f n'existe pas
		exit /b
	)
)

rem creation du dossier de travail
if exist work rmdir /s /q work
mkdir work

rem copie des fichiers dans le dossier de travail
copy "%launch4jXml%" work
copy "%innoSetupIss%" work
copy "%icon%" work
xcopy "%jre%" work\jre /e /i /Y
copy "%lotojar%" work\Loto.jar

rem execution des utilitaires pour créer l'executable
"%launch4jExe%" work\%launch4jXml%
"%innoSetupExe%" /cc work\%innoSetupIss%

rem suppression du dossier de travail et deplacement de l'executable
move work\Loto_setup.exe .
rmdir /s /q work
