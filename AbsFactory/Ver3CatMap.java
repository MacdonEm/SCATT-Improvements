import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

/** Var3CatMap.java
 *
 * Defines a version 3 category map.
 *
 * @author Emily Macdonald
 * @version Spring 2019
 */
public class Var3CatMap implements CategoryMap {

    /*
     * Describes a Block node
     */
    class Block {
        String id;          // Object name produced by Scratch
        String category;    // Block category, first half of opcode
        String type;        // Block name, second half of opcode
        String parent;      // Parent block ID

        public Block(String i, String c, String t, String p) {
            id = i; category = c; type = t; parent = p; }
        public String getID() { return id; }
        public String getCategory() { return category; }
        public String getType() { return type; }
        public String getParent() { return parent; }
        public Boolean isChild(Block parentBlock) {
            if (parent.equals(parentBlock.getID()))
                return true;
            return false;
        }
    }

    // Array of blocks to sort
    JSONArray targetBlocks;

    // Map of Target to Scripts
    HashMap<String, HashMap<Integer, Block>> catMap;

    // Script count and target name (respectively)
    int scriptCount;
    String name;

    /*
     * Creates a version 3 category map object.
     */
    public Var3CatMap() {

        targetBlocks = null;
        scriptCount = 0;
        target = null;
        catMap = new HashMap<String, HashMap<Integer, Block>>();
    }

    /** populate: void
     *
     * Creates a category map entry for the given target.
     *
     * @param name - Name of target
     * @return none
     */
    public void populate() {

        for (int i = 0; i < targetBlocks.size(); i++)
            catMap.put(name, makeScript(targetBlovks.get(i)));
    }

    /** makeScript: void
     *
     * "Creates Script" by inserting blocks to a scriptMap.
     * If script is already exists, the block is inserted in the
     * correct location.
     *
     * @param jsonBlock - JSON for block to insert
     * @return scriptMap - HashMap of Scripts
     */
    private HashMap<Integer, Block> makeScript(JSONObject jsonBlock) {

        HashMap<Integer, Block> scriptMap = new HashMap<Integer, Block>();
        Block block = makeBlock(jsonBlock);

        if (block.getParent().equals("") ||
            !scriptMap.containsValue(block.getParent())) {   // Script does not exist
                scriptMap.put(scriptCount, block);
                scriptCount++;
        } else {                                             // Script exists
            Integer key = getKey(scriptMap.entrySet(), block);

            if (key.intValue() != -1)
                scriptMap.put(key, block);
        }

        return scriptMap;
    }

    /** makeBlock: Block
     *
     * Parses a JSONBlock into a JavaBlock.
     *
     * @param jsonBlock - JSON to parse
     * @return Block - Java Block object
     */
    private Block makeBlock(JSONObject jsonBlock) {

        String [] temp;
        String par;
        String nex;
        temp = (FileUtils.getJSONAttribute(jsonBlock, "opcode")).split("_");
        par = FileUtils.getJSONAttribute(jsonBlock, "parent");

        return new Block((String)jsonBlock, temp[0], temp[1], par);
    }

//------------------------------------------------------------------------------
//                                 Helpers
//------------------------------------------------------------------------------

    /** getKey: Integer
     *
     * Retrieves the key of a value stored in a map.
     *
     * @param map - complete set of key to entry mappings
     *        bl  - block whose key you wish to know
     * @return key - key for parent
     *         -1  - no mapping was found
     */
	private Integer getKey(Set<Map.Entry<Integer, Block>> map, Block bl) {

		for (Map.Entry<Integer, Block> entry : map)
			if (bl.isChild(entry.getValue())
				return entry.getKey();

        return -1;
	}

//------------------------------------------------------------------------------
//                                 Accessors
//------------------------------------------------------------------------------

    /** getScripts: HashMap<Integer, Block>
     *
     * Retrieves the scriptMap of the given target.
     *
     * @param name - target name
     * @return scriptMap - scriptMap for target
     */
    public HashMap<Integer, Block> getScripts(String name) { return catMap.get(name); }

    /** getScriptSize: HashMap<Integer, Block>
     *
     * Retrieves the scriptMap of the given target.
     *
     * @param name - target name
     * @return scriptMap - scriptMap for target
     */
     public int getScriptSize(String name) { return catmap.get(name).size(); }

//------------------------------------------------------------------------------
//                                 Mutators
//------------------------------------------------------------------------------

    /** setTargetBlocks: void
     *
     * Set the targetBlocks array to pull from.
     *
     * @param targetBlocks - JSONArray of target's used blocks
     * @return none
     */
    public void setTargetBlocks(JSONArray targetBlocks) { this.targetBlocks = targetBlocks; }

    /** setTargetName: void
     *
     * Set the target's name.
     *
     * @param name - name of the target that contains these scripts.
     * @return none
     */
    public void setTargetName(String name) { this.name = name; }
}
