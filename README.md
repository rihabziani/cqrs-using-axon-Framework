# cqrs-using-axon-Framework
## Principe de l'architecture CQRS 

***CRQRS (Command Query Responsibility Segregation) :*** 
une architecture logicielle qui repose sur un principe simple : la séparation, au sein d’une application, des composants de modification et de restitution de l’information sous les termes Command (pour l’écriture) et Query (pour la lecture).

**Les « Queries » :** renvoient un résultat et ne modifient pas l'état observable du système (sont donc exemptes d'effet de bord).

**Les « Commands » :** modifient l'état d'un système mais sans renvoyer de valeur.

![image](https://user-images.githubusercontent.com/85801662/219696629-475591f3-dfc2-4651-84ac-a9c5d0f3f5f5.png)


