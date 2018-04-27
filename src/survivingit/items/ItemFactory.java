package survivingit.items;

import survivingit.graphics.Sprite;

public abstract class ItemFactory {

	/**
	 * Static NONE_ITEM object since no object specific state is needed.
	 */
	public final static Item NONE_ITEM = new Item(ItemType.NONE, "None", "None", Sprite.WATER);

	/**
	 * Creates and returns a new Item object of the entered ItemType.
	 * @param itemType to be created.
	 * @return a new Item object of the entered ItemType.
	 */
    public static Item createItem(ItemType itemType) {
		switch (itemType) {
			case KNIFE: {
				Item knife = new Item(itemType, "Knife", "Stabby stabby", Sprite.CAMPFIRE);
				knife.addEffect(new MeleeAttackEffect(10, 10, knife));
				return knife;
			}
			case BERRIES: {
				Item berries = new Item(itemType, "Berries", "Delicious", Sprite.ICON_HEART);
				berries.addEffect(new HealEffect(25, berries));
				return berries;
			}
			case BOOTS: {
				Item boots = new Item(itemType, "Gyllene skor", "Diggiloo diggiley", Sprite.GYLLENE_SKOR);
				boots.addEffect(new MoveSpeedBoostEffect(10, boots));
				return boots;
			}
			case NONE: {
				throw new IllegalArgumentException("Cannot create item of type NONE");
			}
		}
		// Some unknown enum type.
		return null;
	}


}
