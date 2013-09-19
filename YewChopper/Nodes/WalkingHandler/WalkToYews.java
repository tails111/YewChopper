package YewChopper.Nodes.WalkingHandler;

import YewChopper.Jobs.Job;
import YewChopper.Nodes.Globals;

import YewChopper.YewChopper;

import org.shadowbot.bot.api.methods.data.Calculations;
import org.shadowbot.bot.api.methods.data.Inventory;
import org.shadowbot.bot.api.methods.interactive.Players;
import org.shadowbot.bot.api.methods.data.movement.Walking;
import org.shadowbot.bot.api.util.Random;
import org.shadowbot.bot.api.util.Time;
import org.shadowbot.bot.api.wrapper.Tile;

public class WalkToYews  extends Job {


    private double distanceToYewsPath(){
        Tile yewPacePathTile;
        for(int i=0; i<= Globals.PATH_PACE_YEW.length-1; i++){
            yewPacePathTile = Globals.PATH_PACE_YEW[i];
            if(Calculations.distanceTo(yewPacePathTile)<=6){
                return(Calculations.distanceTo(yewPacePathTile));
            }
        }
        return 0;
    }

    public boolean activate(){
        return !Inventory.isFull() && Inventory.contains(Globals.ID_RUNEAXE)
                && !Inventory.contains(Globals.ID_YEW_LOG) && distanceToYewsPath()==0;
    }

    public void execute(){
        int x = 0;
        YewChopper.status = "Walking To Yews.";
        do{
            x++;
            Walking.walkTo(Globals.PATH_BANK_TO_YEW);
            do{
                Time.sleep(350, 500);
            }while(Players.getLocal().isMoving());
        }while(distanceToYewsPath()==0 && x<=Random.nextInt(50, 65));
    }
}
