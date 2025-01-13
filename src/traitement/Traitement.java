package traitement;

import java.awt.image.BufferedImage;

public class Traitement  {
    // Taille du bloc pour la DCT (8x8 par défaut)
    private static final int BLOCK_SIZE = 8;
    static BufferedImage image;
    public Traitement(BufferedImage bufferedImg){
        image = bufferedImg;
    }
    /**
     * Appliquer la transformée en cosinus discrète (TCD) à l'image
     * @return le tableau des coefficients TCD
     */
    public static double[][] appliquerTCD() {
        int width = image.getWidth();  // Largeur de l'image
        int height = image.getHeight();  // Hauteur de l'image
        double[][] C = new double[width][height];  // Tableau pour stocker les coefficients TCD

        // Découper l'image en blocs de taille 8x8
        for (int y = 0; y < height; y += BLOCK_SIZE) {
            for (int x = 0; x < width; x += BLOCK_SIZE) {
                double[][] f = new double[BLOCK_SIZE][BLOCK_SIZE];  // Bloc de pixels à traiter
                // Extraire les pixels du bloc
                for (int i = 0; i < BLOCK_SIZE; i++) {
                    for (int j = 0; j < BLOCK_SIZE; j++) {
                        if (x + j < width && y + i < height) {  // Vérifier que les coordonnées sont dans les limites de l'image
                            int gray = image.getRGB(x + j, y + i) & 0xFF;  // La valeur RGB en niveau de gris
                            f[i][j] = gray;  // Stockage du pixel dans le bloc
                        }
                    }
                }
                // Appliquer la DCT 2D sur le bloc extrait
                double[][] CBlock = dct2D(f);
                // Copier les coefficients DCT dans le tableau global des coefficients
                for (int i = 0; i < BLOCK_SIZE; i++) {
                    for (int j = 0; j < BLOCK_SIZE; j++) {
                        if (x + j < width && y + i < height) {  // Vérifier que les coordonnées sont dans les limites
                            C[x + j][y + i] = CBlock[i][j];
                        }
                    }
                }
            }
        }
        return C;  // Retourner les coefficients TCD calculés pour toute l'image
    }

    /**
     * Appliquer la Transformée en Cosinus Discrète (TCD) 2D sur un bloc 8x8
     * @param f Le bloc 8x8 d'entrée (image) pour lequel la DCT doit être calculée
     * @return Le tableau 8x8 des coefficients DCT
     */
    private static double[][] dct2D(double[][] f) {
        int n = f.length;  // Taille du bloc (8x8)
        double[][] C = new double[n][n];  // Tableau pour stocker les coefficients DCT calculés
        double[][] cosValues = new double[n][n];  // Tableau pour les valeurs pré-calculées des cosinus
        double[] alpha = new double[n];  // Tableau pour les coefficients de normalisation

        // Pré-calcul des coefficients alpha et des valeurs cosinus
        for (int u = 0; u < n; u++) {
            alpha[u] = (u == 0) ? Math.sqrt(1.0 / n) : Math.sqrt(2.0 / n);  // Coefficients de normalisation (alpha)
            for (int v = 0; v < n; v++) {
                cosValues[u][v] = Math.cos(((2 * v + 1) * u * Math.PI) / (2 * n));  // Calcul des valeurs cosinus
            }
        }
        // Calcul de la DCT 2D
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                double sum = 0.0;
                // Parcours des pixels du bloc
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {
                        sum += f[x][y] * cosValues[u][x] * cosValues[v][y];  // Calcul de la somme pour le coefficient DCT
                    }
                }
                // Application des coefficients alpha
                C[u][v] = alpha[u] * alpha[v] * sum;
            }
        }
        return C;  // Retour des coefficients DCT du bloc
    }
}
