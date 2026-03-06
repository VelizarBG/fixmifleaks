package velizarbg.fixmifleaks.mixin.geckolib;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;
import velizarbg.fixmifleaks.ClearableRenderer;

@Restriction(require = @Condition("geckolib3"))
@Environment(EnvType.CLIENT)
@Mixin(GeoBlockRenderer.class)
public class GeoBlockRendererMixin<T extends BlockEntity & IAnimatable> implements ClearableRenderer {
    @Shadow
    protected T animatable;

    @Override
    public void fixmifleaks$clear() {
        animatable = null;
    }
}
