package velizarbg.fixmifleaks.mixin.geckolib;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.renderers.geo.GeoReplacedEntityRenderer;
import velizarbg.fixmifleaks.ClearableRenderer;

// Disabled because causes a crash upon several world switches
@Restriction(require = @Condition("geckolib3"))
@Environment(EnvType.CLIENT)
@Mixin(GeoReplacedEntityRenderer.class)
public class disabled_GeoReplacedEntityRendererMixin<T extends IAnimatable> implements ClearableRenderer {
    @Shadow
    protected T animatable;
    @Shadow
    protected IAnimatable currentAnimatable;

    @Override
    public void fixmifleaks$clear() {
        animatable = null;
        currentAnimatable = null;
    }
}
