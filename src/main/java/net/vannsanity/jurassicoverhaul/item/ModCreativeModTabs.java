package net.vannsanity.jurassicoverhaul.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.vannsanity.jurassicoverhaul.JurassicOverhaul;
import net.vannsanity.jurassicoverhaul.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JurassicOverhaul.MOD_ID);


    public static final RegistryObject<CreativeModeTab> JURASSIC_OVERHAUL_BLOCKS = CREATIVE_MODE_TABS.register("jurassic_overhaul_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.AMBER_BLOCK.get()))
                    .title(Component.translatable("crativetab.jurassic_overhaul_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.AMBER_BLOCK.get());
                        output.accept(ModBlocks.AMBER_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_AMBER_ORE.get());


                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> JURASSIC_OVERHAUL_ITEMS = CREATIVE_MODE_TABS.register("jurassic_overhaul_items",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.AMBER.get()))
                    .title(Component.translatable("crativetab.jurassic_overhaul_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.AMBER.get());
                        output.accept(ModItems.EMPTY_SYRINGE.get());
                        output.accept(ModItems.DNA_SAMPLE.get());


                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
