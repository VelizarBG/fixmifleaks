package velizarbg.fixmifleaks.mixin;

import lol.bai.badpackets.impl.handler.AbstractPacketHandler;
import lol.bai.badpackets.impl.registry.ChannelRegistry;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.ref.WeakReference;
import java.util.Set;

@Restriction(require = @Condition("badpackets"))
@Mixin(ChannelRegistry.class)
public class ChannelRegistryMixin {
    @Shadow
    @Final
    private Set<AbstractPacketHandler<?>> handlers;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void registerSourceCleaner(CallbackInfo ci) {
        var handlersRef = new WeakReference<>(handlers);
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            Set<AbstractPacketHandler<?>> handlers = handlersRef.get();
            if (handlers != null) {
                handlers.clear();
            }
        });
    }
}
