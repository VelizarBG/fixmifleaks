package velizarbg.fixmifleaks.mixin;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import org.orecruncher.dsurround.runtime.audio.SoundFXProcessor;
import org.orecruncher.dsurround.runtime.audio.SourceContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Restriction(require = @Condition("dsurround"))
@Environment(EnvType.CLIENT)
@Mixin(SoundFXProcessor.class)
public class SoundFXProcessorMixin {
    @Shadow
    private static SourceContext[] sources;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void registerSourceCleaner(CallbackInfo ci) {
        ClientPlayConnectionEvents.DISCONNECT.register((packetListener, mc) -> {
            if (sources != null) {
                Arrays.fill(sources, null);
            }
        });
    }
}
