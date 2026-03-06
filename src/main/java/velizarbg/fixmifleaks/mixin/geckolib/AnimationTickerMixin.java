package velizarbg.fixmifleaks.mixin.geckolib;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.event.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import software.bernie.geckolib3.util.AnimationTicker;

import java.util.ArrayList;
import java.util.List;

@Restriction(require = @Condition("geckolib3"))
@Environment(EnvType.CLIENT)
@Mixin(AnimationTicker.class)
public class AnimationTickerMixin {
    @Unique
    private static final List<ClientTickEvents.StartTick> LISTENERS = new ArrayList<>();

    static {
        ClientTickEvents.START_CLIENT_TICK.register(mc -> {
            for (ClientTickEvents.StartTick listener : LISTENERS) {
                listener.onStartTick(mc);
            }
        });
        ClientPlayConnectionEvents.DISCONNECT.register((packetListener, mc) -> LISTENERS.clear());
    }

    @Definition(id = "START_CLIENT_TICK", field = "Lnet/fabricmc/fabric/api/client/event/lifecycle/v1/ClientTickEvents;START_CLIENT_TICK:Lnet/fabricmc/fabric/api/event/Event;")
    @Definition(id = "register", method = "Lnet/fabricmc/fabric/api/event/Event;register(Ljava/lang/Object;)V")
    @Expression("START_CLIENT_TICK.register(?)")
    @WrapOperation(method = "<init>", at = @At("MIXINEXTRAS:EXPRESSION"))
    private <T> void addToListeners(Event<T> event, T listener, Operation<Void> original) {
        LISTENERS.add((ClientTickEvents.StartTick) listener);
    }
}
