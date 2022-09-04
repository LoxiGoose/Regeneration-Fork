package mc.craig.software.regen.common.regeneration.fabric;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import mc.craig.software.regen.common.regeneration.RegenerationData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;
import java.util.Optional;

public class RegenerationDataImpl extends RegenerationData implements ComponentV3 {

    public RegenerationDataImpl(Player player) {
        super(player);
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.deserializeNBT(tag);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        CompoundTag nbt = this.serializeNBT();
        for (String key : nbt.getAllKeys()) {
            tag.put(key, Objects.requireNonNull(nbt.get(key)));
        }
    }

    public static Optional<RegenerationData> get(Player player) {
        try {
            return Optional.of(RegenerationComponents.REGENERATION_DATA.get(player));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
