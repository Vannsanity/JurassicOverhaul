package net.vannsanity.jurassicoverhaul.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vannsanity.jurassicoverhaul.JurassicOverhaul;
import net.vannsanity.jurassicoverhaul.item.custom.DNASampleItem;
import net.vannsanity.jurassicoverhaul.item.custom.EmptySyringeItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, JurassicOverhaul.MOD_ID);

    public static final RegistryObject<Item> AMBER = ITEMS.register("amber",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EMPTY_SYRINGE = ITEMS.register("empty_syringe",
            () -> new EmptySyringeItem(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> DNA_SAMPLE = ITEMS.register("dna_sample",
            () -> new DNASampleItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
