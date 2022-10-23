# TP1_IFT2015

1. lire une grille(n'import quelle size)
2. Initialiser les bombes: 2 -> __ 2__ =>
> voir *initialiserBombes(LinkedQueue matrice)*


boucle:

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
