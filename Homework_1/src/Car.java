public class Car {
    private double currentSpeed;
    private GPS gps;

    public Car(){

    }

    public double getCurrentSpeed(){
        return this.currentSpeed;
    }

    public void setCurrentSpeed(double speed){
        this.currentSpeed = speed;
        this.gps.setCarSpeed(speed);
    }

    public GPS getGPS(){
        return this.gps;
    }

    public void setGPS(GPS gps){
        this.gps = gps;
    }
}
