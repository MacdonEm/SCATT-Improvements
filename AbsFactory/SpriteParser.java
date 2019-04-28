
/** SpriteParser.java
 *
 * Basis for a sprite.
 * Sprites should parse the Scratch Project's stage
 * appropriately for the version, noting the...
 *   -> sprite name
 *   -> sprite variables
 *   -> sprite lists
 *   -> object counts included in a sprite
 *   -> block category counts included in a sprite
 *
 * @author Emily Macdonald
 * @version Spring 2019
 */
public interface SpriteParser {

    // populate should create the sprite to the above specifications
    public abstract void populate();
}
