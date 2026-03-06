package velizarbg.fixmifleaks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import velizarbg.fixmifleaks.mixin.BlockEntityRenderDispatcherAccessor;
import velizarbg.fixmifleaks.mixin.EntityRenderDispatcherAccessor;

import java.util.Collection;


public class FixMIFLeaksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.DISCONNECT.register((packetListener, mc) -> {
            EntityRenderDispatcherAccessor accessedERD = (EntityRenderDispatcherAccessor) mc.getEntityRenderDispatcher();
            clearRenderers(accessedERD.getRenderers().values());
            clearRenderers(accessedERD.getPlayerRenderers().values());
            clearRenderers(((BlockEntityRenderDispatcherAccessor) mc.getBlockEntityRenderDispatcher()).getRenderers().values());
        });
    }

    private static <T> void clearRenderers(Collection<T> renderers) {
        for (var renderer : renderers) {
            if (renderer instanceof ClearableRenderer clearable) {
                clearable.fixmifleaks$clear();
            }
        }
    }
}
