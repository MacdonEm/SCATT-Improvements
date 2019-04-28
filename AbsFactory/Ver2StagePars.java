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
public class Ver2StagePars implements StageParser {

    private String [] globalVariables;
    private String [] globalLists;

    private JSONObject sb2;

    // Stage Blocks
    private int controlBlocksForStage;
    private int dataBlocksForStage;
    private int eventsBlocksForStage;
    private int looksBlocksForStage;
    private int moreBlocksBlocksForStage;
    private int motionBlocksForStage;
    private int operatorsBlocksForStage;
    private int penBlocksForStage;
    private int sensingBlocksForStage;
    private int soundBlocksForStage;

    /** constructor
     *
     * @param JSONObject sb2 - json file of the submitted Scratch project
     *                         (Whole file)
     */
    public Ver2StagePars(JSONObject sb2) {

        this.sb2 = sb2;
    }

    /** processScripts: void
     *
     * Process scripts to count blocks by category. Done via
     * recursion.
     *
     * @param JSONArray scripts - array of scripts for stage
     * @return none
     */
    private void processScripts(JSONArray scripts) {

        // Base Case: scripts is empty
        if (scripts.size() == 0)
            return;

        // Action: If first element is a String, it is the block name.
        //         Get it and and count its category.
        if (scripts.get(0) instanceof String) {
            String category = Ver2ProjA.getCategory((String) scripts.get(0));

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

    /** populateAttributes: void
     *
     * Populate the attribute counts.
     *
     * @param none
     * @return none
     **/
    public void populateAttribute(int add) {

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

        JSONArray varsArr =
            FileUtils.getJSONArrayAttribute(jsonObj, "variables");
        JSONObject varsObj = new JSONObject();

        globalVariables = new String[varsArr.size()];

        for (int i = 0; i < varsArr.size(); i++) {
            listsObj = (JSONObject) varsArr.get(i);
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

        JSONArray listsArr =
            FileUtils.getJSONArrayAttribute(jsonObj, "lists");
        JSONObject listsObj = new JSONObject();

        globalLists = new String[listsArr.size()];

        for (int i = 0; i < listsArr.size(); i++) {
            listsObj = (JSONObject) listsArr.get(i);
            globalLists[i] = (String) listObj.get("listName");
        }
    }

    /** getStageVariableUsageCount: int
     *
     * (Sub-accessor)
     * Get global variable usage count
     *    from stage scripts.
     *
     *  @param String var - the variable being counted
     *  @return int count - number of times the variable is used in Stage
     */
    public int getStageVariableUsageCount(String var) {

        int count = 0;
        JSONArray stageJSON =
            FileUtils.getJSONArrayAttribute(jsonObj, "scripts");
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
     * (Sub-accessor)
     * Get global list usage count.
     *
     * @param String list - the list being counted
     * @return int count - number of times the list is used in Stage
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

    /** getCountForStage: int
     *
     * Helper method for all CountForStage methods.
     * Pass in JSON attribute name.
     * Get count of specified attribute for stage.
     *
     * @param String attribute - name of JSON attribute being counted
     * @return int items - count of attribute
     */
    public int getCountForStage(String attribute) {

        JSONArray items =
            FileUtils.getJSONArrayAttribute(sb2, attribute);

        return (int) items.size();
    }
}
