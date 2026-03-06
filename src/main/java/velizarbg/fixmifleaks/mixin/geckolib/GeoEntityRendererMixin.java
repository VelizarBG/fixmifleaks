package velizarbg.fixmifleaks.mixin.geckolib;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import velizarbg.fixmifleaks.ClearableRenderer;

@Restriction(require = @Condition("geckolib3"))
@Environment(EnvType.CLIENT)
@Mixin(GeoEntityRenderer.class)
public class GeoEntityRendererMixin<T extends LivingEntity & IAnimatable> implements ClearableRenderer {
    @Shadow
    protected T animatable;

    @Override
    public void fixmifleaks$clear() {
        animatable = null;
    }
}
