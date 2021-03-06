package TeemoCrush.data;

/**
 * This class stores game results for a given level. Note that this is
 * just a data holding class. It will be manipulated fully by the
 * MahjongSolitaireRecord class, which stores all the records and manages
 * loading and saving.
 * 
 * @author Richard McKenna
 */
public class LevelRecord
{
    public boolean completed;
    public int score;
    public int levels;
}
