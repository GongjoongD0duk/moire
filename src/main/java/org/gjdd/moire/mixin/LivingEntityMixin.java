package org.gjdd.moire.mixin;

import eu.pb4.polymer.virtualentity.api.attachment.EntityAttachment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import org.gjdd.moire.decorators.effects.StatusEffectDecoratorContext;
import org.gjdd.moire.decorators.effects.StatusEffectDecorators;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "onStatusEffectApplied", at = @At(value = "TAIL"))
    private void moire$injectOnStatusEffectApplied(StatusEffectInstance instance, Entity source, CallbackInfo info) {
        moire$decorate(instance);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void moire$injectReadCustomDataFromNbt(NbtCompound nbt, CallbackInfo info, NbtList nbtList, int i, NbtCompound nbtCompound, StatusEffectInstance instance) {
        moire$decorate(instance);
    }

    @Unique
    private void moire$decorate(StatusEffectInstance instance) {
        var entity = (LivingEntity) (Object) this;
        if (entity.getWorld().isClient()) {
            return;
        }

        var context = new StatusEffectDecoratorContext(instance, entity);
        StatusEffectDecorators.INSTANCE
                .get(instance)
                .stream()
                .map(decorator -> decorator.decorate(context))
                .forEach(holder -> EntityAttachment.ofTicking(holder, entity));
    }
}
