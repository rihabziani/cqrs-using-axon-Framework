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
#### 1.1. Le Package Aggregate :
il contient une seule classe qu'on a appelé *AccountAggregate*

![image](https://user-images.githubusercontent.com/85801662/219717835-7d283d48-41de-4a24-aff9-07aa098a1dd6.png)

 Dans cette classe on utilise l'annotation @Aggregate. Lorsque cette annotation est appliquée à une classe, elle informe le framework Axon que cette classe est un agrégat de racine et qu'elle doit être gérée en conséquence. Le framework Axon utilise l'annotation @Aggregate pour générer automatiquement le code nécessaire à la gestion de l'agrégat, y compris la gestion des événements, la validation des modifications et la gestion de la transaction. Cela permet aux développeurs de se concentrer sur la logique métier de leur application, plutôt que sur les détails de mise en œuvre de la gestion des agrégats.

Puis on déclare les différents attributs qui vous semblent légitimes, et il est indispensable lorsque vous utiliez AXON de déclarer dans l'aggrégat un constructeur sans paramètre .

Cette classe contient deux types de fonctions : *fonctions de décision* et *fonctions d'évolution* , dans une fonction de décision on utilise l'annotation @CommandHandler  pour gérer la commande et produire les événements correspondants, Le commande Handler est responsable de la validation de la commande et de la prise de décisions sur les actions à effectuer en réponse, Il est également responsable de la production d'événements qui représentent l'état modifié de l'agrégat après avoir traité la commande, Ce type de fonction reçoit comme paramètre une commande.  Tandis que dans la fonction d'évolution on utilise l'annotation @EventSourcingHandler pour mettre à jour l'état de l'agrégat en réponse à l'événement, et cette fonction reçoit comme paramètre un *Event* 

#### 1.2. Le Package Controller :
il contient une seule classe appelé AccountCommandController :
![image](https://user-images.githubusercontent.com/85801662/219758311-24da26af-e034-4469-9f0e-bf82c79c1b48.png)
On utilise l'annotation @RestController qui combine les fonctionnalités de deux autres annotations Spring : @Controller et @ResponseBody. Elle indique que la classe annotée est un contrôleur de REST qui retourne directement des données sous forme d'un objet serialisé, plutôt que de renvoyer une vue HTML.Les annotations telles que @GetMapping, @PostMapping, @PutMapping et @DeleteMapping peuvent être utilisées pour marquer les méthodes correspondantes de la classe avec les verbes HTTP appropriés.
