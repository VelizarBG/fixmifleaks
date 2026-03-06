package velizarbg.fixmifleaks.mixin.geckolib;

import com.bawnorton.mixinsquared.TargetHandler;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.LivingEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.lang.ref.WeakReference;

@Restriction(require = @Condition("geckolib3"))
@Environment(EnvType.CLIENT)
@Mixin(value = HumanoidArmorLayer.class, priority = 1500)
public class HumanoidArmorLayerMixin {
    @Unique
    private WeakReference<LivingEntity> fixmifleaks$geckolib3$gl_storedEntity = new WeakReference<>(null);

    @TargetHandler(
            mixin = "software.bernie.geckolib3.mixins.fabric.MixinArmorFeatureRenderer",
            name = "storeEntity"
    )
    @Redirect(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "FIELD",
                    target = "name=/((?i)[a-z0-9]+\\$)?(geckolib3\\$)?gl_storedEntity(\\$\\d+)?/",
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void setGl_storedEntity(@Coerce Object instance, LivingEntity entity) {
        fixmifleaks$geckolib3$gl_storedEntity = new WeakReference<>(entity);
    }

    @TargetHandler(
            mixin = "software.bernie.geckolib3.mixins.fabric.MixinArmorFeatureRenderer",
            name = "removeStored"
    )
    @Redirect(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "FIELD",
                    target = "name=/((?i)[a-z0-9]+\\$)?(geckolib3\\$)?gl_storedEntity(\\$\\d+)?/",
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void setGl_storedEntity2(@Coerce Object instance, LivingEntity entity) {
        fixmifleaks$geckolib3$gl_storedEntity = new WeakReference<>(entity);
    }

    @TargetHandler(
            mixin = "software.bernie.geckolib3.mixins.fabric.MixinArmorFeatureRenderer",
            name = "selectArmorModel"
    )
    @Redirect(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "FIELD",
                    target = "name=/((?i)[a-z0-9]+\\$)?(geckolib3\\$)?gl_storedEntity(\\$\\d+)?/",
                    opcode = Opcodes.GETFIELD
            )
    )
    private LivingEntity getGl_storedEntity(@Coerce Object instance) {
        return fixmifleaks$geckolib3$gl_storedEntity.get();
    }

    @TargetHandler(
            mixin = "software.bernie.geckolib3.mixins.fabric.MixinArmorFeatureRenderer",
            name = "getArmorTexture"
    )
    @Redirect(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "FIELD",
                    target = "name=/((?i)[a-z0-9]+\\$)?(geckolib3\\$)?gl_storedEntity(\\$\\d+)?/",
                    opcode = Opcodes.GETFIELD
            )
    )
    private LivingEntity getGl_storedEntity2(@Coerce Object instance) {
        return fixmifleaks$geckolib3$gl_storedEntity.get();
    }
}
