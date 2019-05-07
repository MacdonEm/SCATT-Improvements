import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.HashMap;
import java.util.ArrayList;

/** Ver3ProjA.java
 *
 * Analyzer factory to process version 3 Scratch projects
 *
 * @author Emily Macdonald
 */
public class Ver3ProjA extends ProjectAnalyzer {

    JSONArray targets;        // Array of stage/sprite targets
    JSONArray targetBlocks;   // Array of a target's blocks
    JSONObject targetObj;     // JSON for current target

    ArrayList<Ver3StagePars> stageTargets;
    ArrayList<Ver3SpritePars> spriteTargets;
    Ver3CatMap map;

    Ver3StagePars stage;      // Holding variable for current stage
    Ver3SpritePars sprite;    // Holding variable for current sprite
    boolean ctFlag;           // Lets totals know if it's calculating a stage or a sprite

    int scriptTotals;         // Total scripts
    int variableTotals;       // Total variables
    int listTotals;           // Total lists
    int soundTotals;          // Total sounds
    int commentTotals;        // Total comments
    int costumeTotals;        // Total costumes
    int controlBTotals;       // Total control blocks
    int dataBTotals;          // Total data blocks
    int eventBTotals;         // Total event blocks
    int lookBTotals;          // Total look blocks
    int motionBTotals;        // Total motion blocks
    int operatorBTotals;      // Total operator blocks
    int sensingBTotals;       // Total sensing blocks
    int soundBTotals;         // Total sound blocks
    int myBlockBTotals;       // Total my block blocks


    /*
     * Run operations for v3.0 Offline Editor.
     */
    public Ver3ProjA(JSONObject sb3, String projectName) {

        super(sb3, projectName);

        stageTargets = new ArrayList<Ver3StagePars>();
        spriteTargets = new ArrayList<Ver3SpritePars>();
        map = new Ver3CatMap();
        report = new TextReport(projectName);

        ctFlag = false;
        scriptTotals = 0;
        variableTotals = 0;
        listTotals = 0;
        soundTotals = 0;
        commentTotals = 0;
        costumeTotals = 0;
        controlBTotals = 0;
        dataBTotals = 0;
        eventBTotals = 0;
        lookBTotals = 0;
        motionBTotals = 0;
        operatorBTotals = 0;
        sensingBTotals = 0;
        soundBTotals = 0;
        myBlockBTotals = 0;

        targets = FileUtils.getJSONArrayAttribute(sb3, "targets");
    }

    /** analyzeProject: void
     *
     * Computes project analysis. Analysis includes:
     *     -> Project Counts
     *
     * @param none
     * @return none
     */
    public void analyzeProject() {

        computeCounts();
    }

    /** produceTextReport: void
     *
     * Produces a text report file.
     *
     * @param none
     * @return none
     */
    public void produceTextReport() {

        System.out.println("Generating report.txt...");

        report.beginReport();
        /* Totals */
        report.totalHeader();
        // You forgot to compute totals, so do that.
            report.printTotalCounts("sprite", (stageTargets.size() + spriteTargets.size()));    // Sprite count
            report.printTotalCounts("script", scriptTotals);                                    // Script count
            report.printTotalCounts("variable", variableTotals);                                // Variable count
            report.printTotalCounts("list", listTotals);                                        // List count
            report.printTotalCounts("sound", soundTotals);                                      // Sound count
            report.printTotalCounts("comment", commentTotals);                                  // Comment count
            report.printTotalCounts("costume", costumeTotals);                                  // Costume count
            report.printTotalCounts("controlB", controlBTotals);                                // Control block count
            report.printTotalCounts("dataB", dataBTotals);                                      // Data block count
            report.printTotalCounts("eventB", eventBTotals);                                    // Event block count
            report.printTotalCounts("lookB", lookBTotals);                                      // Look block count
            report.printTotalCounts("motionB", motionBTotals);                                  // Motion block count
            report.printTotalCounts("operatorB", operatorBTotals);                              // Operator block count
            report.printTotalCounts("sensingB", sensingBTotals);                                // Sensing block count
            report.printTotalCounts("soundB", soundBTotals);                                    // Sound block count
            report.printTotalCounts("myBlockB", myBlockBTotals);                                // MyBlock block count

        /* Stages */
        report.stageHeader();
        for (int i = 0; i < stageTargets.size(); i++) {
            report.printStageCounts("sprite", spriteTargets.size());                          // Sprite count
            report.printStageCounts("script", stageTargets.get(i).getScripts());              // Script count
            report.printStageCounts("variable", stageTargets.get(i).getVarCount());           // Variable count
            report.printStageCounts("list", stageTargets.get(i).getLisCount());               // List count
            report.printStageCounts("sound", stageTargets.get(i).getSounds());                // Sound count
            report.printStageCounts("comment", stageTargets.get(i).getComments());            // Comment count
            report.printStageCounts("costume", stageTargets.get(i).getCostumes());            // Costume count
            report.printStageCounts("controlB", stageTargets.get(i).getControlCount());       // Control block count
            report.printStageCounts("dataB", stageTargets.get(i).getDataCount());             // Data block count
            report.printStageCounts("eventB", stageTargets.get(i).getEventCount());           // Event block count
            report.printStageCounts("lookB", stageTargets.get(i).getLookCount());             // Look block count
            report.printStageCounts("motionB", stageTargets.get(i).getMotionCount());         // Motion block count
            report.printStageCounts("operatorB", stageTargets.get(i).getOperatorCount());     // Operator block count
            report.printStageCounts("sensingB", stageTargets.get(i).getSensingCount());       // Sensing block count
            report.printStageCounts("soundB", stageTargets.get(i).getSoundCount());           // Sound block count
            report.printStageCounts("myBlockB", stageTargets.get(i).getMyBlockCount());       // MyBlock block count
        }

        /* Sprites */
        report.spriteHeader();
        for (int i = 0; i < spriteTargets.size(); i++) {
            report.printSpriteCounts("sprite", 0, spriteTargets.get(i).getName());                  // Sprite name
            report.printSpriteCounts("script", spriteTargets.get(i).getScripts(), "");              // Script count
            report.printSpriteCounts("variable", spriteTargets.get(i).getVarCount(), "");           // Variable count
            report.printSpriteCounts("list", spriteTargets.get(i).getLisCount(), "");               // List count
            report.printSpriteCounts("sound", spriteTargets.get(i).getSounds(), "");                // Sound count
            report.printSpriteCounts("comment", spriteTargets.get(i).getComments(), "");            // Comment count
            report.printSpriteCounts("costume", spriteTargets.get(i).getCostumes(), "");            // Costume count
            report.printSpriteCounts("controlB", spriteTargets.get(i).getControlCount(), "");       // Control block count
            report.printSpriteCounts("dataB", spriteTargets.get(i).getDataCount(), "");             // Data block count
            report.printSpriteCounts("eventB", spriteTargets.get(i).getEventCount(), "");           // Event block count
            report.printSpriteCounts("lookB", spriteTargets.get(i).getLookCount(), "");             // Look block count
            report.printSpriteCounts("motionB", spriteTargets.get(i).getMotionCount(), "");         // Motion block count
            report.printSpriteCounts("operatorB", spriteTargets.get(i).getOperatorCount(), "");     // Operator block count
            report.printSpriteCounts("sensingB", spriteTargets.get(i).getSensingCount(), "");       // Sensing block count
            report.printSpriteCounts("soundB", spriteTargets.get(i).getSoundCount(), "");           // Sound block count
            report.printSpriteCounts("myBlockB", spriteTargets.get(i).getMyBlockCount(), "");       // MyBlock block count
	    report.lineBreak();
        }
        report.finishReport();
    }

    /** produceJSONReport: void
     *
     * Produces a JSON report file.
     *
     * @param none
     * @return none
     */
    public void produceJSONReport() {

        // System.out.println("Generating report.json...");
        // report = new JSONReport(projectName);
        System.out.println("Report.json feature coming soon.\n" +
                           "A Report.txt will be created instead.");
        produceTextReport();
    }

    /** computeCounts: void
     *
     * Generates counts for the project. Counts include the following items per
     * each stage and sprite involed in the project, as well as overall totals.
     *     -> Variables
     *     -> Lists
     *     -> Comments
     *     -> Costumes
     *     -> Sounds
     *     -> Scripts
     *     -> Block Categories
     *
     * @param none
     * @return none
     */
    public void computeCounts() {


        for (int i = 0; i < targets.size(); i++) {
            targetObj = (JSONObject) targets.get(i);

            if(isStage(targetObj))
                stageTargets.add(new Ver3StagePars());
            else
                spriteTargets.add(new Ver3SpritePars());
        }

        int stageIndex = 0;
        int spriteIndex = 0;

        for (int i = 0; i < targets.size(); i++) {
            targetObj = (JSONObject) targets.get(i);

            if(isStage(targetObj)) { 
                stage = stageTargets.get(stageIndex);
                stage.setTargetObj(targetObj);
                createMap();
                createStage();
                ctFlag = true;    // createTotals operates with a stage
                createTotals();
                stageIndex++;
            } else {
                sprite = spriteTargets.get(spriteIndex);
                sprite.setTargetObj(targetObj);
                createMap();
                createSprite();
                ctFlag = false;    // createTotals operates with a sprite
                createTotals();
                spriteIndex++;
            }
        }
    }

//------------------------------------------------------------------------------
//                                 Helpers
//------------------------------------------------------------------------------

    /** createMap: void
     *
     * Creates and populates a category map. Version 3 category maps are set up
     * as Stage/Sprite to Scripts to Blocks.
     *
     * @param none
     * @return none
     */
    protected void createMap() {

        map.setTargetBlocks(targetObj);
        map.setTargetName(FileUtils.getJSONAttribute(targetObj, "name"));
        map.populate();
    }

    /** createStage: void
     *
     * Creates and populates an arraylist of stages in project.
     *
     * @param none
     * @return none
     */
    protected void createStage() {

        stage.populate();
        stage.setScriptCount(map.getScriptSize(stage.getName()));
    }

    /** createSprite: void
     *
     * Creates and populates an arraylist of sprites in project.
     *
     * @param none
     * @return none
     */
    protected void createSprite() {

        sprite.populate();
        sprite.setScriptCount(map.getScriptSize(sprite.getName()));
    }

    /** createTotals: void
     *
     * Calculate totals for project.
     *
     * @param none
     * @return none
     */
    protected void createTotals() {

        if (ctFlag) {    // calculate totals with stage
            scriptTotals += map.getScriptSize(stage.getName());
            variableTotals += stage.getVarCount();
            listTotals += stage.getLisCount();
            soundTotals += stage.getSounds();
            commentTotals += stage.getComments();
            costumeTotals += stage.getCostumes();
            controlBTotals += stage.getControlCount();
            dataBTotals += stage.getDataCount();
            eventBTotals += stage.getEventCount();
            lookBTotals += stage.getLookCount();
            motionBTotals += stage.getMotionCount();
            operatorBTotals += stage.getOperatorCount();
            sensingBTotals += stage.getSensingCount();
            soundBTotals += stage.getSoundCount();
            myBlockBTotals += stage.getMyBlockCount();
        } else {         // calculate totals with sprite
            scriptTotals += map.getScriptSize(sprite.getName());
            variableTotals += sprite.getVarCount();
            listTotals += sprite.getLisCount();
            soundTotals += sprite.getSounds();
            commentTotals += sprite.getComments();
            costumeTotals += sprite.getCostumes();
            controlBTotals += sprite.getControlCount();
            dataBTotals += sprite.getDataCount();
            eventBTotals += sprite.getEventCount();
            lookBTotals += sprite.getLookCount();
            motionBTotals += sprite.getMotionCount();
            operatorBTotals += sprite.getOperatorCount();
            sensingBTotals += sprite.getSensingCount();
            soundBTotals += sprite.getSoundCount();
            myBlockBTotals += sprite.getMyBlockCount();
        }
    }

    /** isStage: boolean
     *
     * Assesses the type of target being dealt with.
     * NullPointerException indicates error.
     *
     * @param index JSONObject - target being assessed
     * @return true  - target is stage
     *         false - target is sprite
     */
    private boolean isStage(JSONObject index) {

        /* String status = FileUtils.getJSONAttribute(index, "isStage");

        if (status.equals("true"))
            return true;
        else if (status.equals("false"))
            return false;
        else
            throw new NullPointerException("Error in isStage: Value was null"); */

        boolean status = FileUtils.getJSONBooleanAttribute(index, "isStage");

        if (status)
            return true;
        else
            return false;
    }

}
