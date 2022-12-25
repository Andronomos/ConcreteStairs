package andronomos.concretestairs;

import andronomos.concretestairs.registry.ModBlocks;
import andronomos.concretestairs.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ConcreteStairs.MODID)
public class ConcreteStairs {
	public static final String MODID = "concretestairs";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final String TAB_NAME = "concretestairs_group";
	public static final CreativeModeTab CONCRETESTAIRS_TAB = new ConcreteStairsItemGroup(TAB_NAME);

	public ConcreteStairs() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		// Register the commonSetup method for modloading
		modEventBus.addListener(this::commonSetup);

		ModBlocks.BLOCKS.register(modEventBus);
		ModItems.ITEMS.register(modEventBus);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void commonSetup(final FMLCommonSetupEvent event)
	{

	}
}
