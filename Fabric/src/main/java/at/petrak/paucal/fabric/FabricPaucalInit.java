package at.petrak.paucal.fabric;

import at.petrak.paucal.xplat.PaucalMod;
import at.petrak.paucal.xplat.common.command.ModCommands;
import at.petrak.paucal.xplat.common.misc.NewWorldMessage;
import at.petrak.paucal.xplat.common.misc.PatPat;
import at.petrak.paucal.xplat.PaucalGamerules;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class FabricPaucalInit implements ModInitializer {
  @Override
  public void onInitialize() {
    Registry.register(
      BuiltInRegistries.GAME_RULE,
      Identifier.fromNamespaceAndPath("paucal", "allow_headpats"),
      PaucalGamerules.createAllowHeadpats()
    );
    PaucalMod.initialize();

    UseEntityCallback.EVENT.register(PatPat::onPat);
    CommandRegistrationCallback.EVENT.register((dp, _registry, _env) -> ModCommands.register(dp));
    ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
      if (entity instanceof Player player) {
        NewWorldMessage.onLogin(player);
      }
    });
  }
}
