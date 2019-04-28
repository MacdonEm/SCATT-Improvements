import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/** Var3SpritePars.java
 *
 * Performs a version 3 sprite parsing.
 *
 * @author Emily Macdonald
 * @version Spring 2019
 */
public class Ver3SpritePars implements SpriteParser {

    JSONObject targetObj;       // Target's Scratch JSON
    String[] variables;         // Array of target's variables
    String[] lists;             // Array of target's lists

    // Object Counts
    String objName;             // Target's name
    int variables;              // Number of target's variables
    int lists;                  // Number of target's lists
    int comments;               // Number of target's comments
    int costumes;               // Number of target's costumes
    int sounds;                 // Number of target's sounds
    int scripts;                // Number of target's scripts

    // Block Category Counts
    int controlCount;           // Number of control blocks used
    int dataCount;              // Number of data blocks used
    int eventCount;             // Number of event blocks used
    int lookCount;              // Number of look blocks used
    int motionCount;            // Number of motion blocks used
    int operatorCount;          // Number of operator blocks used
    int sensingCount;           // Number of sensing blocks used
    int soundCount;             // Number of sound blocks used
    int myBlockCount;           // Number of myBlock blocks used

    /*
     * Creates a version 3 stage object.
     */
    public Ver3SpritePars() {

        targetObj = null;
        variables = null;
        lists = null;
        objName = "";
        comments = 0;
        costumes = 0;
        sounds = 0;
        controlCount = 0;
        dataCount = 0;
        eventCount = 0;
        lookCount = 0;
        motionCount = 0;
        operatorCount = 0;
        sensingCount = 0;
        soundCount = 0;
        myBlockCount = 0;
    }

    /** populate: void
     *
     * Sets target's counts.
     *
     * @param none
     * @return none
     */
    public void populate() {

        populateBlockCount();
        populateObjectCount();
    }

    /** populateObjectCount: void
     *
     * Populates target's object counts.
     *
     * @param none
     * @return none
     */
    private void populateObjectCount() {

        targetVariables();
        targetLists();
        targetName();
        varCount();
        lisCount();
        targetComments();
        targetCostumes();
        targetSounds();
    }

    /** populateBlocktCount: void
     *
     * Populates target's block category counts.
     *
     * @param none
     * @return none
     */
    private void populateBlockCount() {

        JSONArray blocksList =
            FileUtils.getJSONArrayAttribute(targetObj, "blocks");
        String block;
        String[] str;

        for (int i = 0; i < blocks.size(); i++) {
            block = FileUtils.getJSONAttribute(blocksList.get(i), "opcode");
            populateCount(block.split("_")[0]);
        }
    }

//------------------------------------------------------------------------------
//                                 Helpers
//------------------------------------------------------------------------------

    /** targetVariables: void
     *
     * Retrieve target's variables.
     *
     * @param none
     * @return none
     */
    private void targetVariables() {

        JSONArray master = FileUtils.getJSONArrayAttribute(targetObj,"variables");
        JSONArray index;
        variables = new String[master.size()];

        for(int i = 0; i < master.size(); i++) {
            index =
                FileUtils.getJSONArrayAttribute(master.get(i), (String)master.get(i));
            variables[i] = (String)index.get(0);
        }
    }

    /** targetLists: void
     *
     * Retrieve target's lists.
     *
     * @param none
     * @return none
     */
    private void targetLists() {

        JSONArray master = FileUtils.getJSONArrayAttribute(targetObj,"variables");
        JSONArray index;
        variables = new String[master.size()];

        for(int i = 0; i < master.size(); i++) {
            index =
                FileUtils.getJSONArrayAttribute(master.get(i), (String)master.get(i));
            variables[i] = (String)index.get(0);
        }
    }

    /** targetName: void
     *
     * Retrieve target's name.
     *
     * @param none
     * @return none
     */
    private void targetName() {

        objName = FileUtils.getJSONAttribute(targetObj, "name");
    }

    /** varCount: void
     *
     * Retrieve variable count.
     *
     * @param none
     * @return none
     */
    private void varCount() { varCount = variable.length(); }

    /** lisCount: void
     *
     * Retrieve list count.
     *
     * @param none
     * @return none
     */
    private void lisCount() { lisCount = lists.length(); }

    /** targetComments: void
     *
     * Retrieve target's comments.
     *
     * @param none
     * @return none
     */
    private void targetComments() {

        comments = FileUtils.getJSONArrayAttribute(targetObj, "comments").size();
    }

    /** targetCostumes: void
     *
     * Retrieve target's costumes.
     *
     * @param none
     * @return none
     */
    private void targetCostumes() {

        costumes = FileUtils.getJSONArrayAttribute(targetObj, "costumes").size();
    }

    /** targetName: void
     *
     * Retrieve target's sounds.
     *
     * @param none
     * @return none
     */
    private void targetSounds() {

        sounds = FileUtils.getJSONArrayAttribute(targetObj, "sounds").size();
    }

    /** findCategory: void
     *
     * Updates specified count.
     *
     * @param blockCat - category of block
     * @return none
     */
    private void populateCount(String blockCat) {

        switch (blockCat) {
            case "control" :
                controlCount++;
                break;
            case "data" :
                dataCount++;
                break;
            case "event" :
                eventCount++;
                break;
            case "look" :
                lookCount++;
                break;
            case "motion" :
                motionCount++;
                break;
            case "operator" :
                operatorCount++;
                break;
            case "sensing" :
                sensingCount++;
                break;
            case "sound" :
                soundCount++;
                break;
            case "procedures" :
                myBlockCount++;
                break;
        }
    }

//------------------------------------------------------------------------------
//                                 Accessors
//------------------------------------------------------------------------------

    /** getName: String
     *
     * Access the target's name.
     *
     * @param none
     * @return objName - target's name
     */
    public String getName() { return objName; }

    /** getVariables: String[]
     *
     * Access the target's user made variables.
     *
     * @param none
     * @return variables - array of target's variables
     */
    public String[] getVariables() { return variables; }

    /** getLists: String[]
     *
     * Access the target's user made lists.
     *
     * @param none
     * @return lists - array of target's lists
     */
     public String[] getLists() { return lists; }

    /** getComments: int
     *
     * Access the target's comments count.
     *
     * @param none
     * @return comments - target's comments
     */
    public int getComments() { return comments; }

    /** getCostumes: int
     *
     * Access the target's costumes count.
     *
     * @param none
     * @return costumes - target's costumes
     */
    public int getCostumes() { return costumes; }

    /** getSounds: int
     *
     * Access the target's sounds count.
     *
     * @param none
     * @return sounds - target's sounds
     */
    public int getSounds() { return sounds; }

    /** getScripts: int
     *
     * Access the target's script count.
     *
     * @param none
     * @return scripts - target's scripts
     */
    public int getScripts() { return scripts; }

    /** getControlCount: int
     *
     * Access the target's total control blocks used.
     *
     * @param none
     * @return controlCount - target's controlCount
     */
    public int getControlCount() { return controlCount; }

    /** getDataCount: int
     *
     * Access the target's total data blocks used.
     *
     * @param none
     * @return dataCount - target's dataCount
     */
    public int getDataCount() { return dataCount; }

    /** getEventCount: int
     *
     * Access the target's total event blocks used.
     *
     * @param none
     * @return eventCount - target's eventCount
     */
    public int getEventCount() { return eventCount; }

    /** getLookCount: int
     *
     * Access the target's total look blocks used.
     *
     * @param none
     * @return lookCount - target's lookCount
     */
    public int getLookCount() { return lookCount; }

    /** getMotionCount: int
     *
     * Access the target's total motion blocks used.
     *
     * @param none
     * @return motionCount - target's motionCount
     */
    public int getMotionCount() { return motionCount; }

    /** getOperatorCount: int
     *
     * Access the target's total operator blocks used.
     *
     * @param none
     * @return operatorCount - target's operatorCount
     */
    public int getOperatorCount() { return operatorCount; }

    /** getSensingCount: int
     *
     * Access the target's total sensing blocks used.
     *
     * @param none
     * @return sensingCount - target's sensingCount
     */
    public int getSensingCount() { return sensingCount; }

    /** getSoundCount: int
     *
     * Access the target's total sound blocks used.
     *
     * @param none
     * @return soundCount - target's soundCount
     */
    public int getSoundCount() { return soundCount; }

    /** getMyBlockCount: int
     *
     * Access the target's total myBlock blocks used.
     *
     * @param none
     * @return myBlockCount - target's myBlockCount
     */
    public int getMyBlockCount() { return myBlockCount; }

//------------------------------------------------------------------------------
//                                 Mutators
//------------------------------------------------------------------------------

    /** setTargetObj: void
     *
     * Set the targetObj to pull from.
     *
     * @param targetObj - JSONObject of target
     * @return none
     */
    public void setTargetObj(JSONArray targetObj) { this.targetObj = targetObj; }

    /** setScriptCount: void
     *
     * Set target's scripts based on given value.
     *
     * @param sz - target's number of scripts based off catmap
     * @return none
     */
    public void setScriptCount(int sz) { scripts = sz; }


}
