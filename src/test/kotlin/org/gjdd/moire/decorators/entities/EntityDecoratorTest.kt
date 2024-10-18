package org.gjdd.moire.decorators.entities

import net.fabricmc.api.ModInitializer
import net.minecraft.entity.mob.CreeperEntity
import net.minecraft.item.Items
import org.gjdd.moire.elements.*
import org.gjdd.moire.matrices.rotateLocalYDegrees

class EntityDecoratorTest : ModInitializer {
    private object TestEntityDecorator : EntityDecorator<CreeperEntity> {
        override fun decorate(context: EntityDecoratorContext<CreeperEntity>) = elementHolder {
            itemDisplayElement {
                onTick {
                    transform {
                        rotateLocalYDegrees(11.25f)
                    }

                    startInterpolation(1)
                }

                item = Items.TNT.defaultStack
                ignorePositionUpdates()
            }

            addVirtualPassengerTo(context.entity)
        }
    }

    override fun onInitialize() {
        EntityDecorators.register(CreeperEntity::class, TestEntityDecorator)
    }
}