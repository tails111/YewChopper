package YewChopper.Nodes.WalkingHandler;

import YewChopper.Jobs.Job;
import YewChopper.Nodes.Globals;

import YewChopper.YewChopper;

import org.shadowbot.bot.api.methods.data.Calculations;
import org.shadowbot.bot.api.methods.input.Mouse;
import org.shadowbot.bot.api.methods.interactive.Players;
import org.shadowbot.bot.api.methods.interactive.Widgets;
import org.shadowbot.bot.api.util.Random;
import org.shadowbot.bot.api.util.Time;
import org.shadowbot.bot.api.methods.data.Inventory;
import org.shadowbot.bot.api.methods.data.movement.Walking;

import java.awt.*;


public class WalkToBank  extends Job {

    private void setRun(){
        YewChopper.status="Setting run.";
         int mouseX = Widgets.getWidget(548, 41).getX();
         int mouseY = Widgets.getWidget(548, 41).getY();
         Point clickPoint = new Point(mouseX+(Random.nextInt(5,25)),mouseY+Random.nextInt(5,25));
         Mouse.click(clickPoint, true);
         Time.sleep(150,250);
         mouseX = Widgets.getWidget(261, 0).getX();
         mouseY = Widgets.getWidget(261, 0).getY();
         clickPoint.setLocation((mouseX+(Random.nextInt(5,25))),(mouseY+Random.nextInt(5,25)));
         Mouse.click(clickPoint, true);
         YewChopper.status="Opening inventory.";
         Time.sleep(150,250);
         mouseX = 626;//Widgets.getWidget(548, 57).getX();
         mouseY = 170;//Widgets.getWidget(548, 57).getY();
         Point clickPoint2 = new Point(mouseX+(Random.nextInt(5,25)),mouseY+Random.nextInt(5,25));
         Mouse.click(clickPoint2, true);
    }

    public boolean activate(){
        return Calculations.distanceTo(Globals.TILE_BANK)>=4 && Inventory.isFull() || !Inventory.contains(Globals.ID_RUNEAXE);
    }

    public void execute(){
        YewChopper.status = "Walking To Bank.";
        if(Calculations.distanceTo(Globals.TILE_YEW_ONE)<=4){
            PaceYews.placement = 1;
        }
        if(Calculations.distanceTo(Globals.TILE_YEW_TWO)<=8){
            PaceYews.placement = 2;
        }
        if(Calculations.distanceTo(Globals.TILE_YEW_THREE)<=4){
            PaceYews.placement = 3;
        }
        setRun();
        YewChopper.status = "Walking To Bank.";
        if(PaceYews.placement==3){
        do{
            if(PaceYews.placement==3){Walking.walkTo(Globals.PATH_YEW_THREE_TO_ONE);Time.sleep(600,750);}
        }while(Calculations.distanceTo(Globals.TILE_YEW_ONE)>=4);
        }

        if(PaceYews.placement==2)
        {
        do{
            System.out.println("Walking from 2 to One.");
            if(PaceYews.placement==2){Walking.getClosetTileOnMap(Globals.TILE_YEW_ONE).clickOnMap();Time.sleep(600,750);}//Walking.walkTo(Globals.PATH_YEW_TWO_TO_ONE);}
        }while(Calculations.distanceTo(Globals.TILE_YEW_ONE)>=4);
        }

        Time.sleep(600,750);
        YewChopper.status = "Walking To Bank.";
        int x = 0;
        do{

            x++;
            Walking.walkTo(Globals.PATH_YEW_TO_BANK);
            do{
                Time.sleep(350, 500);
            }while(Players.getLocal().isMoving());
        }while(Calculations.distanceTo(Globals.TILE_BANK)>=4 && x<= Random.nextInt(50,65));
    }
}
