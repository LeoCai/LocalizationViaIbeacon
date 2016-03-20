package com.leocai.beaconlocalization.wekaPhone;

/**
 * Created by leocai on 15-5-7.
 */
public class HumanState {
    public static final int WALKING = 0;
    public static final int STAND = 1;
    public static final int CACULATED =2;
    public static final int STOP_CACULATE = 3;

    private static int state = STAND;
    private static boolean readyCalulate = true;
    private static int standCount = 0;
    private static int caculateCount = 0;

    private static int walkCount = 0;

    public static void setState(int state) {
        switch (state){
            case WALKING:
                HumanState.state = state;
                standCount = 0;
                walkCount ++ ;
                //if(walkCount%3==0){
                readyCalulate = false;
                //}
                break;
            case STAND:
                if(HumanState.state == WALKING){
                    standCount ++;
                    if(standCount >=10){
                        HumanState.state = state;
                        readyCalulate = true;
                        walkCount = 0;
                    }
                }
                break;
            case CACULATED:
                standCount=0;
                HumanState.state = state;
                caculateCount++;
                if(caculateCount >= 20){
                    caculateCount= 0;
                    readyCalulate = false;
                    HumanState.state = STOP_CACULATE;
                }
                break;
        }

    }

    public static int getState() {
        return state;
    }

    public static boolean isReadyCalulate() {
        return readyCalulate;
    }

    public static void setReadyCalulate(boolean readyCalulate) {
        HumanState.readyCalulate = readyCalulate;
    }

    public static int getCaculateCount() {
        return caculateCount;
    }
}
