package velizarbg.fixmifleaks.mixin.geckolib;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.renderers.geo.ExtendedGeoEntityRenderer;
import velizarbg.fixmifleaks.ClearableRenderer;

@Restriction(require = @Condition("geckolib3"))
@Environment(EnvType.CLIENT)
@Mixin(ExtendedGeoEntityRenderer.class)
public class ExtendedGeoEntityRendererMixin<T extends LivingEntity & IAnimatable> implements ClearableRenderer {
    @Shadow
    protected T currentEntityBeingRendered;

    @Override
    public void fixmifleaks$clear() {
        currentEntityBeingRendered = null;
    }
}
