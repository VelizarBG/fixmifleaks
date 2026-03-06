package velizarbg.fixmifleaks.mixin;

import dev.emi.trinkets.TrinketScreen;
import dev.emi.trinkets.TrinketScreenManager;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Restriction(require = @Condition("trinkets"))
@Environment(EnvType.CLIENT)
@Mixin(TrinketScreenManager.class)
public class TrinketScreenManagerMixin {
    @Shadow
    public static TrinketScreen currentScreen;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void registerSourceCleaner(CallbackInfo ci) {
        ClientPlayConnectionEvents.DISCONNECT.register((packetListener, mc) -> {
            currentScreen = null;
        });
    }
}
