import java.io.File;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/** Submission.java
 *
 * Class for a Scratch submission.
 * Invokes ProjectAnalysis.
 *
 * @author Emily Macdonald
 * @version Spr 2019
 */
public class Submission {

    File sbp;                    // Scratch project
    File zipsDir;                // directory for zipped files
    File unzipsDir;              // directory for unzipped files
    File json;                   // project.JSON file
    JSONObject sbpObj;           // project.JSON object for parsing
    ProjectAnalyzer analysis;    // Scratch project analysis

    /*
     * Submission constructor
     */
    public Submission(File sbp) {

        this.sbp = sbp;
        zipsDir = new File("zips");
        unzipsDir = new File("unzips");
        convertToZip();
        unZip();
        parseJSONFile();
        evalSubmission();
    }

    /** evalSubmission: void
     *
     * Choose which version to evaluate and do so.
     *
     * @param none
     * @return none
     */
    private void evalSubmission() {

        int v = FileUtils.getScratchVersion(jsonObj);

        switch (v) {
            case 2 :
                analysis = new Ver2ProjA(sbpObj);
                analysis.analyzeProject();
                break;

            case 3 :
                analysis = new Ver3ProjA(sbpObj);
                analysis.analyzeProject();
                break;

            default:
                System.out.println("Error evalSubmission: " +
                                   "Scratch project version not recognized");
                break;
        }
    }

//------------------------------------------------------------------------------
//                                 Helpers
//------------------------------------------------------------------------------

    /** isValid: Boolean
     *
     * Check if Scratch project is valid.
     *
     * @return true  - valid project found
     *         false - no valid project found
     */
    public boolean isValid() {

        String sbpName = sbp.getName();
        int len = sbpName.length();
        String ext = sbpName.substring(len - 4);
        return (ext.equals(".sb2") || ext.equals(".sb3")) && sbp.isFile();
    }

    /** convertToZip: void
     *
     * Converts valid Scratch project to .zip.
     *
     * @param none
     * @return none
     */
    private void convertToZip() {

        if (isValid())
            FileUtils.convertToZip(zipsDir, sbp);
    }

    /** unZip: void
     *
     * Unzips valid Scratch project file.
     *
     * @param none
     * @return none
     */
    private void unZip() {

        if (isValid()) {
            FileUtils.unZip(zipsDir, unzipsDir, sbp);
            String zipDir = FileUtils.getBaseName(sbp);
            json = new File(unzipsDir + File.separator + zipDir
                + File.separator, "project.json");
        }
    }

    /** parseJSONFile: void
     *
     * Parse JSON file.
     *
     * @param none
     * @return none
     */
    private void parseJSONFile() {

        if (json != null)
            sbpObj = FileUtils.parseJSONFile(json.getAbsolutePath());
    }

//------------------------------------------------------------------------------
//                                 Accessors
//------------------------------------------------------------------------------

    /** getName: String
     *
     * Get filename of submission.
     *
     * @param none
     * @return filename - name of Scratch project file
     */
    public String getName() {

        String n = sbp.getName();
        return n.substring(0, (n.length()-4));
    }

    /** getJSONObject: JSONObject
     *
     * Get JSON object of submission.
     *
     * @param none
     * @return jsonObj - JSON object
     */
    public JSONObject getJSONObject() { return jsonObj; }

    /** getAnalysis: ProjectAnalysis
     *
     * Get analysis.
     *
     * @param none
     * @return jsonObj - JSON object
     */
    public ProjectAnalysis getAnalysis() { return analysis; }
}
