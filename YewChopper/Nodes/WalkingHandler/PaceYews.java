package YewChopper.Nodes.WalkingHandler;

import YewChopper.Jobs.Job;
import YewChopper.Nodes.Globals;

import YewChopper.YewChopper;

import org.shadowbot.bot.api.methods.data.Calculations;
import org.shadowbot.bot.api.methods.data.Inventory;
import org.shadowbot.bot.api.methods.interactive.Players;
import org.shadowbot.bot.api.methods.data.movement.Walking;
import org.shadowbot.bot.api.methods.interactive.GameEntities;
import org.shadowbot.bot.api.util.Time;
import org.shadowbot.bot.api.util.Timer;
import org.shadowbot.bot.api.wrapper.GameObject;
import org.shadowbot.bot.api.wrapper.Tile;

public class PaceYews extends Job {

    private boolean closeToDeadYew(){
        GameObject tempYew = GameEntities.getNearest(Globals.ID_YEW_CUT);
        if(tempYew!=null){
            Tile tempYewLoc = GameEntities.getNearest(Globals.ID_YEW_CUT).getLocation();
            System.out.println("Close to Dead Yew Dist: " + (Calculations.distanceTo(tempYewLoc)<=4));
            return(Calculations.distanceTo(tempYewLoc)<=4);
        }
        return false;
    }

    private boolean closeToYew(){
        GameObject tempYew = GameEntities.getNearest(Globals.ID_YEW);
        if(tempYew != null){
            System.out.println("Close to Alive Yew Dist: " + (Calculations.distanceTo(tempYew.getLocation())<=4));
            return(Calculations.distanceTo(tempYew.getLocation())<=4);
        }
        System.out.println("Close to Yew Returning False.");
        return false;
    }

    public boolean activate(){
        return !Inventory.isFull() && Inventory.contains(Globals.ID_RUNEAXE) && closeToDeadYew();
    }

    public static int placement = 1;

    public void execute(){
        if(Calculations.distanceTo(Globals.TILE_YEW_ONE)<=4){
            placement = 1;
        }
        if(Calculations.distanceTo(Globals.TILE_YEW_TWO)<=4){
            placement = 2;
        }
        if(Calculations.distanceTo(Globals.TILE_YEW_THREE)<=4){
            placement = 3;
        }

        if(placement == 1){
            YewChopper.status = "Walking to Yew Two";
            do{
                Walking.walkTo(Globals.PATH_YEW_ONE_TO_TWO);
                Timer timeCheck = new Timer(6000);
                do{
                    Time.sleep(350, 500);
                    System.out.println("Sleeping.");
                }while(Players.getLocal().isMoving() && timeCheck.isRunning());
            }while(!closeToYew());
        }
        if(placement == 2){
            YewChopper.status = "Walking to Yew Three";
            do{
                Walking.walkTo(Globals.PATH_YEW_TWO_TO_THREE);
                Timer timeCheck = new Timer(6000);
                do{
                    Time.sleep(350, 500);
                    System.out.println("Sleeping.");
                }while(Players.getLocal().isMoving() && timeCheck.isRunning());
            }while(!closeToYew());
        }
        if(placement == 3){
            YewChopper.status = "Walking to Yew One";
            do{
                Walking.walkTo(Globals.PATH_YEW_THREE_TO_ONE);
                Timer timeCheck = new Timer(6000);
                do{
                    Time.sleep(350, 500);
                    System.out.println("Sleeping.");
                }while(Players.getLocal().isMoving() && timeCheck.isRunning());
            }while(!closeToYew());
        }

    }
}
