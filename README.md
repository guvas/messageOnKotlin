Denne applikasjonen lar deg å logge inn til meldingerside hvor du kan motta meldinger fra andre brukere eller
sende meldinger til andre brukere som finnes i DB "Bruker" tabellen.

Loginn form laget veldig simple, uten noe Authontication sjekk.
Eneste sjekk ved logge inn er på brukernavn, hvis brukernavn finnes i tabellen "Bruker" da han logger seg inn og kan se alle meldingene som ble sendt til bruker,
hvis brukeren finnes ikke i "Bruker" tabellen, da opprettes en ny bruker.

## Design pattern 
Model View Controller (MVC)

## Teknologier som brukt
* Spring-boot (rammeverk)
* Maven
* Kotlin (BackEnd)
* H2 (embedded DB)
* Thymeleaf (view template)
* Bootstrap, Datatables.js(jquery plugin), Ajax og JavaScript



Ved start av applikasjonen kjøres migration " create tabbeler " og "insert rader" 
som oppretter to test brukere "Guvanch" og "test";
og to meldingene som ble sendt til hverandre.

## Kjør applikasjonen
```bash
$ mvn spring-boot:run
```

## Login side
 på  http://localhost:8080 i nettleseren og fyll inn en brukernavn