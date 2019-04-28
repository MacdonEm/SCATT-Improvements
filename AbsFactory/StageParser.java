
/** StageParser.java
 *
 * Basis for a stage.
 * Stages should parse the Scratch Project's stage
 * appropriately for the version, noting the...
 *   -> stage name
 *   -> stage variables (or global variables)
 *   -> stage lists (or global lists)
 *   -> object counts included in a stage
 *   -> block category counts included in a stage
 *
 * @author Emily Macdonald
 * @version Spring 2019
 */
public interface StageParser {

    // populate should create the stage to the above specifications
    public abstract void populate();
}
