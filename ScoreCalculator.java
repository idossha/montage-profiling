import java.util.Scanner;

public class ScoreCalculator {

    // Method to calculate z-score for focality and map it to a 1-10 scale
    public static double calculateFocalityScore(double focality, double mean, double stdDev) {
        double zScore = (focality - mean) / stdDev;
        return mapToRange(zScore);
    }

    // Method to calculate z-score for v/m and map it to a 1-10 scale
    public static double calculateVmScore(double vm, double mean, double stdDev) {
        double zScore = (vm - mean) / stdDev;
        return mapToRange(zScore);
    }

    // Method to map z-scores to a 1-10 range
    public static double mapToRange(double zScore) {
        // We assume a z-score in the range of -2 to 2 will map to the range 1 to 10
        // Clamp the z-score range to -2.0 <= zScore <= 2.0
        zScore = Math.max(-2, Math.min(2, zScore));
        double scaledScore = 5.5 + (zScore * 2.25); // Shifts and scales to fit 1-10 range
        return Math.round(scaledScore * 100.0) / 100.0; // Cut at second decimal place
    }

    public static void main(String[] args) {
        // Mean and standard deviation values for focality and v/m
        double focalityMean = 177.5315698
        ;
        double focalityStdDev = 91.25764196
        ;

        double vmMean = 1.648213023
        ;
        double vmStdDev = 0.693280058
        ;

        // Scanner to take user input
        Scanner scanner = new Scanner(System.in);

        // Input for focality
        System.out.print("Enter focality: ");
        double focality = scanner.nextDouble();

        // Input for v/m
        System.out.print("Enter volts per meter (v/m): ");
        double vm = scanner.nextDouble();

        // Calculate scores for focality and v/m (in the range 1-10)
        double focalityScore = calculateFocalityScore(focality, focalityMean, focalityStdDev);
        double vmScore = calculateVmScore(vm, vmMean, vmStdDev);

        // Final score: subtracting focality score from v/m score
        double totalScore = Math.round((vmScore - focalityScore) * 100.0) / 100.0;

        // Output the scores
        System.out.println("Focality score (1-10 scale): " + focalityScore);
        System.out.println("V/m score (1-10 scale): " + vmScore);
        System.out.println("Total score (v/m - focality): " + totalScore);

        // Close the scanner
        scanner.close();
    }
}
