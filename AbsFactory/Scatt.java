import java.io.File;
import java.util.Arrays;

/** Scatt.java
 *
 * Scratch Code Analysis Tool for Teachers (SCATT).
 *
 * @author Emily Macdonald
 * @version Spr 2019
 */
public class Scatt
{
    /** main
     *
     * Main method for Scatt class.
     *
     * @param args
     * @return none
     */
    public static void main(String[] args)
    {
        String dirName = "./submissions/";
        File directory = new File(dirName);
	    File[] sbps = directory.listFiles();
        Arrays.sort(sbps);

        Submission[] submissions = new Submission[sbps.length];
        submissions[0] = new Submission(sbps[0]);
    }
}
