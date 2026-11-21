package at.petrak.paucal.xplat;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;

// Holds registered game-rule definition
public class PaucalGamerules {

  // Minecraft's resource identifiers must use lowercase sadly 
  public static final GameRule<Boolean> ALLOW_HEADPATS = 
    Registry.register(
      BuiltInRegistries.GAME_RULE,
      Identifier.fromNamespaceAndPath("paucal", "allow_headpats"), 
      new GameRule<>(
        GameRuleCategory.PLAYER,
        GameRuleType.BOOL,
        BoolArgumentType.bool(),
        GameRuleTypeVisitor::visitBoolean,
        Codec.BOOL,
        value -> value ? 1 : 0,
        true,
        FeatureFlagSet.of()
      )
    );

  public static void init() {
    // static init
  }
}
