# SAE32_2023 : Petit Tableur

Pour utiliser le tableur et comprendre les différentes interactions que l'on peut rencontrer, \
il est conseillé d'utiliser le `terminal` car il affiche plusieurs indications nécessaires à la compréhension.

Pour que les formules fonctionnent, il est important de respecter la syntaxe de celles-ci : comme il est dit dans le sujet, la notation utilisée est préfixée.  
Exemple : `+ 2.66 * B7 0.33`  

Il est également important de laisser un `espace` entre chaque `opérateur` et `opérande`, sinon la formule sera comptabilisée comme fausse.

Chaque cellule peut se trouver dans l'une des quatre situations possibles, visuellement distinctes :
- La cellule contient une formule vide : `gris clair`
- La cellule contient une formule correcte et calculable : `vert`
- La cellule contient une formule correcte mais incalculable : `jaune`
- La cellule contient une formule incorrecte : `rouge`

Commandes :

- `make build` : compile le projet.
- `make run` : compile le projet et lance le programme.
- `make clean` : supprime le dossier build qui contient les classes compilées.
- `make jar` : crée une archive jar `PetitTableur.jar` du projet.
- `make run-jar` : exécute l'archive jar `PetitTableur.jar` du projet.

Répartition des différents dossiers :

- [Fichiers sources](./src/fr/iutfbleau/sae32_2023) : tous les fichiers de code contenus dans un package `sae32_2023`.
- [Diagramme de classes](./uml) : le diagramme de classes de notre projet contenu dans un dossier `uml`.
- [Rapport](Rapport%20SAE32_2023.pdf) : le rapport d'avancement de notre projet.


---

© Baptiste BLANCHON, Ayoub AMMARA & Samet DURAN  
- [Sujet disponible ici](http://www.iut-fbleau.fr/sitebp/sae3/32_2023/48MDP89QG58Q67NH.php).
