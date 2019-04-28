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

    private JSONObject sbp;           // Scratch's project.JSON file
    private CategoryMap map;          // Categories and Blocks mapping
    private SpriteParser [] sprites;  // List of Sprites
    private StageParser stage;        // List of Stages

    /*
     * Basic Project Analyzer construcor
     */
    public ProjectAnalyzer(JSONObject sbp) { this.sbp = sbp; }

    // Analyzer's activation method, invokes all analyzing tools
    public abstract void analyzeProject();

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

    /** getSomething: something
     *
     * Retrieves <blank>.
     *
     * @param none
     * @return something
     */
    public something getSomething() { return; }

}
