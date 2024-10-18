package org.gjdd.moire.decorators.effects

import eu.pb4.polymer.virtualentity.api.ElementHolder
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance

public interface StatusEffectDecorator {
    public fun decorate(context: StatusEffectDecoratorContext): ElementHolder
}

public data class StatusEffectDecoratorContext(
    public val instance: StatusEffectInstance,
    public val entity: LivingEntity
)