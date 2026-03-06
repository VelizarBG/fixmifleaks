package velizarbg.fixmifleaks.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientChunkCache;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Environment(EnvType.CLIENT)
@Mixin(ClientLevel.class)
public interface ClientLevelAccessor {
    @Mutable
    @Accessor
    void setChunkSource(ClientChunkCache chunkSource);

    @Mutable
    @Accessor
    void setConnection(ClientPacketListener connection);
}
