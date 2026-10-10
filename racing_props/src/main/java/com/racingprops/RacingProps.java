package com.racingprops;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.Shapes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(RacingProps.MODID)
public class RacingProps {
    public static final String MODID = "racingprops";

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredBlock<Block> TIRE_BARRIER = BLOCKS.registerBlock("tire_barrier",
            TireBarrierBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(1.5f, 3f)
                    .sound(SoundType.WOOL));

    public static final DeferredBlock<Block> TRAFFIC_CONE = BLOCKS.registerBlock("traffic_cone",
            p -> new ShapedBlock(p, Block.box(3, 0, 3, 13, 14, 13)),
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(0.3f)
                    .sound(SoundType.WOOL).noOcclusion());

    public static final DeferredBlock<Block> RUMBLE_STRIP = BLOCKS.registerBlock("rumble_strip",
            p -> new ShapedBlock(p, Block.box(0, 0, 0, 16, 2, 16)),
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(1.0f)
                    .sound(SoundType.STONE).noOcclusion());

    public static final DeferredBlock<Block> FINISH_LINE = BLOCKS.registerBlock("finish_line",
            p -> new ShapedBlock(p, Block.box(0, 0, 0, 16, 1, 16)),
            BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(0.5f)
                    .sound(SoundType.STONE).noOcclusion());

    public static final DeferredBlock<Block> CONCRETE_BARRIER = BLOCKS.registerBlock("concrete_barrier",
            ConcreteBarrierBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(2.0f, 6f)
                    .sound(SoundType.STONE).noOcclusion());

    static {
        ITEMS.registerSimpleBlockItem(TIRE_BARRIER);
        ITEMS.registerSimpleBlockItem(TRAFFIC_CONE);
        ITEMS.registerSimpleBlockItem(RUMBLE_STRIP);
        ITEMS.registerSimpleBlockItem(FINISH_LINE);
        ITEMS.registerSimpleBlockItem(CONCRETE_BARRIER);
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("racing_props",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.racingprops"))
                    .icon(() -> TIRE_BARRIER.get().asItem().getDefaultInstance())
                    .displayItems((params, out) -> {
                        out.accept(TIRE_BARRIER.get());
                        out.accept(CONCRETE_BARRIER.get());
                        out.accept(TRAFFIC_CONE.get());
                        out.accept(RUMBLE_STRIP.get());
                        out.accept(FINISH_LINE.get());
                    }).build());

    public RacingProps(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        TABS.register(bus);
    }
}
