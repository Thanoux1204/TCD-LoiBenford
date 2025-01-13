package Images;

import java.awt.image.BufferedImage;

public class ColorConverter {

    /**
     * Convertir une image en nuance de gris
     * @param image
     * @param typeToConvert
     * @return
     */
    public static BufferedImage convertTo(BufferedImage image, int typeToConvert) {
        BufferedImage grayImage = new BufferedImage(
                image.getWidth(), image.getHeight(), typeToConvert);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int gray = (int) (0.299 * ((rgb >> 16) & 0xFF) +
                        0.587 * ((rgb >> 8) & 0xFF) +
                        0.114 * (rgb & 0xFF));
                int grayRgb = (gray << 16) | (gray << 8) | gray;
                grayImage.setRGB(x, y, grayRgb);
            }
        }
        return grayImage;
    }

    /**
     * Appliquer un filtre de moyenne (flou) à une image.
     * @param image L'image à filtrer
     * @return L'image après application du filtre
     */
    public static BufferedImage applyMeanFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage filteredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);  // Assurez-vous que l'image filtrée prend en compte le canal alpha.
        // Appliquer un filtre de moyenne 3x3
        int kernelSize = 3;
        int offset = kernelSize / 2;
        // Taille du noyau
        int kernelArea = kernelSize * kernelSize;
        // Parcourir tous les pixels et appliquer le filtre
        for (int y = offset; y < height - offset; y++) {
            for (int x = offset; x < width - offset; x++) {
                int sumRed = 0, sumGreen = 0, sumBlue = 0, sumAlpha = 0;
                // Appliquer le noyau 3x3 autour du pixel actuel
                for (int ky = -offset; ky <= offset; ky++) {
                    for (int kx = -offset; kx <= offset; kx++) {
                        int rgb = image.getRGB(x + kx, y + ky);
                        // Extraire les composantes de couleur (R, G, B, A)
                        int alpha = (rgb >> 24) & 0xFF;
                        int red = (rgb >> 16) & 0xFF;
                        int green = (rgb >> 8) & 0xFF;
                        int blue = rgb & 0xFF;
                        // Additionner les valeurs de chaque composant
                        sumAlpha += alpha;
                        sumRed += red;
                        sumGreen += green;
                        sumBlue += blue;
                    }
                }
                // Calculer la valeur moyenne pour chaque composant de couleur
                int avgAlpha = sumAlpha / kernelArea;
                int avgRed = sumRed / kernelArea;
                int avgGreen = sumGreen / kernelArea;
                int avgBlue = sumBlue / kernelArea;
                // Assurer que les valeurs sont dans la plage valide [0, 255]
                avgAlpha = Math.max(0, Math.min(255, avgAlpha));  // Ne pas toucher à la transparence (alpha)
                avgRed = Math.max(0, Math.min(255, avgRed));
                avgGreen = Math.max(0, Math.min(255, avgGreen));
                avgBlue = Math.max(0, Math.min(255, avgBlue));
                // Recréer la couleur filtrée avec les valeurs moyennes des composants R, G, B et A
                int filteredRgb = (avgAlpha << 24) | (avgRed << 16) | (avgGreen << 8) | avgBlue;
                // Assigner la couleur filtrée au pixel correspondant
                filteredImage.setRGB(x, y, filteredRgb);
            }
        }
        return filteredImage;
    }
}