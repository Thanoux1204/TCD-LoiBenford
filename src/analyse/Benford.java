package analyse;

public class Benford {
    public static void analyse(double[][] dctCoefficients) {
        int[] benfordCounts = new int[9]; // Pour les chiffres 1 à 9
        for (int i = 0; i < dctCoefficients.length; i++) {
            for (int j = 0; j < dctCoefficients[0].length; j++) {
                double coeff = Math.abs(dctCoefficients[i][j]);
                if (coeff > 0) {
                    int firstDigit = Integer.parseInt(Double.toString(coeff).substring(0, 1));
                    if (firstDigit >= 1 && firstDigit <= 9) {
                        benfordCounts[firstDigit - 1]++;
                    }
                }
            }
        }
        // Afficher les résultats
        System.out.println("Distribution des premiers chiffres (règle de Benford) :");
        // Calculer le total des coefficients
        int totalCounts = 0;
        for (int count : benfordCounts) {
            totalCounts += count;
        }
        // Afficher les résultats avec pourcentage
        for (int i = 0; i < benfordCounts.length; i++) {
            double percentage = (totalCounts > 0) ? (benfordCounts[i] * 100.0 / totalCounts) : 0;
            System.out.printf("%d : %d (%.2f%%)%n", i + 1, benfordCounts[i], percentage);
        }
    }
}
