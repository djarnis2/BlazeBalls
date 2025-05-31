To start the ScoringSystem:
PS: mvn -f ScoringSystem\pom.xml spring-boot:run

To start the main game (in a new PS terminal):
PS: java --module-path "mods-mvn;libs" --module Core/dk.sdu.cbse.main.Main

(VM options: --module-path mods-mvn;libs --add-modules Core)
