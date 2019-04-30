import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.HashMap;

/** Ver2StagePars.java
 *
 * Class for parsing a stage in Scratch Version 2
 * projects. This will parse through the project.json
 * from the submitted Scratch project and calculate a basic
 * count of comments, scripts, sprites, sounds, blocks, costumes,
 * variables, and lists used. Blocks are separated by category.
 *
 * @author Emily Macdonald
 */
public class Ver2StagePars extends StageParser {

    String [] globalVariables;          // Array of target's variables
    String [] globalLists;              // Array of target's lists
    JSONObject sb2;                     // Target's Scratch JSON
    JSONObject jsonObj;                 // Quick fix, change later
    HashMap<String, String> categoryMap;// Quick fix, change later
    String name;                        // Target's name

    // Object Counts
    int scriptCountForStage;            // Number of target's scripts
    int variableCountForStage;          // Number of target's variables
    int listCountForStage;              // Number of target's lists
    int scriptCommentCountForStage;     // Number of target's comments
    int soundCountForStage;             // Number of target's sounds
    int costumeCountForStage;           // Number of target's costumes

    // Stage Blocks
    int controlBlocksForStage;          // Number of control blocks used
    int dataBlocksForStage;             // Number of data blocks used
    int eventsBlocksForStage;           // Number of event blocks used
    int looksBlocksForStage;            // Number of look blocks used
    int moreBlocksBlocksForStage;       // Number of moreBlocks blocks used
    int motionBlocksForStage;           // Number of motion blocks used
    int operatorsBlocksForStage;        // Number of operator blocks used
    int penBlocksForStage;              // Number of pen blocks used
    int sensingBlocksForStage;          // Number of sensing blocks used
    int soundBlocksForStage;            // Number of sound blocks used

    /*
     * constructor
     */
    public Ver2StagePars(JSONObject sb2, HashMap<String, String> categoryMap) {

        this.sb2 = sb2;
        jsonObj = sb2;
        this.categoryMap = categoryMap;
        name = FileUtils.getJSONAttribute(sb2, "objName");

        // Initialize attribute counts for stage
        scriptCountForStage = 0;
        variableCountForStage = 0;
        listCountForStage = 0;
        scriptCommentCountForStage = 0;
        soundCountForStage = 0;
        costumeCountForStage = 0;

        // Initialize block counts for stage
        controlBlocksForStage = 0;
        dataBlocksForStage = 0;
        eventsBlocksForStage = 0;
        looksBlocksForStage = 0;
        moreBlocksBlocksForStage = 0;
        motionBlocksForStage = 0;
        operatorsBlocksForStage = 0;
        penBlocksForStage = 0;
        sensingBlocksForStage = 0;
        soundBlocksForStage = 0;
    }

    /** populate: void
     *
     * Sets target's counts.
     *
     * @param none
     * @return none
     */
    public void populate() {

        populateAttribute();
        processScripts(FileUtils.getJSONArrayAttribute(sb2, "scripts"));
        populateGlobalVariables();
        populateGlobalLists();
    }

    /** populateAttributes: void
     *
     * Populate the attribute counts.
     *
     * @param none
     * @return none
     */
    private void populateAttribute() {

        scriptCountForStage = getCountForStage("scripts");
        variableCountForStage = getCountForStage("variables");
        listCountForStage = getCountForStage("lists");
        scriptCommentCountForStage = getCountForStage("scriptComments");
        soundCountForStage = getCountForStage("sounds");
        costumeCountForStage = getCountForStage("costumes");
    }

    /** populateGlobalVariables: void
     *
     * Populate the global variables array.
     *
     * @param none
     * @return none
     */
    private void populateGlobalVariables() {

        JSONArray varsArr = FileUtils.getJSONArrayAttribute(jsonObj, "variables");
        JSONObject varsObj = new JSONObject();

        globalVariables = new String[varsArr.size()];

        for (int i = 0; i < varsArr.size(); i++) {
            varsObj = (JSONObject) varsArr.get(i);
            globalVariables[i] = (String) varsObj.get("name");
        }
    }

    /** populateGlobalLists: void
     *
     * Populate the global lists array.
     *
     * @param none
     * @return none
     */
    private void populateGlobalLists() {

        JSONArray listsArr = FileUtils.getJSONArrayAttribute(jsonObj, "lists");
        JSONObject listsObj = new JSONObject();

        globalLists = new String[listsArr.size()];

        for (int i = 0; i < listsArr.size(); i++) {
            listsObj = (JSONObject) listsArr.get(i);
            globalLists[i] = (String) listsObj.get("listName");
        }
    }

//------------------------------------------------------------------------------
//                                   Helpers
//------------------------------------------------------------------------------

    /** getCategory: String
     *
     * Get category name for specified script name.
     *
     * @param scriptName - name being sought
     * @return category found, null if no mapping
     */
    private String getCategory(String scriptName) {

        return (String) categoryMap.get(scriptName);
    }

    /** processScripts: void
     *
     * Process scripts to count blocks by category. Done via
     * recursion.
     *
     * @param scripts - array of scripts for stage
     * @return none
     */
    private void processScripts(JSONArray scripts) {

        // Base Case: scripts is empty
        if (scripts.size() == 0)
            return;

        // Action: If first element is a String, it is the block name.
        //         Get it and and count its category.
        if (scripts.get(0) instanceof String) {
            String category = getCategory((String) scripts.get(0));

            if (category != null)
                switch (category) {
                    case "control":
                        controlBlocksForStage++;
                        break;
                    case "data":
                        dataBlocksForStage++;
                        break;
                    case "events":
                        eventsBlocksForStage++;
                        break;
                    case "looks":
                        looksBlocksForStage++;
                        break;
                    case "more blocks":
                        moreBlocksBlocksForStage++;
                        break;
                    case "motion":
                        motionBlocksForStage++;
                        break;
                    case "operators":
                        operatorsBlocksForStage++;
                        break;
                    case "pen":
                        penBlocksForStage++;
                        break;
                    case "sensing":
                        sensingBlocksForStage++;
                        break;
                    case "sound":
                        soundBlocksForStage++;
                        break;
                }
        }

        // Recursion: More elements in scripts that may
        //            represent embedded blocks.
        for (int i = 0; i < scripts.size(); i++)
            if (scripts.get(i) instanceof JSONArray)
                processScripts((JSONArray) scripts.get(i));

        return;
    }

    /** getCountForStage: int
     *
     * Helper method for all CountForStage methods.
     * Pass in JSON attribute name.
     * Get count of specified attribute for stage.
     *
     * @param attribute - name of JSON attribute being counted
     * @return items - count of attribute
     */
    public int getCountForStage(String attribute) {

        JSONArray items = FileUtils.getJSONArrayAttribute(sb2, attribute);
        return (int) items.size();
    }

//------------------------------------------------------------------------------
//                                  Accessors
//------------------------------------------------------------------------------

    /** getName: String
     *
     * Get target name.
     *
     * @param none
     * @return getScriptCountForStage - stage name
     */
    public String getName() { return name; }

    /** getScriptCountForStage: int
     *
     * Get script count for stage.
     *
     * @param none
     * @return getScriptCountForStage - number of total scripts
     */
    public int getScriptCountForStage() { return scriptCountForStage; }

    /** getVariableCountForStage: int
     *
     * Get variable count for stage.
     *
     * @param none
     * @return variableCountForStage - number of total variables
     */
    public int getVariableCountForStage() { return variableCountForStage; }

    /** getListCountForStage: int
     *
     * Get list count for stage.
     *
     * @param none
     * @return listCountForStage - number of total lists
     */
    public int getListCountForStage() { return listCountForStage; }

    /** getScriptCommentCountForStage: int
     *
     * Get script comment count for stage.
     *
     * @param none
     * @return scriptCommentCountForStage - number of total script comments
     */
    public int getScriptCommentCountForStage() { return scriptCommentCountForStage; }

    /** getSoundCountForStage: int
     *
     * Get sound count for stage.
     *
     * @param none
     * @return soundCountForStage - number of total sounds
     */
    public int getSoundCountForStage() { return soundCountForStage; }

    /** getCostumeCountForStage: int
     *
     * Get costume count for stage.
     *
     * @param none
     * @return costumeCountForStage - number of total "costumes" (stage backdrops)
     */
    public int getCostumeCountForStage() { return costumeCountForStage; }

    /** getControlBlocksForStage: int
     *
     * Get control block count.
     *
     * @param none
     * @return controlBlocksForStage - number of control blocks found
     */
    public int getControlBlocksForStage() { return controlBlocksForStage; }

    /** getDataBlocksForStage: int
     *
     * Get data block count.
     *
     * @param none
     * @return dataBlocksForStage - number of data blocks found
     */
    public int getDataBlocksForStage() { return dataBlocksForStage; }

    /** getEventsBlocksForStage: int
     *
     * Get events block count.
     *
     * @param none
     * @return eventsBlocksForStage - number of event blocks found
     */
    public int getEventsBlocksForStage() { return eventsBlocksForStage; }

    /** getLooksBlocksForStage: int
     *
     * Get looks block count.
     *
     * @param none
     * @return looksBlocksForStage - number of looks blocks found
     */
    public int getLooksBlocksForStage() { return looksBlocksForStage; }

    /** getMoreBlocksBlocksForStage: int
     *
     * Get more blocks block count.
     *
     * @param none
     * @return moreBlocksBlocksForStage - number of "more blocks" blocks found
     */
    public int getMoreBlocksBlocksForStage() {return moreBlocksBlocksForStage;}

    /** getMotionBlocksForStage: int
     *
     * Get motion block count.
     * Should always be 0 - motion blocks are not available for stage.
     *
     * @param none
     * @return motionBlocksForStage - number of motion blocks found
     */
    public int getMotionBlocksForStage() { return motionBlocksForStage; }

    /** getOperatorsBlocksForStage: int
     *
     * Get operators block count.
     *
     * @param none
     * @return operatorsBlocksForStage - number of operator blocks found
     */
    public int getOperatorsBlocksForStage() { return operatorsBlocksForStage; }

    /** getPenBlocksForStage: int
     *
     * Get pen block count.
     *
     * @param none
     * @return penBlocksForStage - number of pen blocks found
     */
    public int getPenBlocksForStage() { return penBlocksForStage; }

    /** getSensingBlocksForStage: int
     *
     * Get sensing block count.
     *
     * @param none
     * @return sensingBlocksForStage - number of sensing blocks found
     */
    public int getSensingBlocksForStage() { return sensingBlocksForStage; }

    /** getSoundBlocksForStage: int
     *
     * Get sound block count.
     *
     * @param none
     * @return soundBlocksForStage - number of sound blocks found
     */
    public int getSoundBlocksForStage() { return soundBlocksForStage; }

    /** getGlobalVariables: String[]
     *
     * Get the variables array.
     *
     * @param none
     * @return globalVariables - array of variables found
     */
    public String[] getGlobalVariables() { return globalVariables; }

    /** getGlobalLists: String[]
     *
     * Get the list array.
     *
     * @param none
     * @return globalLists - array of lists found
     */
    public String[] getGlobalLists() { return globalLists; }

    /** getStageVariableUsageCount: int
     *
     * Get global variable usage count from stage scripts.
     *
     *  @param var - the variable being counted
     *  @return count - number of times the variable is used in Stage
     */
    public int getStageVariableUsageCount(String var) {

        int count = 0;
        JSONArray stageJSON = FileUtils.getJSONArrayAttribute(jsonObj, "scripts");
        String stage = stageJSON.toString();
        int pos = stage.indexOf(var);

        while (pos >= 0) {
            pos += 1;
            count += 1;
            pos = stage.indexOf(var, pos);
        }

        return count;
    }

    /** getStageListUsageCount: int
     *
     * Get global list usage count.
     *
     * @param list - the list being counted
     * @return count - number of times the list is used in Stage
     */
    public int getStageListUsageCount(String list) {

        int count = 0;
        JSONArray stageJSON =
            FileUtils.getJSONArrayAttribute(jsonObj, "scripts");
        String stage = stageJSON.toString();
        int pos = stage.indexOf(list);

        while (pos >= 0) {
            pos += 1;
            count += 1;
            pos = stage.indexOf(list, pos);
        }

        return count;
    }

}
