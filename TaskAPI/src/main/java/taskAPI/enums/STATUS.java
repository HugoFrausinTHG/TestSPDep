package taskAPI.enums;

public enum STATUS{
    STARTED("Started"),
    FINISHED("Finished"),
    NOT_STARTED("Not_Started");

    private String status;

    STATUS(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }
}