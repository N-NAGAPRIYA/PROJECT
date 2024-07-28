public class Rocket {
    private String stage = "Pre-Launch";
    private int fuel = 100;
    private int altitude = 0;
    private int speed = 0;
    private String checksStatus = "Not Complete";

    public void startChecks() {
        System.out.println("All systems are 'Go' for launch.");
        checksStatus = "Complete";
    }

    public void launch(int second) {
        if (fuel <= 0) {
            System.out.println("Mission Failed due to insufficient fuel.");
            return;
        }
        if (fuel > 70) {
            stage = "Stage 1";
            updateStatus();
            launch(second + 1);
        } else if (fuel == 70) {
            System.out.println("Stage 1 complete. Separating stage. Entering Stage 2.");
            stage = "Stage 2";
            fuel -= 10; // Decrement fuel to avoid infinite loop
            launch(second + 1);
        } else if (fuel > 10) {
            updateStatus();
            launch(second + 1);
        } else if (fuel == 10) {
            System.out.println("Orbit achieved! Mission Successful.");
        }
    }

    public void fastForward(int seconds, int currentSecond) {
        if (fuel <= 0) {
            System.out.println("Mission Failed due to insufficient fuel.");
            return;
        }
        if (currentSecond <= seconds) {
            updateStatus();
            fastForward(seconds, currentSecond + 1);
        }
    }

    private void updateStatus() {
        if (fuel > 0) {
            fuel -= 10;
            altitude += 10;
            speed += 1000;
            System.out.printf("Stage: %s, Fuel: %d%%, Altitude: %d km, Speed: %d km/h%n", stage, fuel, altitude, speed);
        } else {
            System.out.println("Mission Failed due to insufficient fuel.");
        }
    }

    public String getChecksStatus() {
        return checksStatus;
    }
}
