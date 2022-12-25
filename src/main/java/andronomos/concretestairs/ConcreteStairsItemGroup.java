package andronomos.concretestairs;

import andronomos.concretestairs.registry.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ConcreteStairsItemGroup extends CreativeModeTab {
	public ConcreteStairsItemGroup(String name) {
		super(name);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(
				ModBlocks.BLOCKS.getEntries().stream().findFirst().get().get()
		);
	}
}
