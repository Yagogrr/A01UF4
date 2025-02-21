# De quina lògica d'aplicació s'encarrega el Patró DAO?
El patró DAO (Data Access Object) s'encarrega de la lògica d'accés i manipulació de dades, actuant com una capa d'abstracció entre l'aplicació i la base de dades. Específicament les operacions CRUD y Count(aquesta l'he posta jo). Les responsabilitats principals són establir i gestionar connexions amb la base de dades i garantir la persistència de dades.

#  Per què considereu què és útil el patró DAO i en què us ha servit?
El patró DAO ofereix diversos beneficis clau: en termes de reutilització, permet usar els DAOs en diferents parts de l'aplicació mitjançant la clase abstracte i l'interfície, evitant la duplicació de codi i compartint la lògica d'accés entre projectes. Pel que fa a la seguretat, gestiona adequadament les connexions. En la meva experiència personal, ha simplificat la implementació de les operacions CRUD i Count.

# Heu hagut de fer cap ajust al vostre codi d’aplicació (Main, Controladors, Vistes, altres classes que no siguin DAO, etc.) ? Si és així, detalleu de forma breu quins canvis heu fet i per què?
En el meu cas particular, he hagut de canviar bastantes coses del Main, ja que jo treballava d'una manera una mica diferent a la que treballa ara la classe abstracta. Però, deixant això de banda, simplement he afegit el mètode count a la interfície i a la classe abstracta.

# D’igual forma que s’ha fet a l’enunciat, completeu el diagrama de classes de l’activitat A01 de la UF2 incorporant les interfícies, la classe abstracta i els DAOs. Per acoblar això, cal que relacioneu cada classe del model amb el seu DAO (sols aquelles classes que heu treballat a l’A03, no totes!!! ):
![Diagrama de classes](/src/main/resources/diagrama.png)

# [1 punt] Per últim valoreu el paper que hi juga la classe abstracta. És en tots els casos necessària? En el cas de l’activitat A02 de la UF2, on vau emprar JDBC, penseu que seria d’utilitat?   
Gràcies a aquesta classe abstracta, els DAOs només tenen 3 o 4 línies de codi, mentre que abans cada DAO en tenia moltes més. Ara només ens hem de preocupar pels mètodes específics.