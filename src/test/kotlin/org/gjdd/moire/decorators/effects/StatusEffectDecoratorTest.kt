package org.gjdd.moire.decorators.effects

import net.fabricmc.api.ModInitializer
import net.minecraft.block.Blocks
import net.minecraft.entity.effect.StatusEffects
import org.gjdd.moire.elements.*

class StatusEffectDecoratorTest : ModInitializer {
    private val testStatusEffectDecorator = statusEffectDecorator {
        elementHolder {
            blockDisplayElement {
                transformation {
                    translateLocal(-0.5f, -0.5f, -0.5f)
                }

                blockState = Blocks.FIRE.defaultState
                ignorePositionUpdates()
            }

            onTick {
                if (!entity.hasStatusEffect(StatusEffects.STRENGTH)) {
                    destroy()
                }
            }

            addVirtualPassengerTo(entity)
        }
    }

    override fun onInitialize() {
        StatusEffectDecorators.register(StatusEffects.STRENGTH, testStatusEffectDecorator)
    }
}