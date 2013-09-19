package YewChopper;

import YewChopper.Jobs.Job;
import YewChopper.Jobs.JobContainer;

import YewChopper.Nodes.BankingHandler.BankHandler;
import YewChopper.Nodes.ChoppingHandler.ChopTrees;
import YewChopper.Nodes.Globals;
import YewChopper.Nodes.WalkingHandler.PaceYews;
import YewChopper.Nodes.WalkingHandler.WalkToBank;
import YewChopper.Nodes.WalkingHandler.WalkToYews;

import org.shadowbot.bot.api.util.Random;
import org.shadowbot.bot.api.Manifest;
import org.shadowbot.bot.api.listeners.PaintListener;
import org.shadowbot.bot.api.methods.input.Mouse;
import org.shadowbot.bot.api.SkillCategory;
import org.shadowbot.bot.api.ShadowScript;



import java.awt.*;

@Manifest(scriptname = "Yew Chopper", author = "tails111", category = SkillCategory.WOODCUTTING, description = "Chops wood. Duh.")
public class YewChopper extends ShadowScript implements PaintListener {

    JobContainer container = new JobContainer();

    public static String status = "";

    @Override
    public void onStart(){
        status="Starting Script.";

        container.submit(new WalkToYews(), new ChopTrees(), new PaceYews(),
                new WalkToBank(), new BankHandler());

    }

    public long startTime = System.currentTimeMillis();
    public long runTime = System.currentTimeMillis()-startTime;

    public static int actualProfit = 0;
    private int postedProfitPerMath = 0;
    public static int postedLogs = 0;
    private int postedLogsPerMath = 0;

    private int getPerHour(final int value){
        return (int) (value * 3600000D / (System.currentTimeMillis() - startTime));
    }

    @Override
    public void paint(Graphics g1){
        Graphics2D g = (Graphics2D)g1;

        postedProfitPerMath = getPerHour(actualProfit);
        postedLogsPerMath = getPerHour(postedLogs);

        runTime = System.currentTimeMillis()-startTime;

        int seconds = (int) (runTime / 1000) % 60 ;
        int minutes = (int) ((runTime / (1000*60)) % 60);
        int hours   = (int) ((runTime / (1000*60*60)) % 24);

        g.setColor(Color.DARK_GRAY);
        int mouseY = (int) Mouse.getLocation().getY();
        int mouseX = (int) Mouse.getLocation().getX();
        g.drawLine(mouseX - 999, mouseY + 999, mouseX + 999, mouseY - 999);
        g.drawLine(mouseX + 999, mouseY + 999, mouseX - 999, mouseY - 999);
        g.drawOval(mouseX-4,mouseY-4, 8, 8);
        g.setColor(Color.GREEN);
        g.fillOval(mouseX-2,mouseY-2,5,5);

        Font fontNormal = new Font("Comic Sans MS", Font.PLAIN, 12);
        Font fontTitle = new Font("Comic Sans MS", Font.BOLD, 12);
        g.setFont(fontTitle);
        g.setColor(Color.BLACK);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g.fillRect(10, 75, 200, 84);
        g.setColor(Color.GREEN);
        g.drawLine(10, 75, 10, 159);//LEFT
        g.drawLine(10,75,210,75);//TOP
        g.drawLine(210,75,210,159);//RIGHT
        g.drawLine(10,159,210,159);//BOTTOM
        //x1,y1,x2,y2
        g.drawString("    Yew Tree Chopper ",11,90);
        g.setFont(fontNormal);
        g.drawString(("Status: " + status), 15, 105);
        g.drawString(("Time Run: " + hours+":"+minutes+":"+seconds), 15, 122);
        g.drawString(("Profit: " + actualProfit + "(" + postedProfitPerMath + ")"), 15, 139);
        g.drawString(("Logs/H: "+ postedLogs + "(" + postedLogsPerMath + ")"),15, 156);
        g.setColor(Color.GREEN);
        g.drawString(("Log Cost: "+ Globals.LogPrice),15, 173);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));

    }

    @Override
    public void onStop() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    Job job = null;
    @Override
    public int loop(){

        job = container.get();
        if(job != null){
            System.out.println("job started: " + container.get());
            job.execute();
            return job.delay();
        }
        return(Random.nextInt(650, 800));
    }

}

//Kebab = 1972
