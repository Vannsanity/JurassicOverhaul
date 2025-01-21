package net.vannsanity.jurassicoverhaul.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nullable;
import java.util.List;

public class DNASampleItem extends Item {

    public DNASampleItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // Check if the syringe has DNA data
        if (stack.hasTag()) {
            CompoundTag nbt = stack.getTag();

            // Display the mob name
            if (nbt.contains("mob")) {
                tooltip.add(Component.literal("DNA: " + nbt.getString("mob")));
            }

            // Display the DNA properties
            nbt.getAllKeys().forEach(key -> {
                if (!key.equals("mob")) {
                    if (nbt.contains(key, 99)) { // 99 = Any numeric type
                        tooltip.add(Component.literal(key + ": " + nbt.getInt(key)));
                    } else if (nbt.contains(key, 1)) { // 1 = Boolean type
                        tooltip.add(Component.literal(key + ": " + (nbt.getBoolean(key) ? "Yes" : "No")));
                    }
                }
            });
        } else {
            tooltip.add(Component.translatable("tooltip.jurassicoverhaul.empty_syringe")); // Fallback for empty syringes
        }
    }
}
