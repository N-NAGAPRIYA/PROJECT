interface IRocket {
    void updateStatus(double time);
    void separateStage();
    void checkOrbitStatus();
    double getFuel();
    double getAltitude();
    double getSpeed();
    int getStage();
    void setStage(int stage);
}