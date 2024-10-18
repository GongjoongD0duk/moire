package org.gjdd.moire.decorators.entities

import eu.pb4.polymer.virtualentity.api.ElementHolder
import net.minecraft.entity.Entity

public interface EntityDecorator<T : Entity> {
    public fun decorate(context: EntityDecoratorContext<T>): ElementHolder
}

public data class EntityDecoratorContext<T : Entity>(public val entity: T)

public fun <T : Entity> entityDecorator(block: EntityDecoratorContext<T>.() -> ElementHolder): EntityDecorator<T> =
    object : EntityDecorator<T> {
        override fun decorate(context: EntityDecoratorContext<T>) = context.block()
    }