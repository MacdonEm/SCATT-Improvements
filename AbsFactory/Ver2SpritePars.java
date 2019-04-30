import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.HashMap;

/** Ver2SpritePars.java
 *
 * Class for parsing a stage in Scratch Version 2
 * projects. This will parse through the project.json
 * from the submitted Scratch project and calculate a basic
 * count of comments, scripts, sounds, blocks, costumes, variables,
 * and lists used. Blocks are separated by category.
 *
 * @author Emily Macdonald
 */
public class Ver2SpritePars extends SpriteParser {

    String[] variables;                // Array of target's variables
    String[] lists;                    // Array of target's lists

    JSONObject sb2;                    // Target's Scratch JSON
    JSONObject jsonObj;		       // Quick fix, change later
    HashMap<String, String> categoryMap;// Quick fix, change later
    String name;                       // Target's name
    int scriptCount;                   // Number of target's scripts
    int variableCount;                 // Number of target's variables
    int listCount;                     // Number of target's lists
    int scriptCommentCount;            // Number of target's comments
    int soundCount;                    // Number of target's sounds
    int costumeCount;                  // Number of target's costumes

    int controlBlocksForSprite;        // Number of control blocks used
    int dataBlocksForSprite;           // Number of data blocks used
    int eventsBlocksForSprite;         // Number of event blocks used
    int looksBlocksForSprite;          // Number of look blocks used
    int moreBlocksBlocksForSprite;     // Number of moreBlocks blocks used
    int motionBlocksForSprite;         // Number of motion blocks used
    int operatorsBlocksForSprite;      // Number of operator blocks used
    int penBlocksForSprite;            // Number of pen blocks used
    int sensingBlocksForSprite;        // Number of sensing blocks used
    int soundBlocksForSprite;          // Number of sound blocks used

    /*
     * constructor
     */
    public Ver2SpritePars(JSONObject sb2, HashMap<String, String> categoryMap) {

        this.sb2 = sb2;
        jsonObj = sb2;
        this.categoryMap = categoryMap;

        name = FileUtils.getJSONAttribute(jsonObj, "objName");
        scriptCount = 0;
        variableCount = 0;
        listCount = 0;
        scriptCommentCount = 0;
        soundCount = 0;
        costumeCount = 0;

        controlBlocksForSprite = 0;
        dataBlocksForSprite = 0;
        eventsBlocksForSprite = 0;
        looksBlocksForSprite = 0;
        moreBlocksBlocksForSprite = 0;
        motionBlocksForSprite = 0;
        operatorsBlocksForSprite = 0;
        penBlocksForSprite = 0;
        sensingBlocksForSprite = 0;
        soundBlocksForSprite = 0;
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
        populateVariables();
        populateLists();
    }

    /** populateAttributes: void
     *
     * Populate the attribute counts.
     *
     * @param none
     * @return none
     */
    private void populateAttribute() {

        scriptCount = setCount("scripts");
        variableCount = setCount("variables");
        listCount = setCount("lists");
        scriptCommentCount = setCount("scriptComments");
        soundCount = setCount("sounds");
        costumeCount = setCount("costumes");
    }

    /** populateVariables: void
     *
     * Populate the array of variables.
     *
     * @param none
     * @return none
     */
    private void populateVariables() {

        JSONArray vars =
            FileUtils.getJSONArrayAttribute(jsonObj, "variables");
        variables = new String[vars.size()];
        JSONObject children = new JSONObject();

        for (int i = 0; i < vars.size(); i++) {
            children = (JSONObject) vars.get(i);
            variables[i] =
                FileUtils.getJSONAttribute(children, "name");;
        }
    }

    /** populateLists: void
     *
     * Populate the array of lists.
     *
     * @param none
     * @return none
     */
    private void populateLists() {

        JSONArray listArray =
            FileUtils.getJSONArrayAttribute(jsonObj, "lists");
        lists = new String[listArray.size()];
        JSONObject children = new JSONObject();

        for (int i = 0; i < listArray.size(); i++) {
            children = (JSONObject) listArray.get(i);
            lists[i] =
                FileUtils.getJSONAttribute(children, "listName");
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
     * @return category found, nnull if no mapping
     */
    private String getCategory(String scriptName) {

        return (String) categoryMap.get(scriptName);
    }

    /** processScripts: void
     *
     * Process scripts to count blocks by category.
     *
     * @param array - array of scripts for sprite
     * @return none
     */
    private void processScripts(JSONArray array) {

        // Base Case: scripts is empty
        if (array == null || array.size() == 0)
            return;

        // Action: If first element is a String, it is the block name.
        //         Get it and and count its category.
        if (array.get(0) instanceof String) {
            String category = getCategory((String) array.get(0));

            if (category != null)
                switch (category) {
                    case "control":
                        controlBlocksForSprite++;
                        break;
                    case "data":
                        dataBlocksForSprite++;
                        break;
                    case "events":
                        eventsBlocksForSprite++;
                        break;
                    case "looks":
                        looksBlocksForSprite++;
                        break;
                    case "more blocks":
                        moreBlocksBlocksForSprite++;
                        break;
                    case "motion":
                        motionBlocksForSprite++;
                        break;
                    case "operators":
                        operatorsBlocksForSprite++;
                        break;
                    case "pen":
                        penBlocksForSprite++;
                        break;
                    case "sensing":
                        sensingBlocksForSprite++;
                        break;
                    case "sound":
                        soundBlocksForSprite++;
                        break;
                    default:
                        break;
                }
        }

        // Recursion: More elements in scripts that may
        //            represent embedded blocks.
        for (int i = 0; i < array.size(); i++)
            if (array.get(i) instanceof JSONArray)
                processScripts((JSONArray) array.get(i));

        return;
    }

    /** setCount: int
     *
     * Helper method for all CountForStage methods. Pass in JSON attribute name
     * to get count of specified attribute.
     *
     * @param attribute - name of JSON attribute being counted
     * @return items - count of attribute
     */
    public int setCount(String attribute) {

        JSONArray items = FileUtils.getJSONArrayAttribute(sb2, attribute);
        return (int) items.size();
    }

//------------------------------------------------------------------------------
//                                  Accessors
//------------------------------------------------------------------------------

    /** getName: String
     *
     * Get sprite name.
     *
     * @param none
     * @return name - name of the sprite
     */
    public String getName() { return name; }

    /** getScriptCount: int
     *
     * Get script count for sprite.
     *
     * @param none
     * @return scriptCount - number of scripts found
     */
    public int getScriptCount() { return scriptCount; }

    /** getVariableCount: int
     *
     * Get variable count for sprite.
     *
     * @param none
     * @return variableCount - number of variables found
     */
    public int getVariableCount() { return variableCount; }

    /** getListCount: int
     *
     * Get list count for sprite.
     *
     * @param none
     * @return listCount - number of lists found
     */
    public int getListCount() { return listCount; }

    /** getScriptCommentCount: int
     *
     * Get script comment count for sprite.
     *
     * @param none
     * @return scriptCommentCount - number of script comments found
     */
    public int getScriptCommentCount() { return scriptCommentCount; }

    /** getSoundCount: int
     *
     * Get sound count for sprite.
     *
     * @param none
     * @return soundCount - number of sounds found
     */
    public int getSoundCount() { return soundCount; }

    /** getCostumeCount: int
     *
     * Get costume count for sprite.
     *
     * @param none
     * @return costumeCount - number of costumes found
     */
    public int getCostumeCount() { return costumeCount; }

    /** getControlBlocksForSprite: int
     *
     * Get control block count for sprite.
     *
     * @param none
     * @return controlBlocksForSprite - number of control blocks found
     */
    public int getControlBlocksForSprite() { return controlBlocksForSprite; }

    /** getDataBlocksForSprite: int
     *
     * Get data block count for sprite.
     *
     * @param none
     * @return dataBlocksForSprite - number of data blocks found
     */
    public int getDataBlocksForSprite() { return dataBlocksForSprite; }

    /** getEventsBlocksForSprite: int
     *
     * Get events block count for sprite.
     *
     * @param none
     * @return eventsBlocksForSprite - number of events blocks found
     */
    public int getEventsBlocksForSprite() { return eventsBlocksForSprite; }

    /** getLooksBlocksForSprite: int
     *
     * Get looks block count for sprite.
     *
     * @param none
     * @return looksBlocksForSprite - number of looks blocks found
     */
    public int getLooksBlocksForSprite() { return looksBlocksForSprite; }

    /** getMoreBlocksBlocksForSprite: int
     *
     * Get more blocks block count for sprite.
     *
     * @param none
     * @return moreBlocksBlocksForSprite - number of "more blocks" blocks found
     */
    public int getMoreBlocksBlocksForSprite() { return moreBlocksBlocksForSprite; }

    /** getMotionBlocksForSprite: int
     *
     * Get motion block count for sprite.
     *
     * @param none
     * @return motionBlocksForSprite - number of motion blocks found
     */
    public int getMotionBlocksForSprite() { return motionBlocksForSprite; }

    /** getOperatorsBlocksForSprite: int
     *
     * Get operators block count for sprite.
     *
     * @param none
     * @return operatorsBlocksForSprite - number of operator blocks found
     */
    public int getOperatorsBlocksForSprite() { return operatorsBlocksForSprite; }

    /** getPenBlocksForSprite: int
     *
     * Get pen block count for sprite.
     *
     * @param none
     * @return penBlocksForSprite - number of pen blocks found
     */
    public int getPenBlocksForSprite() { return penBlocksForSprite; }

    /** getSensingBlocksForSprite: int
     *
     * Get sensing block count for sprite.
     *
     * @param none
     * @return sensingBlocksForSprite - number of sensing blocks found
     */
    public int getSensingBlocksForSprite() { return sensingBlocksForSprite; }

    /** getSoundBlocksForSprite: int
     *
     * Get sound block count for sprite.
     *
     * @param none
     * @return soundBlocksForSprite - number of sound blocks found
     */
    public int getSoundBlocksForSprite() { return soundBlocksForSprite; }

    /** getVariables: String[]
     *
     * Get list of variables used.
     *
     * @param none
     * @return variables - array of variables found
     */
    public String[] getVariables() { return variables; }

    /** getLists: String[]
     *
     * Get names of lists used.
     *
     * @param none
     * @return lists - array of lists found
     */
    public String[] getLists() { return lists; }

    /** getVariableUsageCount: int
     *
     * Get variable usage count.
     *
     * @param var - the variable being counted
     * @return count - the number of times the variable is used
     */
    public int getVariableUsageCount(String var) {

        int count = 0;
        JSONArray scripts =
            FileUtils.getJSONArrayAttribute(jsonObj, "scripts");
        String spriteScript = scripts.toString();
        int pos = spriteScript.indexOf(var);

        while (pos >= 0) {
            pos += 1;
            count += 1;
            pos = spriteScript.indexOf(var, pos);
        }

        return count;
    }

    /** getListUsageCount: int
     *
     * Get list usage count.
     *
     * @param list - the list being counted
     * @return count - the number of times the list is used
     */
    public int getListUsageCount(String list) {

        int count = 0;
        JSONArray scripts =
            FileUtils.getJSONArrayAttribute(jsonObj, "scripts");
        String spriteScript = scripts.toString();
        int pos = spriteScript.indexOf(list);

        while (pos >= 0) {
            pos += 1;
            count += 1;
            pos = spriteScript.indexOf(list, pos);
        }

        return count;
    }
}
