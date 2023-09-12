package bunchbysoh;

public class Main {
    static class CountsBySoH {
        public int healthy = 0;
        public int exchange = 0;
        public int failed = 0;
    }

    static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
        CountsBySoH counts = new CountsBySoH();

        for (int capacity : presentCapacities) {
            double soh = calculateSoH(capacity, 120); // Assuming rated capacity is 120 Ah
            classifyBattery(counts, soh);
        }

        return counts;//returns count of healthy,exchange,failed
    }

    static double calculateSoH(int present_Capacity, int rated_Capacity) {
        return (double) present_Capacity / rated_Capacity * 100.0;
    }

    static void classifyBattery(CountsBySoH counts, double soh) {
        if (soh > 80.0) 
        {
            counts.healthy++;//increments if it is in the range of 80-100
        } 
        else if (soh >= 63.0)
        {
            counts.exchange++;//increments if it is in the range of 63-80
        } 
        else 
        {
            counts.failed++;//indrements if it is in the range of 0-63
        }
    }

    static void testBucketingByHealth() {
        System.out.println("Counting batteries by SoH...\n");
        int[] presentCapacities = {115, 118, 80, 95, 91, 77};
        CountsBySoH counts = countBatteriesByHealth(presentCapacities);
        assert(counts.healthy == 2);
        assert(counts.exchange == 3);
        assert(counts.failed == 1);
        System.out.println("Healthy: " + counts.healthy);
        System.out.println("Exchange: " + counts.exchange);
        System.out.println("Failed: " + counts.failed);
        System.out.println("Done counting :)\n");
    }

    public static void main(String[] args) {
        testBucketingByHealth();
    }
}
