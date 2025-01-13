import Images.ColorConverter;
import analyse.Benford;
import traitement.Traitement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        try{
            // Ouvrir l'image
            File input = new File("ressources/monaedited.png");
            BufferedImage image = ImageIO.read(input);
            System.out.println("Image lu");

            // Extraire le nom de l'extension du fichier
            String fileName = input.getName();
            String extension = fileName.substring(fileName.lastIndexOf('.') + 1);

            // Appliquer le filtre de moyenne
            BufferedImage filteredImage = ColorConverter.applyMeanFilter(image);
            System.out.println("Image filtrée avec succès");

            // Sauvegarder l'image filtrée
            File outputFiltered = new File("ressources/out/"+fileName.substring(0, fileName.lastIndexOf('.'))+"-filtre."+extension);
            ImageIO.write(filteredImage, "png", outputFiltered);
            System.out.println("Image filtrée sauvegardé avec succès");

            // Convertir en niveaux de gris
            BufferedImage grayImage = ColorConverter.convertTo(filteredImage, BufferedImage.TYPE_BYTE_GRAY);
            System.out.println("Image convertie en niveaux de gris");

            // Sauvegarder l'image en niveaux de gris
            File outputGray = new File("ressources/out/"+fileName.substring(0, fileName.lastIndexOf('.'))+"-gris."+extension);
            ImageIO.write(grayImage, "png", outputGray);
            System.out.println("Image en niveaux de gris sauvegardé avec succès");

            // Appliquer la transformée en cosinus discrète à l'image
            Traitement traitement = new Traitement(grayImage);
            double[][] dctCoefficients = traitement.appliquerTCD();
            // Analyser les coefficients avec la règle de Benford pour vérifier que l'image est authentique
            Benford.analyse(dctCoefficients);
        } catch (Exception e){
            System.out.println("Error: " + e);
        }
    }
}
