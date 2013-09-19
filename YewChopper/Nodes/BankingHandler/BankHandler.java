package YewChopper.Nodes.BankingHandler;

import YewChopper.Jobs.Job;

import YewChopper.Nodes.Globals;
import YewChopper.YewChopper;
import org.shadowbot.bot.api.methods.data.Bank;
import org.shadowbot.bot.api.methods.data.Calculations;
import org.shadowbot.bot.api.methods.data.Inventory;
import org.shadowbot.bot.api.util.Time;

public class BankHandler  extends Job {

    public boolean activate(){
        return Calculations.distanceTo(Globals.TILE_BANK)<=4 && Inventory.contains(Globals.ID_YEW_LOG);
    }

    public void execute(){
        YewChopper.status="Banking.";
        Bank.open();
        Time.sleep(150,250);
        Bank.deposit(Globals.ID_YEW_LOG,27);
        Time.sleep(150,250);
        Bank.close();

    }
}
