# CQRS-using-axon-Framework
## Principe de l'architecture CQRS 

***CRQRS (Command Query Responsibility Segregation) :*** 
une architecture logicielle qui repose sur un principe simple : la séparation, au sein d’une application, des composants de modification et de restitution de l’information sous les termes Command (pour l’écriture) et Query (pour la lecture).

**Les « Queries » :** renvoient un résultat et ne modifient pas l'état observable du système (sont donc exemptes d'effet de bord).

**Les « Commands » :** modifient l'état d'un système mais sans renvoyer de valeur.

![image](https://user-images.githubusercontent.com/85801662/219696629-475591f3-dfc2-4651-84ac-a9c5d0f3f5f5.png)

***
## Structure Du Projet 
Ce Projet se compose de plusieurs packages :

**Common-api :** contient les éléments en commun (Events/Commands/DTos/Queries...)

**Commandes :** c'est la partie dans laquelle on effectue des modifications (écriture), ce package contient les aggregates et les controllers .

**Query :** Dans cette partie on essaie de capter les modifications que nous avons effectuées afin de constituer la base de données et visualiser la base de données.

![image](https://user-images.githubusercontent.com/85801662/219712238-f145cdbe-e54d-475c-851c-cf9e7eccc8e0.png)

Essayons maintenant de détailler chaque package :

### 1.Commands :
![image](https://user-images.githubusercontent.com/85801662/219717835-7d283d48-41de-4a24-aff9-07aa098a1dd6.png)

**Le Package aggregates** contient la classe *AccountAggregate* dans cette classe on utilise l'annotation @Aggregate. Lorsque cette annotation est appliquée à une classe, elle informe le framework Axon que cette classe est un agrégat de racine et qu'elle doit être gérée en conséquence. Le framework Axon utilise l'annotation @Aggregate pour générer automatiquement le code nécessaire à la gestion de l'agrégat, y compris la gestion des événements, la validation des modifications et la gestion de la transaction. Cela permet aux développeurs de se concentrer sur la logique métier de leur application, plutôt que sur les détails de mise en œuvre de la gestion des agrégats.

Puis on déclare les différents attributs qui vous semblent légitimes, et il est indispensable lorsque vous utiliez AXON de déclarer dans l'aggrégat un constructeur sans paramètre .

