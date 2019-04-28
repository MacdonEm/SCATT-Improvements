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

    ArrayList<StageParser> stageTargets;
    ArrayList<SpriteParser> spriteTargets;

    Ver3StagePars stage;      // Holding variable for current stage
    Ver3SpritePars sprite;    // Holding variable for current sprite


    /*
     * Run operations for v3.0 Offline Editor.
     */
    public Ver3ProjA(JSONObject sb3) {

        super(sb3);

        stageTargets = new ArrayList<Ver3StagePars>();
        spriteTargets = new ArrayList<Ver3SpritePars>();
        map = new Ver3CatMap();
        stage = new Ver3StagePars();
        sprite = new Ver3SpritePars();

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

        for (int i = 0; i <= targets.size(); i++) {
            targetObj = targets.get(i);

            if(isStage(targetObj)) {
                stage.setTargetObj(taretObj);
                createMap();
                createStage();
            } else {
                sprite.setTargetObj(taretObj);
                createMap();
                createSprite();
                spriteTargets.add(new Ver3SpritePars(targets.get(i)));
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
    private void createMap() {

        map.setTargetBlocks(FileUtils.getJSONArrayAttribute(targetObj, "blocks"));
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
    private void createStage() {

        stage.populate();
        stage.setScriptCount(stage.getName());
        stageTargets.add(stage);
    }

    /** createSprite: void
     *
     * Creates and populates an arraylist of sprites in project.
     *
     * @param none
     * @return none
     */
    private void createSprite() {

        sprite.populate();
        sprite.setScriptCount(sprite.getName());
        spriteTargets.add(sprite);
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

        JSONObject status = FileUtils.getJSONObject(index, "isStage");

        if (FileUtils.getJSONObject(status, "true") != null)
            return true;
        else if (FileUtils.getJSONObject(status, "false") != null)
            return false;
        else
            throw new NullPointerException("Error in isStage: Value was null");

        return;
    }

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
