package org.gjdd.moire.decorators.entities

import net.fabricmc.api.ModInitializer
import net.minecraft.entity.mob.CreeperEntity
import net.minecraft.item.Items
import org.gjdd.moire.elements.*
import org.gjdd.moire.matrices.rotateLocalYDegrees

class EntityDecoratorTest : ModInitializer {
    private val testEntityDecorator = entityDecorator<CreeperEntity> {
        elementHolder {
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

            addVirtualPassengerTo(entity)
        }
    }

    override fun onInitialize() {
        EntityDecorators.register(CreeperEntity::class, testEntityDecorator)
    }
}