package YewChopper.Nodes.ChoppingHandler;

import YewChopper.Jobs.Job;
import YewChopper.Nodes.Globals;

import YewChopper.YewChopper;

import org.shadowbot.bot.api.methods.data.Calculations;
import org.shadowbot.bot.api.methods.data.Inventory;
import org.shadowbot.bot.api.methods.input.Mouse;
import org.shadowbot.bot.api.methods.interactive.GameEntities;

import org.shadowbot.bot.api.methods.interactive.Players;
import org.shadowbot.bot.api.methods.interactive.Widgets;
import org.shadowbot.bot.api.methods.data.movement.Camera;
import org.shadowbot.bot.api.methods.data.Menu;
import org.shadowbot.bot.api.util.Random;
import org.shadowbot.bot.api.util.Time;
import org.shadowbot.bot.api.wrapper.GameObject;
import org.shadowbot.bot.api.wrapper.Tile;

import java.awt.*;

public class ChopTrees  extends Job {

    private boolean closeToYew(GameObject tempYew){
        if(tempYew != null){
            return(Calculations.distanceTo(tempYew.getLocation())<=4);
        }
        return false;
    }

    private boolean closeToDeadYew(){
        GameObject tempYew = GameEntities.getNearest(Globals.ID_YEW_CUT);
        if(tempYew!=null){
            Tile tempYewLoc = GameEntities.getNearest(Globals.ID_YEW_CUT).getLocation();
            return(Calculations.distanceTo(tempYewLoc)<=4);
        }
        return false;
    }

    public boolean invChange(){
        int newInvCount = Inventory.getCount();
        if(newInvCount!=oldInvCount){
            YewChopper.postedLogs++;
            YewChopper.actualProfit=YewChopper.actualProfit+Globals.LogPrice;
            return true;
        }
        return false;
    }


    private GameObject YewTree;
    int oldInvCount=0;

    private void antiBan(){
        int antiBan1=13, antiBan2=22, antiBan3=79, antiBan4=250;
        int randX = Random.nextInt(0,500);
        int antiBan5 = Random.nextInt(1,100);

        if(randX==antiBan1){
            YewChopper.status="Anti-ban: Camera";
            Camera.setAngel(Random.nextInt(0,359));
        }

         if(randX==antiBan2){
             YewChopper.status="Anti-ban: Equipment Tab";
             int mouseX = 663;//Widgets.getWidget(548, 58).getX();
             int mouseY = 170;//Widgets.getWidget(548, 58).getY();
             Point clickPoint = new Point(mouseX+(Random.nextInt(5,25)),mouseY+Random.nextInt(5,25));
            Mouse.click(clickPoint, true);
             Time.sleep(3000,6000);
             mouseX = 626;//Widgets.getWidget(548, 57).getX();
             mouseY = 170;//Widgets.getWidget(548, 57).getY();
             Point clickPoint2 = new Point(mouseX+(Random.nextInt(5,25)),mouseY+Random.nextInt(5,25));
             Mouse.click(clickPoint2, true);
         }

         if(randX==antiBan3){
             YewChopper.status="Anti-ban: Skills Tab";
              int mouseX =560;// Widgets.getWidget(548, 55).getX();
             int mouseY = 170;//Widgets.getWidget(548, 55).getY();
             Point clickPoint = new Point(mouseX+(Random.nextInt(5,25)),mouseY+Random.nextInt(5,25));
              Mouse.click(clickPoint, true);
              Time.sleep(250,500);
              mouseX= Widgets.getWidget(320,144).getX();
              mouseY= Widgets.getWidget(320,144).getY();
              clickPoint.setLocation((mouseX+(Random.nextInt(5,45))),(mouseY+Random.nextInt(5,25)));
              Mouse.move(clickPoint);
              Time.sleep(6000,10000);
              mouseX = 626;//Widgets.getWidget(548, 57).getX();
              mouseY = 170;//Widgets.getWidget(548, 57).getY();
              clickPoint.setLocation((mouseX+(Random.nextInt(5,25))),(mouseY+Random.nextInt(5,25)));
              Mouse.click(clickPoint, true);
          }

        if(randX==antiBan4){
            YewChopper.status="Anti-ban: Random Click";
            Point clickPoint = new Point((Random.nextInt(10,510)),(Random.nextInt(10,330)));
            Mouse.click(clickPoint, false);
            Time.sleep(6000,10000);
            clickPoint.setLocation(Random.nextInt(25,510),Random.nextInt(20,330));
            Mouse.move(clickPoint);
        }

        if(randX==antiBan5){
            YewChopper.status="Anti-ban: Random Move";
            Point movePoint = new Point(Random.nextInt(10,750),Random.nextInt(10,475));
            Mouse.move(movePoint);
            Time.sleep(1000,2000);
        }
    }

    private boolean checkAxe(){
        return(Inventory.contains(Globals.ID_RUNEAXE));
    }

    private void tTurnTo(GameObject tObject){
        if(tObject!=null){
            int angleTo = Camera.getAngleTo(tObject.getLocation());
            int randTurn = Random.nextInt(45,75);
            if(angleTo+randTurn<360){
                Camera.setAngel(angleTo-20+Random.nextInt(0,45));
            }
        }
    }

    private boolean checkUpText(String s){
        String[] menuOptions = Menu.getMenuItems();
        for(int i=0; i <menuOptions.length; i++){
            return(menuOptions[i].contentEquals(s));
        }
        return false;
    }

    public boolean activate(){
        YewTree = GameEntities.getNearest(Globals.ID_YEW);
        return !Inventory.isFull() && checkAxe() && YewTree != null && closeToYew(YewTree);
    }

    public void execute(){
        YewChopper.status = "Chopping Yews.";
        if(YewTree!=null){
            tTurnTo(YewTree);
            do{
                Point mousePoint = YewTree.getLocation().getPointOnScreen();
                mousePoint.setLocation((mousePoint.getX()-55+Random.nextInt(5,85)),(mousePoint.getY()-55+ Random.nextInt(5,85)));
                Mouse.move(mousePoint);
                if(checkUpText("Chop down") && Players.getLocal().getAnimation()==-1){
                    Mouse.click(mousePoint, true);
                    Time.sleep(850,1000);
                }
            }while(!checkUpText("Chop down"));
            // YewTree.interact("Chop down");
            oldInvCount=Inventory.getCount();
            int x=0;
            int randX = Random.nextInt(200,350);
            do{
                x++;
                YewChopper.status="Waiting to Chop: " + x + "("+randX+")";
                antiBan();
                Time.sleep(650,850);
                invChange();
                oldInvCount=Inventory.getCount();
                if(!closeToYew(YewTree) || x==randX){break;}
            }while(YewTree!=null && Players.getLocal().getAnimation()!= -1 && !Inventory.isFull());
            YewTree=null;
        }
    }
}
