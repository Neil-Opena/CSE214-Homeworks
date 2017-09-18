public class GPS {

    private Location currentLocation;
    private Location destinationLocation;
    private double carSpeed;

    public GPS(){

    }

    public Location getCurrentLocation(){
        return this.currentLocation;
    }

    public Location getDestinationLocation(){
        return this.destinationLocation;
    }

    public double getCarSpeed(){
        return this.carSpeed;
    }

    public void setCurrentLocation(Location currentLocation){
        this.currentLocation = currentLocation;
    }

    public void setDestinationLocation(Location destinationLocation){
        this.destinationLocation = destinationLocation;
    }

    public void setCarSpeed(double carSpeed){
        this.carSpeed = carSpeed;
    }

    public double getCalculatedDistance(){
        double distance = Math.sqrt((Math.pow(destinationLocation.getX() - currentLocation.getX(), 2) + Math.pow(destinationLocation.getY() - currentLocation.getY(), 2)));
        return distance;
    }

    public double getArrivalTime(){
        double time = getCalculatedDistance() / getCarSpeed();
        return time;
    }

    @Override
    public String toString(){
        return "currentLocation=" + currentLocation +", destinationLocation=" + destinationLocation;
    }
}
