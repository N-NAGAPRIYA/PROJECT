class LaunchSequence {
    private IRocket rocket;

    public LaunchSequence(IRocket rocket) {
        this.rocket = rocket;
    }

    public void startChecks() {
        System.out.println("Pre-Launch Checks: All systems are 'Go' for launch.");
        rocket.setStage(1);
    }

    public void launch() {
        if (rocket.getStage() != 1) {
            System.out.println("Pre-launch checks not complete. Unable to launch.");
            return;
        }
        System.out.println("Launch initiated.");
    }

    public void fastForward(int seconds) {
        for (int i = 1; i <= seconds; i++) {
            rocket.updateStatus(1);
if (i % 10 == 0) {
                System.out.printf("Stage: %d, Fuel: %.1f%%, Altitude: %.1f km, Speed: %.1f km/h%n",
                        rocket.getStage(), rocket.getFuel(), rocket.getAltitude(), rocket.getSpeed());
            }

            rocket.checkOrbitStatus();
            if (rocket.getAltitude() >= 200.0 || rocket.getFuel() <= 0) {
                break;
            }
        }
    }
}
