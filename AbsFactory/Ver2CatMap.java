import java.util.HashMap;

public class Ver2CatMap extends CategoryMap {

    private static HashMap<String, String> categoryMap;

    /** constructor
    *
    * @param JSONObject sb2 - json file of the submitted Scratch project
    */
    public Ver2CatMap(HashMap<String, String> categoryMap) {

        this.categoryMap = categoryMap;
    }

    /** populate: void
     *
     * Creates a category map entry for the given target.
     *
     * @param none
     * @return none
     */
    public void populate() {

        addControlCategoryMap();
        addDataCategoryMap();
        addEventsCategoryMap();
        addLooksCategoryMap();
        addMoreBlocksCategoryMap();
        addMotionCategoryMap();
        addOperatorsCategoryMap();
        addPenCategoryMap();
        addSensingCategoryMap();
        addSoundCategoryMap();
    }

    /** addControlCategoryMap: void
     *
     * HashMap to store script names with block category.
     * Version 2 Control scripts.
     *
     * @param none
     * @return none
     */
    private void addControlCategoryMap() {

        categoryMap.put("wait:elapsed:from:", "control");
        categoryMap.put("wait:elapsed:from:", "control");
        categoryMap.put("doRepeat", "control");
        categoryMap.put("doForever", "control");
        categoryMap.put("doIf", "control");
        categoryMap.put("doIfElse", "control");
        categoryMap.put("doWaitUntil", "control");
        categoryMap.put("doUntil", "control");
        categoryMap.put("stopScripts", "control");
        categoryMap.put("whenCloned", "control");
        categoryMap.put("createCloneOf", "control");
        categoryMap.put("deleteClone", "control");
    }

    /** addDataCategoryMap: void
     *
     * HashMap to store script names with block category.
     * Version 2 Data scripts.
     *
     * @param none
     * @return none
     */
    private void addDataCategoryMap() {

        categoryMap.put("readVariable", "data");
        categoryMap.put("setVar:to:", "data");
        categoryMap.put("changeVar:by:", "data");
        categoryMap.put("showVariable:", "data");
        categoryMap.put("hideVariable:", "data");
        categoryMap.put("contentsOfList:", "data");
        categoryMap.put("append:toList:", "data");
        categoryMap.put("deleteLine:ofList:", "data");
        categoryMap.put("insert:at:ofList:", "data");
        categoryMap.put("setLine:ofList:to:", "data");
        categoryMap.put("getLine:ofList:", "data");
        categoryMap.put("lineCountOfList:", "data");
        categoryMap.put("list:contains:", "data");
        categoryMap.put("showList:", "data");
        categoryMap.put("hideList:", "data");
    }

    /** addEventsCategoryMap: void
     *
     * HashMap to store script names with block category.
     * Version 2 Events scripts.
     *
     * @param none
     * @return none
     */
    private void addEventsCategoryMap() {

        categoryMap.put("whenGreenFlag", "events");
        categoryMap.put("whenKeyPressed", "events");
        categoryMap.put("whenClicked", "events");
        categoryMap.put("whenSceneStarts", "events");
        categoryMap.put("whenSensorGreaterThan", "events");
        categoryMap.put("whenIReceive", "events");
        categoryMap.put("broadcast:", "events");
        categoryMap.put("doBroadcastAndWait", "events");
    }

    /** addLooksCategoryMap
     *
     * HashMap to store script names with block category.
     * Version 2 Looks scripts.
     *
     * @param none
     * @return none
     */
    private void addLooksCategoryMap() {

        categoryMap.put("say:duration:elapsed:from:", "looks");
        categoryMap.put("say:", "looks");
        categoryMap.put("think:duration:elapsed:from:", "looks");
        categoryMap.put("think:", "looks");
        categoryMap.put("show", "looks");
        categoryMap.put("hide", "looks");
        categoryMap.put("lookLike:", "looks");
        categoryMap.put("nextCostume", "looks");
        categoryMap.put("startScene", "looks");
        categoryMap.put("changeGraphicEffect:by:", "looks");
        categoryMap.put("setGraphicEffect:to:", "looks");
        categoryMap.put("filterReset", "looks");
        categoryMap.put("changeSizeBy:", "looks");
        categoryMap.put("setSizeTo:", "looks");
        categoryMap.put("comeToFront", "looks");
        categoryMap.put("goBackByLayers:", "looks");
        categoryMap.put("costumeIndex", "looks");
        categoryMap.put("sceneName", "looks");
        categoryMap.put("scale", "looks");
    }

    /** addMoreBlocksCategoryMap: void
     *
     * HashMap to store script names with block category.
     * More blocks scripts.
     *
     * @param none
     * @return none
     */
    private void addMoreBlocksCategoryMap() {

        categoryMap.put("procDef", "more blocks");
        categoryMap.put("call", "more blocks");
        categoryMap.put("LEGO WeDo\u001fmotorOnFor", "more blocks");
        categoryMap.put("LEGO WeDo\u001fmotorOn", "more blocks");
        categoryMap.put("LEGO WeDo\u001fmotorOff", "more blocks");
        categoryMap.put("LEGO WeDo\u001fstartMotorPower", "more blocks");
        categoryMap.put("LEGO WeDo\u001fsetMotorDirection", "more blocks");
        categoryMap.put("LEGO WeDo\u001fwhenDistance", "more blocks");
        categoryMap.put("LEGO WeDo\u001fwhenTilt", "more blocks");
        categoryMap.put("LEGO WeDo\u001fgetDistance", "more blocks");
        categoryMap.put("LEGO WeDo\u001fgetTilt", "more blocks");
        categoryMap.put("LEGO WeDo 2.0\u001fmotorOnFor", "more blocks");
        categoryMap.put("LEGO WeDo 2.0\u001fmotorOn", "more blocks");
        categoryMap.put("LEGO WeDo 2.0\u001fmotorOff", "more blocks");
        categoryMap.put("LEGO WeDo 2.0\u001fstartMotorPower", "more blocks");
        categoryMap.put("LEGO WeDo 2.0\u001fsetMotorDirection", "more blocks");
        categoryMap.put("LEGO WeDo 2.0\u001fsetLED", "more blocks");
        categoryMap.put("LEGO WeDo 2.0\u001fplayNote", "more blocks");
        categoryMap.put("LEGO WeDo 2.0\u001fwhenDistance", "more blocks");
        categoryMap.put("LEGO WeDo 2.0\u001fwhenTilted", "more blocks");
        categoryMap.put("LEGO WeDo 2.0\u001fgetDistance", "more blocks");
        categoryMap.put("LEGO WeDo 2.0\u001fisTilted", "more blocks");
        categoryMap.put("LEGO WeDo 2.0\u001fgetTilt", "more blocks");
        categoryMap.put("PicoBoard\u001fwhenSensorConnected", "more blocks");
        categoryMap.put("PicoBoard\u001fwhenSensorPass", "more blocks");
        categoryMap.put("PicoBoard\u001fsensorPressed", "more blocks");
        categoryMap.put("PicoBoard\u001fsensor", "more blocks");
    }

    /** addMotionCategoryMap: void
     *
     * HashMap to store script names with block category.
     * Version 2 Motion scripts.
     *
     * @param none
     * @return none
     */
    private void addMotionCategoryMap() {

        categoryMap.put("forward:", "motion");
        categoryMap.put("turnRight:", "motion");
        categoryMap.put("turnLeft:", "motion");
        categoryMap.put("heading:", "motion");
        categoryMap.put("pointTowards:", "motion");
        categoryMap.put("gotoX:y:", "motion");
        categoryMap.put("gotoSpriteOrMouse:", "motion");
        categoryMap.put("glideSecs:toX:y:elapsed:from:", "motion");
        categoryMap.put("changeXposBy:", "motion");
        categoryMap.put("xpos:", "motion");
        categoryMap.put("changeYposBy:", "motion");
        categoryMap.put("ypos:", "motion");
        categoryMap.put("bounceOffEdge", "motion");
        categoryMap.put("setRotationStyle", "motion");
    }

    /** addOperatorsCategoryMap: void
     *
     * HashMap to store script names with block category.
     * Version 2 Operators scripts.
     *
     * @param none
     * @return none
     */
    private void addOperatorsCategoryMap() {

        categoryMap.put("+", "operators");
        categoryMap.put("-", "operators");
        categoryMap.put("*", "operators");
        categoryMap.put("\\/", "operators");
        categoryMap.put("randomFrom:to:", "operators");
        categoryMap.put("<", "operators");
        categoryMap.put("=", "operators");
        categoryMap.put(">", "operators");
        categoryMap.put("&", "operators");
        categoryMap.put("|", "operators");
        categoryMap.put("not", "operators");
        categoryMap.put("concatenate:with:", "operators");
        categoryMap.put("letter:of:", "operators");
        categoryMap.put("stringLength:", "operators");
        categoryMap.put("%", "operators");
        categoryMap.put("rounded", "operators");
        categoryMap.put("computeFunction:of:", "operators");
    }

    /** addPenCategoryMap: void
     * HashMap to store script names with block category.
     * Pen scripts.
     *
     * @param none
     * @return none
     */
    private void addPenCategoryMap() {

        categoryMap.put("clearPenTrails", "pen");
        categoryMap.put("stampCostume", "pen");
        categoryMap.put("putPenDown", "pen");
        categoryMap.put("putPenUp", "pen");
        categoryMap.put("penColor:", "pen");
        categoryMap.put("changePenHueBy:", "pen");
        categoryMap.put("setPenHueTo:", "pen");
        categoryMap.put("changePenShadeBy:", "pen");
        categoryMap.put("setPenShadeTo:", "pen");
        categoryMap.put("changePenSizeBy:", "pen");
        categoryMap.put("penSize:", "pen");
    }

    /** addSensingCategoryMap: void
     *
     * HashMap to store script names with block category.
     * Version 2 Sensing scripts.
     *
     * @param none
     * @return none
     */
    private void addSensingCategoryMap() {

        categoryMap.put("touching:", "sensing");
        categoryMap.put("touchingColor:", "sensing");
        categoryMap.put("color:sees:", "sensing");
        categoryMap.put("distanceTo:", "sensing");
        categoryMap.put("doAsk", "sensing");
        categoryMap.put("answer", "sensing");
        categoryMap.put("keyPressed:", "sensing");
        categoryMap.put("mousePressed", "sensing");
        categoryMap.put("mouseX", "sensing");
        categoryMap.put("mouseY", "sensing");
        categoryMap.put("soundLevel", "sensing");
        categoryMap.put("senseVideoMotion", "sensing");
        categoryMap.put("setVideoState", "sensing");
        categoryMap.put("setVideoTransparency", "sensing");
        categoryMap.put("timer", "sensing");
        categoryMap.put("timerReset", "sensing");
        categoryMap.put("getAttribute:of:", "sensing");
        categoryMap.put("timeAndDate", "sensing");
        categoryMap.put("timestamp", "sensing");
        categoryMap.put("getUserName", "sensing");
    }

    /** addSoundCategoryMap
     *
     * HashMap to store script names with block category.
     * Version 2 Sound scripts.
     *
     * @param none
     * @return none
     */
    private void addSoundCategoryMap() {

        categoryMap.put("playSound:", "sound");
        categoryMap.put("doPlaySoundAndWait", "sound");
        categoryMap.put("stopAllSounds", "sound");
        categoryMap.put("playDrum", "sound");
        categoryMap.put("rest:elapsed:from:", "sound");
        categoryMap.put("noteOn:duration:elapsed:from:", "sound");
        categoryMap.put("instrument:", "sound");
        categoryMap.put("changeVolumeBy:", "sound");
        categoryMap.put("setVolumeTo:", "sound");
        categoryMap.put("volume", "sound");
        categoryMap.put("changeTempoBy:", "sound");
        categoryMap.put("setTempoTo:", "sound");
        categoryMap.put("tempo", "sound");
    }
}
