# ISEPVelibProject

## Génération de la base de données (avec MySQL Workbench) :

###Dans une instance MySQL connectée au port 3306 de votre adresse (localhost:3306) :

Créez un nouveau schéma appelé **theisepsvelibproject**<br>
File > Open SQL Script > sélectionnez **database.sql** à la racine de ce projet<br>
Double-cliquez sur le schéma **theisepsvelibproject** créé dans l'arborescence afin de vous y connecter<br>
Query > Execute (all or selection)

## Lancement avec IntelliJ : 
###**File > Project Structure...**<br>
Onglet **Project** (à Gauche)<br>
Vérifie que le "Project SDK" est bien à 1.8 et que le "Project Language level" est bien à 8<br>
Pas sur que ça serve, mais au cas où : "Project Compiler Output" doit être "racine/du/projet/out"<br>
<br>
Onglet **Libraries** (à Gauche)<br>
La croix verte > Java<br>
Sélectionnez le dossier libs/ qui est dans le dossier racine du projet<br>
Ok ok ok ok ok ok <br>
Idem pour le dossier slick/lib<br>
<br>
###**Retours sur le code**
Dans la class Main<br>
La flèche verte à droite > Run<br>
