import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.ArrayList;
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

    String projectName;                       // Scratch's project name
    JSONObject sbp;                           // Scratch's project.JSON file
    TextReport report;                        // Report file

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

    protected abstract void createMap();        // Creates category map object
    protected abstract void createStage();      // Creates stage object
    protected abstract void createSprite();     // Creates sprite object
    protected abstract void createTotals();     // Calculates totals

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

}
