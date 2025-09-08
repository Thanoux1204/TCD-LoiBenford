# TCD-LoiBenford
Création d'un script permettant l'application de la transformée en cosinus discrète sur une image afin d'appliquer par la suite la loi de Benford. Il s'agit d'une expérimentation en Java.
## Objectif
Le but est d'analyser une image pour détecter une éventuelle manipulation ou falsification en utilisant deux concepts principaux :
1. **La Transformée en Cosinus Discrète (TCD)** : C'est une méthode de traitement du signal utilisée pour transformer les données dans le domaine fréquentiel. Elle permet de réduire les dimensions des données tout en préservant les informations essentielles.
2. **La Loi de Benford** : Elle prédit la distribution des premiers chiffres significatifs dans de nombreuses séries de données naturelles.
A partir des données récoltés, on compare les fréquences d'apparitions avec celle de la loi de Benford, Si la tendance ne respecte pas cette dernière, c'est que l'image est fausse

## Application de la loi Benford
### Définition
La loi de benford fait référence à une fréquence de distribution statistique observée empiriquement sur de nombreuses sources de données dans la vraie vie, ainsi qu'en mathématiques.
Dans une série de données numériques, on pourrait s'attendre à voir les chiffres de 1 à 9 apparaître à peu près aussi fréquemment comme premier chiffre significatif, soit avec une fréquence de 1/9 = 11,1 % pour chacun. Or, contrairement à cette intuition (biais d'équiprobabilité), la série suit très souvent approximativement la loi de Benford : pour près du tiers des données, le 1er chiffre significatif est le 1. Viennent ensuite le chiffre 2, puis le 3, etc., et la probabilité d'avoir un 9 comme premier chiffre significatif n'est que de 4,6 %

### Fréquence d'apparition des nombres


| Valeurs  | Loi de Benford |
| -------- | -------------- |
|    1     |      30.10%    |
|    2     |      18.00%    |
|    3     |      12.49%    |
|    4     |      9.69%     |
|    5     |      7.92%     |
|    6     |      6.62%     |
|    7     |      5.80%     |
|    8     |      5.12%     |
|    9     |      4.58%     |
