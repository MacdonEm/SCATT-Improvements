import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.HashMap;
import java.util.ArrayList;

public class Ver2ProjA extends ProjectAnalyzer {

    HashMap<String, String> categoryMap;
    ArrayList<Ver2StagePars> stageTargets;
    ArrayList<Ver2SpritePars> spriteTargets;
    JSONObject jsonObj;
    Ver2StagePars stage;
    Ver2CatMap map;

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
    int moreBlocksBTotals;    // Total more block blocks
    int penBTotals;           // Total pen blocks

    /*
     * Run operations for v2.0 Offline Editor.
     */
    public Ver2ProjA(JSONObject sb2, String projectName) {

        super(sb2, projectName);

        categoryMap = new HashMap<String, String>();
        map = new Ver2CatMap(categoryMap);
        stageTargets = new ArrayList<Ver2StagePars>();
        spriteTargets = new ArrayList<Ver2SpritePars>();
        report = new TextReport(projectName);
        stage = new Ver2StagePars(sbp, categoryMap);

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
        moreBlocksBTotals = 0;
        penBTotals = 0;
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

        createMap();
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

        createStage();
        createSprite();
        createTotals();
    }

//------------------------------------------------------------------------------
//                                 Helpers
//------------------------------------------------------------------------------

    /** createMap: void
     *
     * Create block map.
     *
     * @param none
     * @return none
     */
    protected void createMap() {

        map.populate();
    }

    /** createStage: void
     *
     * Create assesed stage.
     *
     * @param none
     * @return none
     */
    protected void createStage() {

        stage = new Ver2StagePars(sbp, categoryMap);
        stage.populate();
        stageTargets.add(stage);
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
    protected void createSprite() {

        JSONArray children = FileUtils.getJSONArrayAttribute(sbp, "children");
        Ver2SpritePars sprite;

        for (int i = 0; i < children.size(); i++) {
            if (FileUtils.getJSONAttribute((JSONObject) children.get(i), "objName") != "") {
                sprite = new Ver2SpritePars((JSONObject) children.get(i), categoryMap);
                sprite.populate();
                spriteTargets.add(sprite);
            }
        }
    }

    /** createTotals: void
     *
     * Create assesed stage.
     *
     * @param none
     * @return none
     */
    protected void createTotals() {

        stage = stageTargets.get(0);
        scriptTotals = getScriptCount();
        variableTotals = getVariableCountForProgram();
        listTotals = getListCountForProgram();
        commentTotals = getScriptCommentCountForProgram();
        soundTotals = getSoundCountForProgram();
        costumeTotals = getCostumeCountForProgram();
        controlBTotals = getControlBlocksForProgram();
        dataBTotals = getDataBlocksForProgram();
        eventBTotals = getEventsBlocksForProgram();
        lookBTotals = getLooksBlocksForProgram();
        motionBTotals = getMotionBlocksForProgram();
        operatorBTotals = getOperatorsBlocksForProgram();
        sensingBTotals = getSensingBlocksForProgram();
        soundBTotals = getSoundBlocksForProgram();
        moreBlocksBTotals = getMoreBlocksBlocksForProgram();
        penBTotals = getPenBlocksForProgram();
    }

    /** getCategory: String
     *
     * Get category name for specified script name.
     *
     * @param scriptName - name being sought
     * @return category found, null if no mapping
     */
    public String getCategory(String scriptName) {

        return (String) categoryMap.get(scriptName);
    }

//------------------------------------------------------------------------------
//                                  Reports
//------------------------------------------------------------------------------

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
            report.printTotalCounts("sprite", (getSpriteCount()));         // Sprite count
            report.printTotalCounts("script", scriptTotals);               // Script count
            report.printTotalCounts("variable", variableTotals);           // Variable count
            report.printTotalCounts("list", listTotals);                   // List count
            report.printTotalCounts("sound", soundTotals);                 // Sound count
            report.printTotalCounts("comment", commentTotals);             // Comment count
            report.printTotalCounts("costume", costumeTotals);             // Costume count
            report.printTotalCounts("controlB", controlBTotals);           // Control block count
            report.printTotalCounts("dataB", dataBTotals);                 // Data block count
            report.printTotalCounts("eventB", eventBTotals);               // Event block count
            report.printTotalCounts("lookB", lookBTotals);                 // Look block count
            report.printTotalCounts("motionB", motionBTotals);             // Motion block count
            report.printTotalCounts("operatorB", operatorBTotals);         // Operator block count
            report.printTotalCounts("sensingB", sensingBTotals);           // Sensing block count
            report.printTotalCounts("soundB", soundBTotals);               // Sound block count
            report.printTotalCounts("moreBlockB", moreBlocksBTotals);      // MyBlock block count
            report.printTotalCounts("penB", penBTotals);                   // MyBlock block count

        /* Stages */
        report.stageHeader();
        for (int i = 0; i < stageTargets.size(); i++) {
            report.printStageCounts("sprite", spriteTargets.size());                                        // Sprite count
            report.printStageCounts("script", stageTargets.get(i).getScriptCountForStage());                            // Script count
            report.printStageCounts("variable", stageTargets.get(i).getVariableCountForStage());                         // Variable count
            report.printStageCounts("list", stageTargets.get(i).getListCountForStage());                             // List count
            report.printStageCounts("sound", stageTargets.get(i).getSoundCountForStage());                              // Sound count
            report.printStageCounts("comment", stageTargets.get(i).getScriptCommentCountForStage());                          // Comment count
            report.printStageCounts("costume", stageTargets.get(i).getCostumeCountForStage());                          // Costume count
            report.printStageCounts("controlB", stageTargets.get(i).getControlBlocksForStage());                     // Control block count
            report.printStageCounts("dataB", stageTargets.get(i).getDataBlocksForStage());                           // Data block count
            report.printStageCounts("eventB", stageTargets.get(i).getEventsBlocksForStage());                         // Event block count
            report.printStageCounts("lookB", stageTargets.get(i).getLooksBlocksForStage());                           // Look block count
            report.printStageCounts("motionB", stageTargets.get(i).getMotionBlocksForStage());                       // Motion block count
            report.printStageCounts("operatorB", stageTargets.get(i).getOperatorsBlocksForStage());                   // Operator block count
            report.printStageCounts("sensingB", stageTargets.get(i).getSensingBlocksForStage());                     // Sensing block count
            report.printStageCounts("soundB", stageTargets.get(i).getSoundBlocksForStage());                         // Sound block count
            report.printStageCounts("moreBlockB", stageTargets.get(i).getMoreBlocksBlocksForStage());       // MyBlock block count
            report.printStageCounts("penB", stageTargets.get(i).getPenBlocksForStage());                    // Pen block count
        }

        /* Sprites */
        report.spriteHeader();
        for (int i = 0; i < spriteTargets.size(); i++) {
            report.printSpriteCounts("sprite", 0, spriteTargets.get(i).getName());                            // Sprite name
            report.printSpriteCounts("script", spriteTargets.get(i).getScriptCount(), "");                        // Script count
            report.printSpriteCounts("variable", spriteTargets.get(i).getVariableCount(), "");                     // Variable count
            report.printSpriteCounts("list", spriteTargets.get(i).getListCount(), "");                         // List count
            report.printSpriteCounts("sound", spriteTargets.get(i).getSoundCount(), "");                          // Sound count
            report.printSpriteCounts("comment", spriteTargets.get(i).getScriptCommentCount(), "");                      // Comment count
            report.printSpriteCounts("costume", spriteTargets.get(i).getCostumeCount(), "");                      // Costume count
            report.printSpriteCounts("controlB", spriteTargets.get(i).getControlBlocksForSprite(), "");                 // Control block count
            report.printSpriteCounts("dataB", spriteTargets.get(i).getDataBlocksForSprite(), "");                       // Data block count
            report.printSpriteCounts("eventB", spriteTargets.get(i).getEventsBlocksForSprite(), "");                     // Event block count
            report.printSpriteCounts("lookB", spriteTargets.get(i).getLooksBlocksForSprite(), "");                       // Look block count
            report.printSpriteCounts("motionB", spriteTargets.get(i).getMotionBlocksForSprite(), "");                   // Motion block count
            report.printSpriteCounts("operatorB", spriteTargets.get(i).getOperatorsBlocksForSprite(), "");               // Operator block count
            report.printSpriteCounts("sensingB", spriteTargets.get(i).getSensingBlocksForSprite(), "");                 // Sensing block count
            report.printSpriteCounts("soundB", spriteTargets.get(i).getSoundBlocksForSprite(), "");                     // Sound block count
            report.printSpriteCounts("moreBlockB", spriteTargets.get(i).getMoreBlocksBlocksForSprite(), "");       // MyBlock block count
            report.printSpriteCounts("penB", spriteTargets.get(i).getPenBlocksForSprite(), "");                    // Pen block count
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

//------------------------------------------------------------------------------
//                                Total Counts
//------------------------------------------------------------------------------

    /** getScriptCount: int
     *
     * Get script count.
     *
     * @param none
     * @return scriptCount - count of total scripts
     */
    private int getScriptCount() {

/*        JSONObject obj = FileUtils.getJSONObject(jsonObj, "info");
        return (int) FileUtils.getJSONLongAttribute(obj, "scriptCount"); */
        int count = 0;

        for (int i = 0; i < spriteTargets.size(); i++)
            count += spriteTargets.get(i).getScriptCount();

        return count + stage.getScriptCountForStage();
    }

    /** getSpriteCount: int
     *
     * Get sprite count.
     *
     * @param none
     * @return spriteCount - total spriteCount
     */
    private int getSpriteCount() {

/*        JSONObject obj = FileUtils.getJSONObject(jsonObj, "info");
        return (int) FileUtils.getJSONLongAttribute(obj, "spriteCount"); */
        return spriteTargets.size();
    }

    /** getVariableCountForProgram: int
     *
     * Get variable count for program.
     *
     * @param none
     * @return count - total variable count
     */
    private int getVariableCountForProgram() {

        int count = 0;

        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getVariableCount();

        return count + stage.getVariableCountForStage();
    }

    /** getListCountForProgram: int
     *
     * Get list count for program.
     *
     * @param none
     * @return count - total list count
     */
    private int getListCountForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getListCount();

        return count + stage.getListCountForStage();
    }

    /** getScriptCommentCountForProgram: int
     *
     * Get script comment count for program.
     *
     * @param none
     * @return count - total script comment count
     */
    private int getScriptCommentCountForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getScriptCommentCount();

        return count + stage.getScriptCommentCountForStage();
    }

    /** getSoundCountForProgram: int
     *
     * Get sound count for program.
     *
     * @param none
     * @return count - total sound count
     */
    private int getSoundCountForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getSoundCount();

        return count + stage.getSoundCountForStage();
    }

    /** getCostumeCountForProgram: int
     *
     * Get costume count for program.
     *
     * @param none
     * @return count - total costume count
     */
    private int getCostumeCountForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getCostumeCount();

        return count + stage.getCostumeCountForStage();
    }

    /** getControlBlocksForProgram: int
     *
     * Get control block count for program.
     *
     * @param none
     * @return count - total control block count
     */
    private int getControlBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getControlBlocksForSprite();

        return count + stage.getControlBlocksForStage();
    }

    /** getDataBlocksForProgram: int
     *
     * Get data block count for program.
     *
     * @param none
     * @return count - total data block count
     */
    private int getDataBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getDataBlocksForSprite();

        return count + stage.getDataBlocksForStage();
    }

    /** getEventsBlocksForProgram: int
     *
     * Get events block count for program.
     *
     * @param none
     * @return count - total event block count
     */
    private int getEventsBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getEventsBlocksForSprite();

        return count + stage.getEventsBlocksForStage();
    }

    /** getLooksBlocksForProgram: int
     *
     * Get looks block count for program.
     *
     * @param none
     * @return count - total look block count
     */
    private int getLooksBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getLooksBlocksForSprite();

        return count + stage.getLooksBlocksForStage();
    }

    /** getMoreBlocksBlocksForProgram: int
     *
     * Get more blocks block count for program.
     *
     * @param none
     * @return count - total more blocks block count
     */
    private int getMoreBlocksBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getMoreBlocksBlocksForSprite();

        return count + stage.getMoreBlocksBlocksForStage();
    }

    /** getMotionBlocksForProgram: int
     *
     * Get motion block count for program.
     *
     * @param none
     * @return count - total motion block count
     */
    private int getMotionBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getMotionBlocksForSprite();

        return count + stage.getMotionBlocksForStage();
    }

    /** getOperatorsBlocksForProgram: int
     *
     * Get operators block count for program.
     *
     * @param none
     * @return count - total operator block count
     */
    private int getOperatorsBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getOperatorsBlocksForSprite();

        return count + stage.getOperatorsBlocksForStage();
    }

    /** getPenBlocksForProgram: int
     *
     * Get pen block count for program.
     *
     * @param none
     * @return count - total pen block count
     */
    private int getPenBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getPenBlocksForSprite();

        return count + stage.getPenBlocksForStage();
    }

    /** getSensingBlocksForProgram: int
     *
     * Get sensing block count for program.
     *
     * @param none
     * @return count - total sensing block count
     */
    private int getSensingBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getSensingBlocksForSprite();

        return count + stage.getSensingBlocksForStage();
    }

    /** getSoundBlocksForProgram: int
     *
     * Get sound block count for program.
     *
     * @param none
     * @return count - total sound block count
     */
    private int getSoundBlocksForProgram() {

        int count = 0;
        if (getSpriteCount() > 0)
            for (int i = 0; i < spriteTargets.size(); i++)
                count += spriteTargets.get(i).getSoundBlocksForSprite();

        return count + stage.getSoundBlocksForStage();
    }

    /** getProgramVariableUsageCount: int
     *
     * Get total program variable usage counts.
     *
     * @param var - the variable to be counted
     * @return number of times variable used total
     */
    public int getProgramVariableUsageCount(String var) {

        int count = stage.getStageVariableUsageCount(var);
        if (spriteTargets == null)
            return count;

        for (int i = 0; i < spriteTargets.size(); i++)
            count += spriteTargets.get(i).getVariableUsageCount(var);

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

        int count = stage.getStageListUsageCount(list);
        if (spriteTargets == null)
            return count;

        for (int i = 0; i < spriteTargets.size(); i++)
            count += spriteTargets.get(i).getListUsageCount(list);

        return count;
    }
}
