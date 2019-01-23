# Introduction : 

Le mode de scrutin, c'est le système de vote par lequel on désigne le gagnant d'une élection. L'élection présidentielle française, comme la grande majorité des élections actuelles, utilise un mode de scrutin qu'on appelle "majoritaire" qui consiste à sélectionner un candidat parmi plusieurs et le candidat ayant le plus de voix remporte l'élection. On sait cependant depuis plusieurs siècles, grâce notamment aux travaux de Borda, de Condorcet, et de Arrow sur le sujet, que ce mode de scrutin donne des résultats paradoxaux.

# Qu'est-ce que le jugement majoritaire ?

Le jugement majoritaire est un nouveau système de vote développé par deux chercheurs CNRS (organisateurs de cette expérience) comme remède aux maladies du scrutin majoritaire. Il repose sur une théorie mathématique développée depuis une dizaine d’années et publiée dans un livre paru chez MIT Press en 2011. Chaque électeur attribue à chaque candidat/e une mention «Très bien», «Bien», «Assez bien», «Passable», «Insuffisant» ou «A Rejeter». Le/la candidat/e élu/e est celui ou celle qui obtient la meilleure mention soutenue par une majorité. Au cas où deux candidat(e)s ont la même mention majoritaire, celui ou celle qui gagne (ou perd) est celui ou celle avec le plus d'électeurs lui attribuants strictement plus (ou strictement moins) que sa mention majoritaire ([voir la BD](https://www.jugementmajoritaire2017.com/#bd)).

# Quel est le but de cette expérience ?

Cette expérience à grande échelle réalisée en partenariat avec LaPrimaire.org a pour but de :

1. familiariser les électeurs avec les récentes avancées scientifiques sur les modes de scrutin ;
2. tester les avancées en vote électronique utilisant une urne électronique transparente et sécurisée basée sur la technologie blockchain.



# Outils et Langages :



| Outils et Langages |
| :----------------: |
|       SCALA        |
|       PLAY !       |
|      CanvasJS      |
|    SBT Builder     |
|       CSS 3        |
|       HTML 5       |
|     BootStrap      |



# Données d'entrée :

les votes vont être insérer sous forme d'un fichier csv { data.csv } portant la
structure suivante :

* Chaque ligne contien un vote d'une personne pour les 6 candidats  et il attribue les mentions suivantes :

  > 1 => A rejeter 
  >
  > 2 => Insuffisant 
  >
  > 3 => Passable 
  >
  > 4 => Assez Bien 
  >
  > 5 => Bien 
  >
  > 6 => Trés Bien

  les votes sont separée par des " ; "

  on aura un fichier qui prend la forme suivante :

  ```csv
  1;2;3;4;6;6
  2;2;3;4;6;6
  2;2;3;4;6;5
  ```

  

# Compilation du projet : 

Avant de compiler il faut specifier le chemin vers le CSV qui va etre traiter pour avoir des resultats :

+ il faut allez dans JugMag/app/services 

+ Puis ouvrir le fichier Vote.scala 

+ allez a la ligne suivante  :

  

  ```scala
  val result = content.readFromFile("home/nabil/IdeaProjects/JugMaj/public/data.csv")
  ```

  

  et changer le chemin existant vers le chemin ou vous allez mettre le fichier data.csv :

  ```scala
  val result = content.readFromFile("/Chemin/vers/mon/projet/JugMaj/public/data/data.csv")
  ```

  

  Puis Aprés pour compiler il faut ce placer l'interieur du projet { /JugMag } et lancer la commande suiante sur le terminal :

  ```shell
  sbt run 
  ```

   le serveur va se lancer et on pourra accéder a la page d'acceuil de l'application avec l'adresse suivante :

  ```http
  http://localhost:9000/
  ```

Aprés cela il suffira juste de se rendre sur la page d'acceuil , on aura une explication avec des images sur le jugement majoritaire et on poura générer des resultats de notre scrutin contenu dans notre { data.csv } sur la page :

```http
http://localhost:9000/action?
```



# Ameloration : 

​	J'ai commencer aussi par implementer un FileUploader qui fonctionne si on lui donne le bon chemin mais malheursement par manque de temps j'ai pas pu reussir a router vers le bon directory pour sauvagarder le fichier sur mon serveur et le récuperer pour travailler dessus plustard.



# Conculsion :

​	grâce a se projet j'ai pu apprendre a programmer en scala et
particulièrement travailler sur ( Play ! ) un framework serveur web que je
trouve un peut difficile a prendre en mains mais une fois on qu’on s y habitue
on peut pas s'en passer et cela parce-que j'ai eu plusieurs difficulté lie a l’accès
a mes fichiers cela m'a aussi interpeller sur le fait que le framework play a une
très bonne sécurité d’accès au contenus , j'ai du la désactiver afin d’éviter
plusieurs erreurs et finir mon projet rapidement vu le temps imparti

​	Ce framework aura un grand avantage pour la gestion de notre projet
quand ce dernier grand en contenus .