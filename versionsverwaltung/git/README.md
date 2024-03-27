# git - Versionsverwaltung

## Eigenschaften
- FOSS
- extrem schnell
- extrem platzsparend
- speichert alle Versionen einer Datei 
- Projektstände sind Menge von Dateien und Ordnern in bestimmter Version
- eingespielte Änderungen sind nur schwer zu löschen

## Repository
- stark verlustlos komprimierte Datenbank aller Versionen aller Dateien plus Ordnerstruktur
- kann offline alle Versionen zur Verfügung stellen
- neue Versionen können offline zum Repository hinzugefügt werden
- zwei Repositories können gemergt werden und enthalten dann alle Versionen beider Repositories
  - fremdes Repository pullen
  - Merges automatisch ausführen
  - falls Mergekonflikte manuell nacharbeiten
  - gemergtes Repository pushen
- TODO: push, pull, pull-request, fetch, fork, sync

## TODO
- commit
- ammend commit
- tag
- push tags
- branch

## Löschen von Änderungen
- bei Veröffentlichung von Interna
- bei rechtlichen Anforderungen
- Verhindert nicht die Weiterverbreitung, bei Kopien von Dritten
- Vorgehen bei selbstgehosteten Repositories
  - spezielle Parameter TODO
- Vorgehen bei fremdgehosteten Repositories
  - unter Umständen ist direktes Entfernen nicht möglich
  - lokale Kopien erstellen
  - Absichern, dass ausreichend lokale Kopien vollständig an verschiedenen Orten existieren
  - Löschung in lokaler Kopie vornehmen
  - Änderungen überprüfen
  - fremdgehostetes Repository löschen
  - lokale geänderte Kopie erneut einspielen
- Löschen von Änderungen sollte möglichst vermieden werden
