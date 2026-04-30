# Passwort-Policy

## Muss-Kriterien (Basis):

mindestens 8 Zeichen,
mindestens eine Ziffer (0–9),
Groß- und Kleinbuchstaben (beides muss vorkommen),
nicht in einer Liste häufiger/schwacher Passwörter.

## Optional (Bonus-Policy):

mindestens ein Sonderzeichen (definiere erlaubte Menge),
Mindestanzahl unterschiedlicher Zeichengruppen (z. B. 3 von 4: Groß, Klein, Ziffer, Sonderzeichen).

## Build, Run & Tests

### Lokal ausführen
Um das Projekt zu bauen und alle Tests (Unit-Tests & Checkstyle) auszuführen, nutzen Sie:
```bash
mvn clean verify
