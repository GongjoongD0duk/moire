package org.gjdd.moire.mixin;

import eu.pb4.polymer.virtualentity.api.attachment.EntityAttachment;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerEntityManager;
import net.minecraft.world.entity.EntityLike;
import org.gjdd.moire.decorators.entities.EntityDecoratorContext;
import org.gjdd.moire.decorators.entities.EntityDecorators;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ServerEntityManager.class)
public abstract class ServerEntityManagerMixin {
    @Inject(method = "addEntity(Lnet/minecraft/world/entity/EntityLike;Z)Z", at = @At(value = "RETURN"))
    private void moire$injectAddEntity(EntityLike entityLike, boolean existing, CallbackInfoReturnable<Boolean> info) {
        if (!info.getReturnValue() || !(entityLike instanceof Entity entity)) {
            return;
        }

        var context = new EntityDecoratorContext<>(entity);
        EntityDecorators.INSTANCE
                .get(entity)
                .stream()
                .map(decorator -> decorator.decorate(context))
                .forEach(holder -> EntityAttachment.ofTicking(holder, entity));
    }
}
