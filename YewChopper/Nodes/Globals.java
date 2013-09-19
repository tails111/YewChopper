package YewChopper.Nodes;

//import org.shadowbot.bot.api.methods.data.ItemPrice;
import org.shadowbot.bot.api.wrapper.Tile;

public class Globals {

    public static int LogPrice = 480;//ItemPrice.lookUp("Yew logs");
    //2767
    public static int ID_YEW = 12611, ID_BANK = 2196, ID_RUNEAXE = 1359, ID_YEW_CUT = 7402;
    public static int ID_YEW_LOG = 1515;

    public static Tile[] PATH_PACE_YEW = {new Tile(3152,3229,0),new Tile(3159,3225,0),new Tile(3164,3220,0),
            new Tile(3168,3220,0),new Tile(3176,3223,0),new Tile(3183,3228,0),
            new Tile(3183,3227,0),new Tile(3173,3226,0),new Tile(3162,3227,0),
            new Tile(3153,3229,0)};

    public static Tile[] PATH_BANK_TO_YEW = {new Tile(3101,3244,0),new Tile(3101,3238,0),new Tile(3105,3230,0),new Tile(3113,3225,0),
            new Tile(3122,3221,0),new Tile(3133,3224,0),new Tile(3142,3229,0),new Tile(3150,3230,0)};

    public static Tile[] PATH_YEW_TO_BANK = {new Tile(3150,3230,0), new Tile(3143,3228,0), new Tile(3135,3225,0), new Tile(3127,3222),
            new Tile(3120,3221,0), new Tile(3114,3224,0), new Tile(3110,3229,0), new Tile(3103,3234),
            new Tile(3100,3241,0), new Tile(3094,3243,0)};

    public static Tile TILE_YEW_ONE = new Tile(3152,3229,0);
    public static Tile TILE_YEW_TWO = new Tile(3164,3220,0);
    public static Tile TILE_YEW_THREE =  new Tile(3183,3226,0);
    public static Tile[] PATH_YEW_ONE_TO_TWO = {  new Tile(3154,3226,0),new Tile(3158,3224,0),new Tile(3164,3220,0)};
    public static Tile[] PATH_YEW_TWO_TO_THREE = {new Tile(3168,3221,0),new Tile(3175,3223,0),new Tile(3183,3226,0)};

    public static Tile[] PATH_YEW_THREE_TO_ONE = {new Tile(3183,3227,0),new Tile(3178,3227,0),new Tile(3170,3226,0),
            new Tile(3162,3227,0),new Tile(3152,3228,0)};
    public static Tile[] PATH_YEW_TWO_TO_ONE = {new Tile(3164,3220,0),new Tile(3158,3224,0),new Tile(3154,3226,0)};

    public static Tile TILE_BANK = new Tile(3093,3243,0);

}