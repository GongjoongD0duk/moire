package org.gjdd.moire

import net.fabricmc.api.ModInitializer
import net.minecraft.block.Blocks
import net.minecraft.entity.mob.CreeperEntity
import net.minecraft.util.math.Vec3d
import org.gjdd.moire.decorators.entities.EntityDecorator
import org.gjdd.moire.decorators.entities.EntityDecoratorContext
import org.gjdd.moire.decorators.entities.EntityDecorators
import org.gjdd.moire.elements.*
import org.gjdd.moire.matrices.rotateLocalYDegrees

class EntityDecoratorTest : ModInitializer {
    private object CreeperEntityDecorator : EntityDecorator<CreeperEntity> {
        override fun decorate(context: EntityDecoratorContext<CreeperEntity>) = elementHolder {
            blockDisplayElement {
                transformation {
                    translateLocal(-0.5f, -0.5f, -2.0f)
                }

                addTickListener {
                    transform {
                        rotateLocalYDegrees(11.25f)
                    }

                    startInterpolation(1)
                }

                blockState = Blocks.TNT.defaultState
                offset = Vec3d(0.0, 1.0, 0.0)
                teleportDuration = 5
            }
        }
    }

    override fun onInitialize() {
        EntityDecorators.register(CreeperEntity::class, CreeperEntityDecorator)
    }
}