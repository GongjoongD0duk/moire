package org.gjdd.moire.decorators.entities

import net.minecraft.entity.Entity
import org.gjdd.moire.decorators.Decorator
import org.gjdd.moire.decorators.DecoratorContext

public interface EntityDecorator<T : Entity> : Decorator<EntityDecoratorContext<T>>

public data class EntityDecoratorContext<T : Entity>(public val entity: T) : DecoratorContext