package net.vannsanity.jurassicoverhaul.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.vannsanity.jurassicoverhaul.item.ModItems;
import net.vannsanity.jurassicoverhaul.utils.MobDNA;

import java.util.Map;

public class EmptySyringeItem extends Item {

    // Map of DNA properties for each mob (define this for the mobs you allow extraction from)
    private static final Map<ResourceLocation, MobDNA> DNA_PROPERTIES = Map.of(
            new ResourceLocation("minecraft", "bat"), new MobDNA("Bat", Map.of("NocturnalCycle", true, "Echolocation", true)),
            new ResourceLocation("minecraft", "cat"), new MobDNA("Cat", Map.of("Empathy", 1, "Agility", 3, "Intelligence", 2)),
            new ResourceLocation("minecraft", "spider"), new MobDNA("Spider", Map.of("NocturnalCycle", true, "Agility", 3, "Aggressiveness", 3))
            // Add more mobs here...
    );

    public EmptySyringeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        // Check if the target mob is extractable
        ResourceLocation mobId = EntityType.getKey(target.getType());
        if (DNA_PROPERTIES.containsKey(mobId)) {
            if (!player.level().isClientSide) {
                // Get the DNA properties for the target mob
                MobDNA mobDNA = DNA_PROPERTIES.get(mobId);

                // Create a new filled syringe with DNA data
                ItemStack dnaSample = new ItemStack(ModItems.DNA_SAMPLE.get());
                CompoundTag nbt = new CompoundTag();
                nbt.putString("mob", mobDNA.getMobName()); // Store the mob name
                mobDNA.getProperties().forEach((key, value) -> {
                    if (value instanceof Integer) {
                        nbt.putInt(key, (Integer) value); // Store integer properties
                    } else if (value instanceof Boolean) {
                        nbt.putBoolean(key, (Boolean) value); // Store boolean properties
                    }
                });
                dnaSample.setTag(nbt);

                stack.shrink(1);

                if (!player.getInventory().add(dnaSample)){

                    player.drop(dnaSample, false);
                }

                // Optional: Send a message to the player
                if (player instanceof ServerPlayer) {
                    player.displayClientMessage(Component.literal("You extracted DNA from a " + mobDNA.getMobName() + "!"), true);
                }
            }
            return InteractionResult.SUCCESS; // Indicate the action was successful
        }

        return InteractionResult.PASS; // If mob is not extractable, do nothing
    }
}


