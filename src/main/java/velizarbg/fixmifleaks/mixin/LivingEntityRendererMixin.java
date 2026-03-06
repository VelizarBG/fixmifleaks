package velizarbg.fixmifleaks.mixin;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import traben.entity_model_features.utils.EMFEntity;
import traben.entity_texture_features.utils.ETFEntity;
import velizarbg.fixmifleaks.ClearableRenderer;

@Restriction(require = { @Condition("entity_model_features"), @Condition("entity_texture_features") })
@Environment(EnvType.CLIENT)
@Mixin(value = LivingEntityRenderer.class, priority = 1500)
public class LivingEntityRendererMixin implements ClearableRenderer {
    @Shadow
    private EMFEntity emf$heldEntity;
    @Shadow
    private ETFEntity etf$heldEntity;

    @Override
    public void fixmifleaks$clear() {
        emf$heldEntity = null;
        etf$heldEntity = null;
    }
}
