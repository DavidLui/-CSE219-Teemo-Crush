/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush.data;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;

/**
 *
 * @author david
 */
public class Recordz {
    private HashMap<String, LevelRecord> levelRecords;
    private DataModel data;

    /**
     * Default constructor, it simply creates the hash table for
     * storing all the records stored by level.
     */
    public Recordz()
    {
        levelRecords = new HashMap();
    }
        public void addWin(int level, boolean completed, int score)
    {
        // GET THE RECORD FOR levelName
        LevelRecord rec = levelRecords.get(level);
        
        // IF THE PLAYER HAS NEVER PLAYED A GAME ON levelName
        if (rec == null)
        {
            // MAKE A NEW RECORD FOR THIS LEVEL, SINCE THIS IS
            // THE FIRST TIME WE'VE PLAYED IT
            rec = new LevelRecord();
            rec.levels = data.currentLevel;
            rec.score = score;
            rec.completed = true;
            //levelRecords.put(level, completed, score);
        }
        else
        {
            if (rec.score < score) rec.score = score;
            // WE'VE PLAYED THIS LEVEL BEFORE, SO SIMPLY
            // UPDATE THE STATS
     
        }
    }
    
    public byte[] toByteArray() throws IOException
    {
        Iterator<String> keysIt = levelRecords.keySet().iterator();
        int numLevels = levelRecords.keySet().size();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(numLevels);
        while(keysIt.hasNext())
        {
            String key = keysIt.next();
            dos.writeUTF(key);
            LevelRecord rec = levelRecords.get(key);
           
        }
        // AND THEN RETURN IT
     
        return baos.toByteArray();
    }
}
