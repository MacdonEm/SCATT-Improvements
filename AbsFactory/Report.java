
/** Report.java
 *
 * Basis for a report file.
 * Report files should be stored in the analysedReports directory.
 *
 * @author Emily Macdonald
 * @version Spring 2019
 */
public interface Report {

    // beginReport should at least set up the file being written to
    public abstract void beginReport();

    // finishReport should at least close the file and state the report
    // was made
    public abstract void finishReport();
}
