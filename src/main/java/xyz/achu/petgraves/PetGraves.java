package xyz.achu.petgraves;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.achu.petgraves.block.GraveBlock;

public class PetGraves implements ModInitializer {
	public static final String MODID = "petgraves";

	public static final Logger logger = LogManager.getLogger();
	public static final Block GRAVE_BLOCK = register(new GraveBlock(defaultSettings().nonOpaque().strength(9, 3600000)), "grave_block");

	private static Block.Settings defaultSettings() {
		return FabricBlockSettings.of(Material.STONE).build();
	}

	private static Block register(Block block, String name) {
		Identifier id = new Identifier(PetGraves.MODID, name);
		Item.Settings settings = new Item.Settings()
				.group(ItemGroup.DECORATIONS);
		BlockItem item = new BlockItem(block, settings);
		Registry.register((Registry.BLOCK), id, block);
		Registry.register(Registry.ITEM, id, item);
		return block;
	}

	@Override
	public void onInitialize() {
		logger.info("Hewwo! UwU");
	}
}
