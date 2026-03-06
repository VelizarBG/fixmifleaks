package velizarbg.fixmifleaks.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientChunkCache;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {
    @Shadow
    private ClientLevel level;
    @Shadow
    private int serverChunkRadius;

    @Inject(method = "cleanup", at = @At("HEAD"))
    private void doMoreClearing(CallbackInfo ci) {
        ClientLevel level = this.level;
        if (level != null) {
            ((ClientLevelAccessor) level).setChunkSource(new ClientChunkCache(level, serverChunkRadius));
        }
    }
}
