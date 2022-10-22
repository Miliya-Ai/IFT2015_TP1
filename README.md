# TP1_IFT2015

1. lire une grille(n'import quelle size)

<<<<<<< Updated upstream
   boucle:
2. touver le bomb
3. souligner le bomb active (numéro 2)
4. supprimer la ligne explosé
5. chaque chiffre à côté de bomb
6. vérifier si c'est 0 ou 1
7. Si c'est 1, on ajoute 1
8. Si c'est 0, on fait rien

   fini(Si c'est juste 2/o)
=======
<<<<<<< Updated upstream
boucle:

2. Initialiser les bombes: 2 -> __ 2__ => 
> voir *initialiserBombes(LinkedQueue matrice)*
3. touver le __ 2__
> voir *activeBombeInactive(LinkedQueue matrice)* 
4. chaque chiffre à côté de bomb
   1. vérifier si c'est 0 ou 1
      1. Si c'est 1, on ajoute 1
      2. Si c'est 0, on fait rien
   > voir *incrementerBombesAdjacentes(LinkedQueue matrice)*
   > 
   > voir *bombesAdjacentes(int indexBombeActiveExplose)*
5. supprimer/changer la ligne explosé: __ 2__ -> '2
   1. changer en '2 permet de nous rappeler que c'est une bombe déjà explosé
   > voir *activeBombeInactive(LinkedQueue matrice)*

7. fini(Si c'est juste '2)
> voir *isDone()*
=======

2. touver le bomb 2
3. souligner"()" le bomb active (numéro 2)

   boucle:
4. trouver le bomb avec "(2)"
5. supprimer la ligne"()" explosé
6. chaque chiffre à côté de bomb
7. vérifier si c'est 0 ou 1
8. Si c'est 1, on ajoute 1, et mettre un "()"
9. Si c'est 0, on fait rien

   fini(Si c'est juste 2/o)

1  2  0
0  0  1
3*3
---> 1  (2)  0
     0   0   1
     0   1   1

-->   [1,2,0,0,0,1,0,1,1]
-->(dequeue) [2,0,0,0,1,0,1,1,(enqueue)**1**]
-->(dequeue) [0,0,0,1,0,1,1,1,(2)]
-->(dequeue) [1,(2),0,0,0,1,0,1,1]
index = dequeue(premier element) // i = 1
premier boucle----->
while i != 1:
boucle

deuxieme boucle----->
while i != 1:
boucle : -->(dequeue) [1,(2),0,0,0,1,0,1,1]
   ----> touver les chiffres (-1,+1,-3,+3)
   boucle(4 fois):
   ----> vérifier si c'est exist 
   ----> s'il y a exist: boucle enqueue n fois:
                           if chiffre = (enqueue) == 1: plus 1
                           si 0 || 2, fais rien
   ----> sinon break 

dequeue : (2), 0,0,0,1,0,1,1



---> 1   2   0
     0   0   1
     0   1   1
---->2   2   0
>>>>>>> Stashed changes
>>>>>>> Stashed changes
