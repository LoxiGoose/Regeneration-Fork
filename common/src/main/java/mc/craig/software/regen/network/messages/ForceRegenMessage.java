package mc.craig.software.regen.network.messages;

import mc.craig.software.regen.common.regen.RegenerationData;
import mc.craig.software.regen.common.regen.state.RegenStates;
import mc.craig.software.regen.network.MessageC2S;
import mc.craig.software.regen.network.MessageContext;
import mc.craig.software.regen.network.MessageType;
import mc.craig.software.regen.network.RegenNetwork;
import mc.craig.software.regen.util.RegenDamageTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.damagesource.DamageSource;
import org.jetbrains.annotations.NotNull;

public class ForceRegenMessage extends MessageC2S {

    public ForceRegenMessage() {
    }

    public ForceRegenMessage(FriendlyByteBuf buffer) {
    }

    public void handle(MessageContext context) {
        RegenerationData.get(context.getPlayer()).ifPresent((cap) -> {
            if (cap.regenState() == RegenStates.ALIVE || cap.regenState().isGraceful()) {
                if (cap.canRegenerate()) {
                    cap.getLiving().die(new DamageSource(RegenDamageTypes.getHolder(context.getPlayer(), RegenDamageTypes.REGEN_DMG_FORCED)));
                }
            }
        });
    }

    @NotNull
    @Override
    public MessageType getType() {
        return RegenNetwork.FORCE_REGENERATION;
    }

    public void toBytes(FriendlyByteBuf buf) {

    }

}
