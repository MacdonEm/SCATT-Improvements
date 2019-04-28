import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/** FileUtils.java
 *
 * Class for file utilities.
 *
 * @author Michelle Melton, Kara Beason, Cydney Caldwell (Spr 2017)
 * @author Emily Macdonald
 * @version Spr 2019
 */
public class FileUtils {

    /** convertToZip: void
     *
     * Copy file to zips directory to preserve original.
     * Convert file to .zip.
     *
     * @param zipsDir - directory of file
     *        file    - file being converted to zip
     * @return none
     */
    public static void convertToZip(File zipsDir, File file) {

        if (!zipsDir.exists())
            zipsDir.mkdir();

        String zipName = getBaseName(file) + ".zip";
        File zip = new File(zipsDir, zipName);

        if (!zip.exists()) {
            try {
                Files.copy(file.toPath(), zip.toPath());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** unZip: void
     *
     * Make zip named directory in unzips directory.
     * Copy zip into .zip named directory.
     * Unzip files.
     * Delete copied zip.
     *
     * @param zipsDir   - directory to hold zipped files
     *        unzipsDir - directory to hold unzipped files
     *        sbp       - Scratch project
     * @return none
     */
    public static void unZip(File zipsDir, File unzipsDir, File sbp) {

        if (!unzipsDir.exists())
            unzipsDir.mkdir();

        String zipDirName = getBaseName(sbp);
        String zipName = zipDirName + ".zip";
        File unzipDir = new File(unzipsDir, zipDirName);
        unzipDir.mkdir();

        File zip = new File(zipsDir, zipName);
        File copy = new File(unzipDir, zipName);

        try {
            Files.copy(zip.toPath(), copy.toPath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        unZipUtil(copy, unzipDir);
        copy.delete();
    }

    /** unZipUtil: void
     *
     * Unzip utility.
     * Adapted from Pankaj
     * http://www.journaldev.com/960/java-unzip-file-example
     *
     * @param zip     - zip file
     *        destDir - destination directory for files
     * @return none
     */
    public static void unZipUtil(File zip, File destDir)
    {
        FileInputStream fis;

        // Buffer for read and write data to file.
        byte[] buffer = new byte[1024];

        try {
            fis = new FileInputStream(zip);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;

                while ((len = zis.read(buffer)) > 0)
                    fos.write(buffer, 0, len);

                fos.close();
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            fis.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** getBaseName: String
     *
     * Get filename without extension.
     *
     * @param file - file being identified
     * @return name - base filename
     */
    public static String getBaseName(File file) {

        if (!file.exists())
            return "";

        String fileName = file.getName();
        int len = fileName.length();
        return fileName.substring(0, len - 4);
    }

    /** readValidDirectory: Boolean
     *
     * Check to see if folder is valid.
     *
     * @param dir - directory file object
     * @return true  - directory found
     *         false - no directory found
     */
    public static Boolean readValidDirectory(File dir) {

        if (dir.exists() && dir.isDirectory()) {
            String[] files = dir.list();

            if (files.length > 0)
                return true;
        }
        return false;
    }

//------------------------------------------------------------------------------
//                            JSON Parsing Tools
//------------------------------------------------------------------------------

    /** parseJSONFile: jsonObj
     *
     * Read JSON file.
     *
     * @param filePath - path to JSON file
     * @return jsonObj - JSON object
     */
    public static JSONObject parseJSONFile(String filePath) {

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = new JSONObject();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            jsonObj = (JSONObject) obj;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonObj;
    }

    /** getJSONObject: JSONObject
     *
     * Get JSON object by name.
     *
     * @param obj  - JSON object
     *        name - attribute's name
     * @return jsonObj - JSON object
     */
    public static JSONObject getJSONObject(JSONObject obj, String name) {

        JSONObject jsonObj = new JSONObject();
        if (obj != null && obj.get(name) != null)
            jsonObj = (JSONObject) obj.get(name);
        return jsonObj;
    }

    /** getJSONArrayAttribute: JSONArray
     *
     * Get JSONArray attribute by name.
     *
     * @param obj  - JSON Object
     *        name - name of Array
     * @return jsonArr - JSONArray
     */
    public static JSONArray getJSONArrayAttribute(JSONObject obj, String name) {

        JSONArray jsonArr = new JSONArray();
        if (obj != null && obj.get(name) != null)
            jsonArr = (JSONArray) obj.get(name);
        return jsonArr;
    }

    /** getJSONAttribute: String
     *
     * Get JSON object attribute's by name.
     *
     * @param obj  - JSON Object
     *        name - attribute's name
     * @return attribute - String value of attribute
     */
    public static String getJSONAttribute(JSONObject obj, String name) {

        String attribute = "";
        if (obj != null && obj.get(name) != null)
            attribute = (String) obj.get(name);
        return attribute;
    }

    /** getJSONLongAttribute: long
     *
     * Get JSON object attribute's by name.
     *
     * @param obj  - JSON Object
     *        name - attribute's name
     * @return value - long value of attribute
     */
    public static long getJSONLongAttribute(JSONObject obj, String name) {

        long value = 0;
        if (obj != null && obj.get(name) != null)
            value = (long) obj.get(name);
        return value;
    }

    /** getScratchVersion: int
     *
     * Check version of Scratch project file.
     *
     * @param obj - Parsed JSON file
     * @return int - Scratch project version
     */
    public static int getScratchVersion(JSONObject obj) {

        JSONObject o = new JSONObject();
        String val = "";

	    // v2.0 Offline Editor
        o = getJSONObject(obj, "info");
        val = getJSONAttribute(o, "userAgent");
        if (val.equals("Scratch 2.0 Offline Editor"))
            return 2;

	    // v3.0.0
        o = getJSONObject(obj, "meta");
        val = getJSONAttribute(o, "semver");
        if (val.equals("3.0.0"))
            return 3;

        return -1;
    }
}
