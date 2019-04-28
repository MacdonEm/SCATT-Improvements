import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.HashMap;

public class Ver2ProjA extends ProjectAnalyzer {

// PUT THESE IN STAGE!!!!
    // Stage Attributes
    private int scriptCountForStage;
    private int variableCountForStage;
    private int listCountForStage;
    private int scriptCommentCountForStage;
    private int soundCountForStage;
    private int costumeCountForStage;

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

    /**
     * Run operations for v2.0 Offline Editor.
     */
    public Ver2ProjA(JSONObject sb2) {

        super(sb2);

        stage = new Ver2StagePars(sb2);
        map = new Ver2CatMap(categoryMap);

        // Stage Things
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

    public void analyzeProject() {

        createMap();
        createStage();
        createSprite();
    }

    /** createMap: void
     *
     * Create block map.
     *
     * @param none
     * @return none
     */
    private void createMap() {

        map.addControlCategoryMap();
        map.addDataCategoryMap();
        map.addEventsCategoryMap();
        map.addLooksCategoryMap();
        map.addMoreBlocksCategoryMap();
        map.addMotionCategoryMap();
        map.addOperatorsCategoryMap();
        map.addPenCategoryMap();
        map.addSensingCategoryMap();
        map.addSoundCategoryMap();
    }

    /** createStage: void
     *
     * Create assesed stage.
     *
     * @param none
     * @return none
     */
    private void createStage() {

        scriptCountForStage = stage.getCountForStage("scripts");
        variableCountForStage = stage.getCountForStage("variables");
        listCountForStage = stage.getCountForStage("lists");
        scriptCommentCountForStage = stage.getCountForStage("scriptComments");
        soundCountForStage = stage.getCountForStage("sounds");
        costumeCountForStage = stage.getCountForStage("costumes");

        stage.processScripts(
                    FileUtils.getJSONArrayAttribute(sb2, "scripts"));
        stage.populateGlobalVariables();
        stage.populateGlobalLists();
    }

    /** createSprites: void
     *
     * Populate sprites array via creating sprite objects.
     *
     * Unchecked warnings are suppressed because JSONArray does not
     *   allow for a type specification, and this is a private
     *   method only called from within this class.
     *
     * @param none
     * @return none
     */
    @SuppressWarnings("unchecked")
    private void createSprite() {

        if (getSpriteCount() > 0) {
            JSONArray children =
                FileUtils.getJSONArrayAttribute(jsonObj, "children");

            sprites = new Ver2SpritePars[getSpriteCount()];

            for (int i = 0; i < children.size(); i++)
                if (FileUtils.getJSONAttribute(
                                (JSONObject) children.get(i), "objName") != "")
                    sprites[i] = new Sprite((JSONObject) children.get(i));
        }
    }

    //-----------------------------------------------------------
    //                          Helpers
    //-----------------------------------------------------------

    /** getCategory: String
     *
     * Get category name for specified script name.
     *
     * @param scriptName - name being sought
     * @return category found, null if no mapping
     */
    protected static String getCategory(String scriptName) {

        return (String) categoryMap.get(scriptName);
    }

    //-----------------------------------------------------------
    //                     Field accessors
    //-----------------------------------------------------------

    /** getSprites: SpriteParser[]
     *
     * Get sprites.
     *
     * @param none
     * @return sprites
     */
    public SpriteParser[] getSprites() { return sprites; }

    /** getSprites: StageParser
     *
     * Get stage.
     *
     * @param none
     * @return stage
     */
    public StageParser getStage() { return stage; }

    /** getScriptCountForStage: int
     *
     * Get script count for stage.
     *
     * @param none
     * @return int getScriptCountForStage - number of total scripts
     */
    public int getScriptCountForStage() { return scriptCountForStage; }

    /** getVariableCountForStage: int
     *
     * Get variable count for stage.
     *
     * @param none
     * @return int variableCountForStage - number of total variables
     */
    public int getVariableCountForStage() { return variableCountForStage; }

    /** getListCountForStage: int
     *
     * Get list count for stage.
     *
     * @param none
     * @return int listCountForStage - number of total lists
     */
    public int getListCountForStage() { return listCountForStage; }

    /** getScriptCommentCountForStage: int
     *
     * Get script comment count for stage.
     *
     * @param none
     * @return int scriptCommentCountForStage - number of total script comments
     */
    public int getScriptCommentCountForStage() { return scriptCommentCountForStage; }

    /** getSoundCountForStage: int
     *
     * Get sound count for stage.
     *
     * @param none
     * @return int soundCountForStage - number of total sounds
     */
    public int getSoundCountForStage() { return soundCountForStage; }

    /** getCostumeCountForStage: int
     *
     * Get costume count for stage.
     *
     * @param none
     * @return int costumeCountForStage - number of total "costumes" (stage backdrops)
     */
    public int getCostumeCountForStage() { return costumeCountForStage; }

    /** getControlBlocksForStage: int
     *
     * Get control block count.
     *
     * @param none
     * @return int controlBlocksForStage - number of control blocks found
     */
    public int getControlBlocksForStage() { return controlBlocksForStage; }

    /** getDataBlocksForStage: int
     *
     * Get data block count.
     *
     * @param none
     * @return int dataBlocksForStage - number of data blocks found
     */
    public int getDataBlocksForStage() { return dataBlocksForStage; }

    /** getEventsBlocksForStage: int
     *
     * Get events block count.
     *
     * @param none
     * @return int eventsBlocksForStage - number of event blocks found
     */
    public int getEventsBlocksForStage() { return eventsBlocksForStage; }

    /** getLooksBlocksForStage: int
     *
     * Get looks block count.
     *
     * @param none
     * @return int looksBlocksForStage - number of looks blocks found
     */
    public int getLooksBlocksForStage() { return looksBlocksForStage; }

    /** getMoreBlocksBlocksForStage: int
     *
     * Get more blocks block count.
     *
     * @param none
     * @return int moreBlocksBlocksForStage - number of "more blocks" blocks found
     */
    public int getMoreBlocksBlocksForStage() {return moreBlocksBlocksForStage;}

    /** getMotionBlocksForStage: int
     *
     * Get motion block count.
     * Should always be 0 - motion blocks are not available for stage.
     *
     * @param none
     * @return int motionBlocksForStage - number of motion blocks found
     */
    public int getMotionBlocksForStage() { return motionBlocksForStage; }

    /** getOperatorsBlocksForStage: int
     *
     * Get operators block count.
     *
     * @param none
     * @return int operatorsBlocksForStage - number of operator blocks found
     */
    public int getOperatorsBlocksForStage() { return operatorsBlocksForStage; }

    /** getPenBlocksForStage: int
     *
     * Get pen block count.
     *
     * @param none
     * @return int penBlocksForStage - number of pen blocks found
     */
    public int getPenBlocksForStage() { return penBlocksForStage; }

    /** getSensingBlocksForStage: int
     *
     * Get sensing block count.
     *
     * @param none
     * @return int sensingBlocksForStage - number of sensing blocks found
     */
    public int getSensingBlocksForStage() { return sensingBlocksForStage; }

    /** getSoundBlocksForStage: int
     *
     * Get sound block count.
     *
     * @param none
     * @return int soundBlocksForStage - number of sound blocks found
     */
    public int getSoundBlocksForStage() { return soundBlocksForStage; }

    /** getGlobalVariables: String[]
     *
     * Get the variables array.
     *
     * @param none
     * @return String[] globalVariables - array of variables found
     */
    public String[] getGlobalVariables() { return globalVariables; }

    /** getGlobalLists: String[]
     *
     * Get the list array.
     *
     * @param none
     * @return String[] globalLists - array of lists found
     */
    public String[] getGlobalLists() { return globalLists; }

    //-----------------------------------------------------------
    //                Total Counts accessors found here
    //-----------------------------------------------------------

    /**
    * Get script count.
    *
    * @return scriptCount
    */
    public int getScriptCount() {

        JSONObject obj = FileUtils.getJSONObject(jsonObj, "info");

        return (int) FileUtils.getJSONLongAttribute(obj, "scriptCount");
    }

    /**
    * Get sprite count.
    *
    * @return spriteCount
    */
    public int getSpriteCount() {

        JSONObject obj = FileUtils.getJSONObject(jsonObj, "info");

        return (int) FileUtils.getJSONLongAttribute(obj, "spriteCount");
    }

    /**
    * Get variable count for program.
    *
    * @return count
    */
    public int getVariableCountForProgram() {

        int count = 0;

        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getVariableCount();

        return count + stage.getVariableCountForStage();
    }

    /**
    * Get list count for program.
    *
    * @return count
    */
    public int getListCountForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getListCount();

        return count + stage.getListCountForStage();
    }

    /**
    * Get script comment count for program.
    *
    * @return count
    */
    public int getScriptCommentCountForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getScriptCommentCount();

        return count + stage.getScriptCommentCountForStage();
    }

    /**
    * Get sound count for program.
    *
    * @return count
    */
    public int getSoundCountForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getSoundCount();

        return count + getSoundCountForStage();
    }

    /**
    * Get costume count for program.
    *
    * @return count
    */
    public int getCostumeCountForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getCostumeCount();

        return count + getCostumeCountForStage();
    }

    /**
    * Get control block count for program.
    *
    * @return count
    */
    public int getControlBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getControlBlocksForSprite();

        return count + controlBlocksForStage;
    }

    /**
    * Get data block count for program.
    *
    * @return count
    */
    public int getDataBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getDataBlocksForSprite();

        return count + dataBlocksForStage;
    }

    /**
    * Get events block count for program.
    *
    * @return count
    */
    public int getEventsBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getEventsBlocksForSprite();

        return count + eventsBlocksForStage;
    }

    /**
    * Get looks block count for program.
    *
    * @return count
    */
    public int getLooksBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getLooksBlocksForSprite();

        return count + looksBlocksForStage;
    }

    /**
    * Get more blocks block count for program.
    *
    * @return count
    */
    public int getMoreBlocksBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getMoreBlocksBlocksForSprite();

        return count + moreBlocksBlocksForStage;
    }

    /**
    * Get motion block count for program.
    *
    * @return count
    */
    public int getMotionBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getMotionBlocksForSprite();

        return count + motionBlocksForStage;
    }

    /**
    * Get operators block count for program.
    *
    * @return count
    */
    public int getOperatorsBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getOperatorsBlocksForSprite();

        return count + operatorsBlocksForStage;
    }

    /**
    * Get pen block count for program.
    *
    * @return count
    */
    public int getPenBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getPenBlocksForSprite();

        return count + penBlocksForStage;
    }

    /**
    * Get sensing block count for program.
    *
    * @return count
    */
    public int getSensingBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getSensingBlocksForSprite();

        return count + sensingBlocksForStage;
    }

    /**
    * Get sound block count for program.
    *
    * @param none
    * @return count
    */
    public int getSoundBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < sprites.length; i++)
                count += sprites[i].getSoundBlocksForSprite();

        return count + soundBlocksForStage;
    }

    /** getProgramVariableUsageCount: int
     *
     * Get total program variable usage counts.
     *
     * @param var - the variable to be counted
     * @return number of times variable used total
     */
    public int getProgramVariableUsageCount(String var) {

        int count = getStageVariableUsageCount(var);
        if (sprites == null)
            return count;

        for (int i = 0; i < sprites.length; i++)
            count += sprites[i].getVariableUsageCount(var);

        return count;
    }

    /** getProgramListUsageCount: int
     *
     * Get total program list usage count.
     *
     * @param list - the list to be counted
     * @return number of times list used in total
     */
    public int getProgramListUsageCount(String list) {

        int count = getStageListUsageCount(list);
        if (sprites == null)
            return count;

        for (int i = 0; i < sprites.length; i++)
            count += sprites[i].getListUsageCount(list);

        return count;
    }
}
