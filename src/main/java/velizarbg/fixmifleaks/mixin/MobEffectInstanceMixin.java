package velizarbg.fixmifleaks.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.lang.ref.WeakReference;

@Restriction(require = @Condition("things"))
@Mixin(value = MobEffectInstance.class, priority = 1500)
public class MobEffectInstanceMixin {
    @Unique
    private WeakReference<LivingEntity> fixmifleaks$things$attachedEntityRef = new WeakReference<>(null);

    @TargetHandler(
            mixin = "com.glisco.things.mixin.StatusEffectInstanceMixin",
            name = "things$setAttachedEntity"
    )
    @Redirect(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/effect/MobEffectInstance;things$attachedEntity:Lnet/minecraft/world/entity/LivingEntity;",
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void setThings$attachedEntity(@Coerce Object instance, LivingEntity entity) {
        fixmifleaks$things$attachedEntityRef = new WeakReference<>(entity);
    }

    @TargetHandler(
            mixin = "com.glisco.things.mixin.StatusEffectInstanceMixin",
            name = "things$getAttachedEntity"
    )
    @Redirect(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/effect/MobEffectInstance;things$attachedEntity:Lnet/minecraft/world/entity/LivingEntity;",
                    opcode = Opcodes.GETFIELD
            )
    )
    private LivingEntity getThings$attachedEntity(@Coerce Object instance) {
        return fixmifleaks$things$attachedEntityRef.get();
    }

    @TargetHandler(
            mixin = "com.glisco.things.mixin.StatusEffectInstanceMixin",
            name = "skipUpdate"
    )
    @Redirect(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/effect/MobEffectInstance;things$attachedEntity:Lnet/minecraft/world/entity/LivingEntity;",
                    opcode = Opcodes.GETFIELD
            )
    )
    private LivingEntity getThings$attachedEntity2(@Coerce Object instance) {
        return fixmifleaks$things$attachedEntityRef.get();
    }
}
