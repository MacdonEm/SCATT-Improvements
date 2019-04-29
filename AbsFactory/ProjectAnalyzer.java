import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.HashMap;

/** ProjectAnalyzer.java
 *
 * Abstract class outlining how Scratch projects should be
 * analyzed. Also sets and maintains the variables involved.
 *
 * @author Emily Macdonald
 * @version Spring 2019
 */
public abstract class ProjectAnalyzer {

    private String projectName;       // Scratch's project name
    private JSONObject sbp;           // Scratch's project.JSON file
    private CategoryMap map;          // Categories and Blocks mapping
    private SpriteParser [] sprites;  // List of Sprites
    private StageParser stage;        // List of Stages
    private Report report;            // report file

    /*
     * Basic Project Analyzer construcor
     */
    public ProjectAnalyzer(JSONObject sbp, String projectName) {

        this.sbp = sbp;
        this.projectName = projectName;
    }

    // Analyzer's activation method, invokes all analyzing tools
    public abstract void analyzeProject();

    // Produces a report as a text file
    public abstract void produceTextReport();

    // Produces a report as a JSON file
    public abstract void produceJSONReport();

    // Counting analysis tool
    public abstract void computeCounts();

//------------------------------------------------------------------------------
//                                 Helpers
//------------------------------------------------------------------------------

    private abstract void createMap();        // Creates category map object
    private abstract void createStage();      // Creates stage object
    private abstract void createSprite();     // Creates sprite object

//------------------------------------------------------------------------------
//                                 Accessors
//------------------------------------------------------------------------------

    /** getName: String
     *
     * Get filename of submission.
     *
     * @param none
     * @return projectName - name of Scratch project file
     */
    public String getName() { return projectName; }

    /** getSomething: something
     *
     * Retrieves <blank>.
     *
     * @param none
     * @return something
     */
    public something getSomething() { return; }

}
