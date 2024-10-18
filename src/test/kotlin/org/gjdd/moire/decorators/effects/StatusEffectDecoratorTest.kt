package org.gjdd.moire.decorators.effects

import net.fabricmc.api.ModInitializer
import net.minecraft.block.Blocks
import net.minecraft.entity.effect.StatusEffects
import org.gjdd.moire.elements.*

class StatusEffectDecoratorTest : ModInitializer {
    private object TestStatusEffectDecorator : StatusEffectDecorator {
        override fun decorate(context: StatusEffectDecoratorContext) = elementHolder {
            blockDisplayElement {
                transformation {
                    translateLocal(-0.5f, -0.5f, -0.5f)
                }

                blockState = Blocks.FIRE.defaultState
                ignorePositionUpdates()
            }

            onTick {
                if (!context.entity.hasStatusEffect(StatusEffects.STRENGTH)) {
                    destroy()
                }
            }

            addVirtualPassengerTo(context.entity)
        }
    }

    override fun onInitialize() {
        StatusEffectDecorators.register(StatusEffects.STRENGTH, TestStatusEffectDecorator)
    }
}