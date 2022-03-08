package lab2;

import static java.lang.System.*;

public class LectureHall extends Room{
    private boolean videoProjector;

    public LectureHall(String inputName, int inputCapacity, boolean hasProjector){
        if(inputName != null && inputName.length() > 0){
            name = inputName;
        }else{
            err.println("Name cannot be NULL");
            exit(1);
        }

        if(inputCapacity > 1){
            capacity = inputCapacity;
        }else{
            err.println("Capacity cannot be less than 1");
            exit(1);
        }

        type = "LectureHall";
        videoProjector = hasProjector;
    }

    public boolean isVideoProjector() {
        return videoProjector;
    }

    public void setVideoProjector(boolean hasProjector){
        videoProjector = hasProjector;
    }
}
