public class AirPurifier {
    //Instance Variables
    boolean power = false;
    String mode = "OFF";
    int fanSpeed = 0;
    String model;
    String serialNo;

    //Constructor
    AirPurifier(String model, String serialNo){
        this.model = model;
        this.serialNo = serialNo;
    }

    //Overloading Constructor
    AirPurifier(String serialNo){
        this("Pro", serialNo);
    }

    //Instance Methods
    void turnOn(int pm25){
        if(this.mode.equals("OFF")){
            this.power = true;
            autoMode(pm25);
            System.out.printf("Turn %s %s on\n", model, serialNo);
        }
    }
    void turnOff(){
        if(this.mode.equals("OFF")){
            this.power = false;
            this.mode = "OFF";
            fanSpeed = 0;
            System.out.printf("Turn %s %s off\n",model,serialNo);
        }
    }

    void showMode(){
        System.out.printf("%s %s urrent Mode: %s\n",model,serialNo,mode);
    }

    void showAQI(int pm25){
        String AQIcat ="";
        if(this.power){
            if(pm25 < 50) AQIcat = "GOOD";
            else if (pm25 >= 51 && pm25 <= 100) AQIcat = "MODERATE";
            else if (pm25 >= 101 && pm25 <= 150) AQIcat = "UNHEALTHY FOR SENSITIVE GROUPS";
            else if (pm25 >= 151 && pm25 <= 200) AQIcat = "UNHEALTHY";
            else if (pm25 >= 201 && pm25 <= 300) AQIcat = "VERY UNHEALTHY";
            else if (pm25 >= 300) AQIcat = "HAZARDOUS";

            System.out.printf("PM2.5 value = %d - AQI Category %s.\n", pm25,AQIcat);

        }
    }

    void autoMode(int pm25){
        if(pm25 < 50) this.fanSpeed = 1;
        else if (pm25 > 51 && pm25 < 100) this.fanSpeed = 2;
        else if (pm25 > 101 && pm25 < 150) this.fanSpeed = 3;
        else if (pm25 > 151 && pm25 < 200) this.fanSpeed = 4;
        else if (pm25 >201 && pm25 < 300) this.fanSpeed = 5;
        else if (pm25 < 300) this.fanSpeed= 0;
        this.mode = "AUTO";

    }

    void powerfulMode(){
        this.fanSpeed = 6;
        this.mode = "POWERFUL";

    }

    void sleepMode(int pm25){
        if(pm25 < 150) {
            this.fanSpeed = 1;
            this.mode = "SLEEP";
        }
        else if (pm25 >= 151 && pm25 <= 200) {
            System.out.println("Set to SLEEP improperly.");
            this.fanSpeed = 1;
            this.mode = "SLEEP";
        }
        else if (pm25 >= 201) {
            System.out.println("Cannot set to SLEEP due to bad quality air.");
        }

    }

    void setMode(int pm25,int nextMode){
        if(this.power){
            if(nextMode == 1){
                autoMode(pm25);
            }else if(nextMode == 2){
                powerfulMode();
            }else if(nextMode == 3){
                sleepMode(pm25);
            } else {
                System.out.println("ERROR");
            }
        }
        System.out.printf("Set %s %s to %s Mode.\n",model,serialNo,mode);
    }
    void showFanSpeed(){
        System.out.printf("Fan Speed: %d.\n", this.fanSpeed);
    }

    public static void main(String[] args){
        AirPurifier A1 = new AirPurifier("P-01");
        int pm25 = 100;

        A1.turnOn(pm25);
        A1.showMode();
        A1.setMode(pm25,1);
        A1.showMode();
        A1.showAQI(pm25);
        A1.turnOff();
        A1.showMode();

        System.out.println("-------------------------------------------------");
        int pm25sec = 1080;

        AirPurifier A2 = new AirPurifier("Pro Max","P-M-02");
        A2.showMode();
        A2.turnOn(pm25sec);
        A2.showAQI(pm25sec);
        A2.showFanSpeed();
    }
}
