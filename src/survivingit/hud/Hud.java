package survivingit.hud;

import survivingit.gameobjects.Player;
import survivingit.graphics.HudRenderer;
import survivingit.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Hud is a container for hud elements.
 * It creates the elements with correct data and positions.
 */
public class Hud {

    private List<HudElement> elements;
    private ItemContainerHud inventoryHud;

    /**
     * Creates a new hud
     * @param player player to set data from
     */
    public Hud(Player player) {
        this.elements = new ArrayList<>();
        this.add(new HealthBar(5, 90, 15, 5, player));
        this.add(new Icon(21, 90, 2.5, 5, Sprite.ICON_HEART));
        this.add(new EquippedItemContainerHud(80, 90, 15, 5,
                player.getPlayerInventory().getEquippedItemContainer(), 5));
        this.inventoryHud = new ItemContainerHud(80, 70, 15, 20,
                player.getPlayerInventory().getPassiveStorage(), 5, true);
    }

    private void add(HudElement element) {
        this.elements.add(element);
    }

    /**
     * Renders all hud elements
     * @param renderer Renderer to use
     */
    public void render(HudRenderer renderer) {
        for (HudElement element : elements) {
            if (element.isVisible()) {
                element.render(renderer);
            }
        }
        if (inventoryHud.isVisible()) {
            inventoryHud.render(renderer);
        }
    }

    /**
     * Toggle inventory visibility
     */
    public void toggleInvetory() {
        this.inventoryHud.toggleVisible();
    }
}
