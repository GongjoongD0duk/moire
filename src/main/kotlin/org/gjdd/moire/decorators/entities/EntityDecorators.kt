package org.gjdd.moire.decorators.entities

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import kotlin.reflect.KClass

public object EntityDecorators {
    private var class2Decorators = mapOf<KClass<out Entity>, List<EntityDecorator<out Entity>>>()
    private var type2Decorators = mapOf<EntityType<out Entity>, List<EntityDecorator<out Entity>>>()

    public fun <T : Entity> register(clazz: KClass<T>, decorator: EntityDecorator<T>) {
        class2Decorators += clazz to (class2Decorators[clazz] ?: listOf()) + decorator
    }

    public fun <T : Entity> register(type: EntityType<T>, decorator: EntityDecorator<T>) {
        type2Decorators += type to (type2Decorators[type] ?: listOf()) + decorator
    }

    @Suppress("UNCHECKED_CAST")
    @PublishedApi
    internal fun <T : Entity> get(entity: T): List<EntityDecorator<T>> =
        (class2Decorators[entity::class] as? List<EntityDecorator<T>> ?: listOf()) +
                (type2Decorators[entity.type] as? List<EntityDecorator<T>> ?: listOf())
}

public inline fun <reified T : Entity> EntityDecorators.register(decorator: EntityDecorator<T>): Unit =
    register(T::class, decorator)