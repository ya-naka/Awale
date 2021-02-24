# Awale

<h2> INTRODUCTION </h2>

Le but de ce projet est de permettre à deux joueurs de jouer une partie d’Awalé ou une de
ses variantes. L’Awalé et ses 11 variantes constituent l’ensemble des jeux de Mancala, jeux de
société populaires en Afrique.<br><br>
Le plateau de jeu de l’Awalé est composé de deux rangées de 6 trous chacune. Chaque trou
comporte 4 graines au début du jeu. Chaque joueur possède une rangée de trous.<br><br>
Le but du jeu consiste à ramasser le plus de graines. Pour ce faire, à tour de rôle, chaque
joueur s’empare des graines se trouvant dans un trou (de sa rangée ou non selon la variante) et les
sème une à une dans les trous suivants, dans le sens inverse des aiguilles d’une montre. Le joueur a
la possiblité de ramasser des graines à la suite de son semis. Les conditions pour pouvoir ramasser
des graines dépendent de la variante choisie. Le joueur gagnant la partie est ainsi celui qui aura
ramassé le plus graines à la fin du jeu.<br><br>
La partie se termine si plus aucune prise ne peut être réalisée ou lorsque les joueurs
décident, d’un commun accord, en saisissant la valeur 0, d’arrêter la partie.<br><br>
L’enjeu de ce projet est de proposer une architecture du programme permettant une
intégration aisée de toutes les variantes de jeux de Mancala.<br><br>

![Capture](https://user-images.githubusercontent.com/66320697/108989123-8ba54180-7695-11eb-9bbf-4a37151eca1e.PNG)

<h2> PRESENTATION </h2>
L'application permet de jouer à 5 jeux de Mancala :
<ul>
  <li>Awalé</li>
  <li>Kakua</li>
  <li>Owani</li>
  <li>Wali</li>
  <li>Tamtam-Apachi</li>
</ul>
<br>
Affichage au début d'une partie d'Awalé :
![Capture1](https://user-images.githubusercontent.com/66320697/108993109-65ce6b80-769a-11eb-9daa-d3c8c60c29a9.PNG)
<br><br>
Affichage au cours d'une partie d'Awalé :
![Capture2](https://user-images.githubusercontent.com/66320697/108993373-c493e500-769a-11eb-9b0b-afe3298e75cb.PNG)
<br><br>
Affichage à la fin d'une partie d'Awalé :
![Capture2](https://user-images.githubusercontent.com/66320697/108993476-eb521b80-769a-11eb-8540-3f0b3acfb018.PNG)


<h2> BILAN DU PROJET </h2>
<h3> Démarche </h3>
L’un des enjeux principaux de ce projet étant de réaliser une architecture du programme
telle que l’intégration des variantes non abordées soit aisée, j’ai débuté la réalisation de ce projet par
une analyse attentive de l’ensemble des variantes. A l’aide d’un tableur Excel, j’ai répertorié les
différentes caractéristiques de chaque variante (nombre de trous, nombre de rangées, conditions
pour semer, conditions pour ramasser, etc…). Cette analyse m’a permis de mettre en lumière une
importante ressemblance entre l’Awalé et quatre autres variantes. En effet, le Kakua, l’Owani, le
Tamtam-Apachi et le Wali possèdent un plateau similaire à celui de l’Awalé. Le nombre de joueurs, le
nombre de graines dans chaque trou au début de la partie, la répartition des rangées et le sens de
parcours d’un semis sur le plateau sont identiques.<br><br>
A partir de là, je me suis focalisée sur ces cinq variantes et j’ai essayé de comprendre leur
fonctionnement. Je me suis aperçue qu’une partie suivait le même enchainement d’actions pour
chaque joueur, à tour de rôle : choisir un trou, semer depuis ce trou, ramasser les graines si les
conditions sont réunies. J’en ai ainsi déduis une structure commune aux jeux de mancala. Cette
structure est traduite dans l’interface Mancala. On y trouve également les différents affichages
nécessaires à l’application, communs à toutes les variantes. A cela s’ajoutent les méthodes
nécessaires au déroulement du jeu, telles que la saisie, le passage au joueur suivant ou encore
l’indicateur de fin de partie.<br><br>
J’ai par la suite détaillé le fonctionnement de chaque action du jeu pour chaque variante,
pour pouvoir factoriser le plus possible mon code. J’ai ainsi réalisé que les seules véritables
différences entre ces variantes se trouvaient dans les actions de semer et de ramasser, ainsi que dans
les conditions pour pouvoir semer ou ramasser. J’ai alors décidé de créer une classe AwaléStandard
réunissant toutes les caractéristiques communes aux cinq variantes et de créer une classe pour
chaque variante, héritant chacune de la classe AwaléStandard. La classe AwaléStandard n’est pas un
jeu de Mancala à proprement parlé, mais réunit les méthodes communes à la majorité des variantes,
j’ai ainsi fait le choix d’en faire une classe abstraite. Enfin, j’ai spécialisé les méthodes représentant
les actions spécifiques à chaque variante, dans leur classe respective.<br><br>
Une fois cette version du programme terminée, je me suis demandée comment le rendre
plus compatible à l’intégration des autres variantes. Là encore, après analyse, je me suis rendue
compte que les différences majeures se trouvaient dans la composition du plateau. J’ai ainsi fait en
sorte que chaque variante puisse créer un plateau en fonction de ses besoins. Elles peuvent donc, en
appelant le constructeur de leur classe mère AwaléStandard, préciser le nombre de trous dont est
composé le plateau et le nombre de graines présentes dans chaque trou au début de la partie.<br><br>
<h3> Difficultés rencontrées </h3>
La partie la plus difficile de ce projet a été le travail d’analyse fait en amont. La réalisation
d’un tableur Excel m’a toutefois aidée dans cette tâche. Le choix des méthodes composant l’interface 
Mancala a également été compliqué. Le plus difficile étant de savoir faire la différence entre les
méthodes indispensables au fonctionnement d’un jeu de Mancala et celles superflues.<br><br>
De plus, l’organisation des paquetages et des classes dans ceux-ci m’a posé problème. J’ai eu
du mal à organiser mon programme de telle sorte qu’il y ait le moins de dépendances possibles.<br><br>
<h3> Ce qui est réussi </h3>
Le programme permet à deux joueurs de s’affronter pour une partie, au choix, d’Awalé (1),
de Kakua (2), d’Owani (3), de Tamtam-Apachi (4) ou de Wali (5). Ils peuvent également choisir quel
joueur débutera la partie (0 pour SUD et 1 pour NORD). Dans le cas d’une partie d’Awalé, ils peuvent
en plus choisir s’il veulent jouer à une variante où seules les graines précédant le dernier trou du
semis peuvent être ramassées (1).<br><br>
Les affichages de l’application respectent les consignes énoncées dans le sujet, à chaque
tour, le plateau de jeu ainsi que les scores de chaques joueurs sont affichés ; un message indique au
joueur courant de jouer ; le nombre de graines ramassées par le joueur courant est affiché ; le nom
du gagnant est affiché en fin de partie.<br><br>
Les saisies des joueurs sont vérifiées. Si le joueur choisit un trou vide ou ne respectant pas les
conditions de la variante pour pouvoir semer, un message l’invite à recommencer sa saisie, tant
qu’elle n’est pas valide.<br><br>
Le code du programme a été factorisé au maximum, permettant ainsi d’avoir peu de code
dans chaque classe de jeu de Mancala. Seules les méthodes représentant des actions ou conditions
spécifiques à chaque variante ont été spécialisées.<br><br>
L’absence de « setters » est également un plus. Aucune propriété de classe ne peut être
directement modifiée, seules les actions nécessaires au déroulé du jeu sont présentes.
La classe AwaléStandard, dont héritent les classes des cinq variantes, a facilement permis de
proposer ces cinq versions. Il n’a donc pas été long de coder ces classes.
La classe AwaléStandard propose de nombreuses méthodes caractéristiques aux jeux de
Mancala, permettant ainsi, hypothétiquement, une intégration plus aisée d’autres variantes de
l’Awalé.<br><br>
L’ajout d’une fabrique de jeux de Mancala facilite l’utilisation, dans la classe Application, des
variantes ajoutées a posteriori.<br><br>
Note : les numéros représentent les valeurs envoyées en paramètres du main.<br><br>
<h3> Ce qui peut être amélioré </h3>
Ayant eu des difficultés à organiser les paquetages et les classes, une réorganisation de ceux-ci pour réduire le nombre de dépendances entre eux et rendre les paquetages plus stables est nécessaire.<br><br>
L’Oware peut se jouer à plusieurs joueurs, l’état actuel du code de la classe AwaléStandard
ne permet pas entièrement de répondre aux spécificités d’un tel mode de jeu. Des améliorations
pourraient être apportées à ce sujet.
