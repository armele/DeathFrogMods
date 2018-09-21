package binnie.extrabees.apiary;

import net.minecraft.item.Item;

public class SoulFrameRegistryHack {
	// The ExtraBees mod makes it hard to get to the actual registered item for a Soul Frame.
	// It is available only on a protected member variable.  Hence, this hack to obtain it.
	public static Item registeredSoulFrameItem() {
		ItemHiveFrame soulFrame = new ItemHiveFrame(EnumHiveFrame.Soul);
		
		return soulFrame.frame.item;
	}
}
