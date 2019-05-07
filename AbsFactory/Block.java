public class Block {
    String id;
    String category;
    String type;
    String parent;

    public Block(String i, String c, String t, String p) {
        id = i; category = c; type = t; parent = p; }
    public String getID() { return id; }
    public String getCategory() { return category; }
    public String getType() { return type; }
    public String getParent() { return parent; }
    public boolean isChild(Block parentBlock) {
        if (parent.equals(parentBlock.getID()))
            return true;
        return false;
    }
}
