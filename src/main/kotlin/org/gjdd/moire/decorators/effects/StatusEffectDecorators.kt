package org.gjdd.moire.decorators.effects

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.registry.entry.RegistryEntry

public object StatusEffectDecorators {
    private var effect2Decorators = mapOf<RegistryEntry<StatusEffect>, List<StatusEffectDecorator>>()

    public fun register(effect: RegistryEntry<StatusEffect>, decorator: StatusEffectDecorator) {
        effect2Decorators += effect to (effect2Decorators[effect] ?: listOf()) + decorator
    }

    @PublishedApi
    internal fun get(instance: StatusEffectInstance): List<StatusEffectDecorator> =
        effect2Decorators[instance.effectType] ?: listOf()
}