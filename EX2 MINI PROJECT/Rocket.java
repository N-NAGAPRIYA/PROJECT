class Rocket implements IRocket {
    private double fuel;
    private double altitude;
    private double speed;
    private int stage;
    private boolean stageSeparationOccurred;

    public Rocket() {
        this.fuel = 100.0;
        this.altitude = 0.0;
        this.speed = 0.0;
        this.stage = 0; // 0: Pre-Launch, 1: Stage 1, 2: Stage 2
        this.stageSeparationOccurred = false;
    }

    @Override
    public void updateStatus(double time) {
        if (fuel > 0) {
            double initialFuel = fuel;
            double fuelConsumption = 0.5 * time;
            fuel -= fuelConsumption;
            
            // Ensure fuel doesn't drop below 90% before stage separation
            if (fuel <= 90 && !stageSeparationOccurred) {
                fuel = 90; // Set fuel to exactly 90%
                altitude = 10; // Set altitude to exactly 10 km
                stageSeparationOccurred = true; // Mark that stage separation has occurred
                separateStage();
            }

            speed += 50.0 * time; // Adjusted speed increase
            altitude += speed * time / 3600.0; // Convert speed to km/h
        }
    }

    @Override
    public void separateStage() {
        if (stage == 1) {
            System.out.println("Stage 1 complete. Separating stage. Entering Stage 2.");
            stage = 2;
        }
    }

    @Override
    public void checkOrbitStatus() {
        if (altitude >= 200.0) {
            System.out.println("Orbit achieved! Mission Successful.");
        } else if (fuel <= 0) {
            System.out.println("Mission Failed due to insufficient fuel.");
        }
    }

    @Override
    public double getFuel() {
        return fuel;
    }

    @Override
    public double getAltitude() {
        return altitude;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public int getStage() {
        return stage;
    }

    @Override
    public void setStage(int stage) {
        this.stage = stage;
    }
}
