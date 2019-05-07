import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/** TextReport.java
 *
 * Generates report as text file.
 *
 * @author Emily Macdonald
 * @version Spr 2019
 */
public class TextReport extends Report {

    String projectName;    // Name of Scratch Project
    PrintWriter printW;    // Printwriter for saving to a file

    /*
     * Creates text report object
     */
    public TextReport(String projectName) { this.projectName = projectName; }

    /** beginReport: void
     *
     * Begin creating text file report.
     *
     * @param none
     * @return none
     */
    public void beginReport() {

	try {
        	File reportFile = new File("./analyzedReports/" + projectName + ".txt");
        	printW = new PrintWriter(reportFile);

        	printW.println();
        	printW.println("SCATT Report " + headerDateTime() + "\n");
        	printW.println("File: " + projectName);
        	printW.println("---------------------------------");
	}
	catch (FileNotFoundException e) {
    		e.printStackTrace();
    		System.out.println("Error, report not made");
	}
    }

    /** finishReport: void
     *
     * Complete report and close printwriter.
     *
     * @param none
     * @return none
     */
    public void finishReport() {

        printW.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        printW.println();

        printW.close();
        System.out.println("Report finished. Report located in current directory.");
    }

//------------------------------------------------------------------------------
//                                  Headers
//------------------------------------------------------------------------------

    /** totalHeader: void
     *
     * Print the total header
     *
     * @param none
     * @return none
     */
    public void totalHeader() {

        printW.println();
        printW.println("Total Counts");
        printW.println("---------------------------------");
    }

    /** stageHeader: void
     *
     * Print the stage header
     *
     * @param none
     * @return none
     */
    public void stageHeader() {

        printW.println();
        printW.println("Stage Counts");
        printW.println("---------------------------------");
    }

    /** spriteHeader: void
     *
     * Print the stage header
     *
     * @param none
     * @return none
     */
    public void spriteHeader() {

        printW.println();
        printW.println("Sprite Counts");
        printW.println("---------------------------------");
    }

//------------------------------------------------------------------------------
//                                  Counts
//------------------------------------------------------------------------------

    /** printTotalCounts: void
     *
     * Print out the total counts for a given submission.
     * Because apparently there's a reason for this specific formatting.
     *
     * @param countType - Type of item being passed
     *        count     - count of that item
     * @return none
     */
    public void printTotalCounts(String countType, int count) {

        switch (countType) {
            case "script" :
                printW.println("Scripts Total: \t\t\t" + count);
                break;
            case "sprite" :
                printW.println("Sprites Total: \t\t\t" + count);
                break;
            case "variable" :
                printW.println("Variables Total: \t\t" + count);
                // printVariableUsageForProgram(printW, i);
                break;
            case "list" :
                printW.println("Lists Total: \t\t\t" + count);
                // printListUsageForProgram(printW, i);
                break;
            case "sound" :
                printW.println("Sounds Total: \t\t\t" + count);
                break;
            case "comment" :
                printW.println("Comments Total: \t\t" + count);
                break;
            case "costume" :
                printW.println("Costumes Total: \t\t" + count);
                break;
            default:
                printBlockCounts(countType, count);
            }
    }

    /** printBlockCounts: void
     *
     * Print out the block category counts for a given submission.
     * Because apparently there's a reason for this specific formatting.
     *
     * @param blockType - type of block categpry being passed
     *        count     - count of that item
     * @return none
     */
    public void printBlockCounts(String blockType, int count) {

        switch (blockType) {
            case "controlB" :
                printW.println("Control Blocks: \t\t" + count);
                break;
            case "dataB" :
                printW.println("Data Blocks: \t\t\t" + count);
                break;
            case "eventB" :
                printW.println("Event Blocks: \t\t\t" + count);
                break;
            case "lookB" :
                printW.println("Looks Blocks: \t\t\t" + count);
                break;
            case "moreBlockB" :
                printW.println("More Blocks Blocks: \t" + count);
                break;
            case "motionB" :
                printW.println("Motion Blocks: \t\t\t" + count);
                break;
            case "operatorB" :
                printW.println("Operator Blocks: \t\t" + count);
                break;
            case "penB" :
                printW.println("Pen Blocks: \t\t\t" + count);
                break;
            case "sensingB" :
                printW.println("Sensing Blocks: \t\t" + count);
                break;
            case "soundB" :
                printW.println("Sound Blocks: \t\t\t" + count);
                break;
            case "myBlockB" :
                printW.println("My Block Blocks: \t\t" + count);
                break;
            }
    }

    /** printStageCount: void
     *
     * Print out the stage counts for a given submission.
     * Because apparently there's a reason for this specific formatting.
     *
     * @param countType - Type of item being passed
     *        count     - count of that item
     * @return none
     */
    public void printStageCounts(String countType, int count) {

        switch (countType) {
            case "script" :
                printW.println("Scripts: \t\t\t\t" + count);
                break;
            case "sprite" :
                printW.println("Sprites: \t\t\t\t" + count);
                break;
            case "variable" :
                printW.println("Variables: \t\t\t\t" + count);
                // printVariableUsageForStage(printW, i);
                break;
            case "list" :
                printW.println("Lists: \t\t\t\t\t" + count);
                // printListUsageForStage(printW, i);
                break;
            case "sound" :
                printW.println("Sounds: \t\t\t\t" + count);
                break;
            case "comment" :
                printW.println("Comments: \t\t\t\t" + count);
                break;
            case "costume" :
                printW.println("Costumes: \t\t\t\t" + count);
                break;
            default:
                printBlockCounts(countType, count);
            }
    }

    /** printSpriteCounts: void
     *
     * Print out the sprite counts for a given submission.
     * Because apparently there's a reason for this specific formatting.
     *
     * @param countType - Type of item being passed
     *        count     - count of that item
     *        name      - sprite name
     * @return none
     */
    public void printSpriteCounts(String countType, int count, String name) {

        switch (countType) {
            case "script" :
                printW.println("Scripts: \t\t\t\t" + count);
                break;
            case "sprite" :
                printW.println("Sprite: " + name);
                break;
            case "variable" :
                printW.println("Variables: \t\t\t\t" + count);
                //printVariableUsage(printW, i, sprites[j]);
                break;
            case "list" :
                printW.println("Lists: \t\t\t\t\t" + count);
                //printListUsage(printW, i, sprites[j]);
                break;
            case "sound" :
                printW.println("Sounds: \t\t\t\t" + count);
                break;
            case "comment" :
                printW.println("Comments: \t\t\t\t" + count);
                break;
            case "costume" :
                printW.println("Costumes: \t\t\t\t" + count);
                break;
            default:
                printBlockCounts(countType, count);
            }
    }

//------------------------------------------------------------------------------
//                                Helpers
//------------------------------------------------------------------------------


/*
    * Print total variable usage counts.
    *
    * @param printW - the printWriter to use
    * @param i - the submission to get count for
    *
    public void printVariableUsageForProgram(PrintWriter printW, int i) {

        String[] vars = submissions[i].getGlobalVariables();
        if (vars.length > 0)
        {
            printW.println("\tGlobal Variables:");
            for (int j = 0; j < vars.length; j++)
            {
                printW.println("\t\t" + vars[j]
                + " used "
                + submissions[i].getProgramVariableUsageCount(vars[j])
                + " time(s)");
            }
        }
    }


    * Print toal list usage counts.
    *
    * @param printW - the printWriter to use
    * @param i - the submission to get count for
    *
    public void printListUsageForProgram(PrintWriter printW, int i) {

        String [] lists = submissions[i].getGlobalLists();
        if (lists.length > 0)
        {
            printW.println("\tGlobal Lists:");
            for (int j = 0; j < lists.length; j++)
            {
                printW.println("\t\t" + lists[j]
                + " used "
                + submissions[i].getProgramListUsageCount(lists[j])
                + " time(s)");
            }
        }
    }



    * Print Variable name and usage count for Stage.
    *
    * @param printW - the printwriter to use
    * @param i - the submission to get count for
    *
    public void printVariableUsageForStage(PrintWriter printW, int i) {

        String[] vars = submissions[i].getGlobalVariables();
        if (vars.length > 0)
        {
            printW.println("\tGlobal Variables:");
            for (int j = 0; j < vars.length; j++)
            {
                printW.println("\t\t" + vars[j]
                + " used "
                + submissions[i].getStageVariableUsageCount(vars[j])
                + " time(s)");
            }
        }
    }


    * Print list name and usage count for Stage.
    *
    * @param printW - the printwriter to use
    * @param i - the submission to get count for
    *
    public void printListUsageForStage(PrintWriter printW, int i) {

        String[] lists = submissions[i].getGlobalLists();
        if (lists.length > 0)
        {
            printW.println("\tGlobal Lists:");
            for (int j = 0; j < lists.length; j++)
            {
                printW.println("\t\t" + lists[j]
                + " used "
                + submissions[i].getStageListUsageCount(lists[j])
                + " time(s)");
            }
        }
    }


    * Print Variable name and usage count for Sprite.
    *  This includes both global and local variables.
    *
    *  @param printW - the printWriter to use
    *  @param i - the submission to get count for
    *  @param sprite - the sprite to get count for
    *
    public void printVariableUsage(PrintWriter printW, int i, Sprite sprite) {

        String[] globalVars = submissions[i].getGlobalVariables();
        String[] vars = sprite.getVariables();
        if (globalVars.length > 0)
        {
            printW.println("\tGlobal Variables:");
            for (int k = 0; k < globalVars.length; k++)
            {
                printW.println("\t\t" + globalVars[k]
                + " used " + sprite.getVariableUsageCount(globalVars[k])
                + " time(s)");
            }
        }
        if (vars.length > 0)
        {
            printW.println("\tSprite Variables:");
            for (int j = 0; j < vars.length; j++)
            {
                printW.println("\t\t" + vars[j]
                + " used " + sprite.getVariableUsageCount(vars[j])
                + " time(s)");
            }
        }
    }


     *
     * Print List name and usage count for Sprite.
     *  This includes both global and local lists.
     *
     *  @param printW - the printWriter to use
     *  @param i - the submission to get count for
     *  @param sprite - the sprite to get count for
     *
    public void printListUsage(PrintWriter printW, int i, Sprite sprite) {

        String[] globalLists = submissions[i].getGlobalLists();
        String[] lists = sprite.getLists();
        if (globalLists.length > 0)
        {
            printW.println("\tGlobal Lists:");
            for (int k = 0; k < globalLists.length; k++)
            {
                printW.println("\t\t" + globalLists[k]
                + " used " + sprite.getListUsageCount(globalLists[k])
                + " time(s)");
            }
        }
        if (lists.length > 0)
        {
            printW.println("\tSprite Lists:");
            for (int j = 0; j < lists.length; j++)
            {
                printW.println("\t\t" + lists[j]
                + " used " + sprite.getListUsageCount(lists[j])
                + " time(s)");
            }
        }
    }
*/

    /** reportDateTime: String
     *
     * Creates the current datetime for report.
     *
     * @param none
     * @return datetime - formatted datetime
     */
    public String reportDateTime() {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /** headerDateTime: String
     *
     * Creates the current datetime for the report's header.
     *
     * @param none
     * @return datetime - formatted datetime
     */
    public String headerDateTime() {

        DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy h:mm:ss a");
        Date date = new Date();
        return dateFormat.format(date);
    }


    /** lineBreak: void
     *
     * Creates a line break.
     *
     * @param none
     * @return none
     */
    public void lineBreak() { printW.println(); }

}
