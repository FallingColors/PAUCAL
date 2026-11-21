package at.petrak.paucal.api.datagen;

import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.core.ClientAsset;
import net.minecraft.world.item.ItemStackTemplate;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * Post 1.19.4, the advancement provider is actually a thin wrapper around a list of these things.
 */
public abstract class PaucalAdvancementSubProvider implements AdvancementSubProvider {
  protected final String modid;

  protected PaucalAdvancementSubProvider(String modid) {
    this.modid = modid;
  }

  protected DisplayInfo simpleDisplay(ItemLike icon, String name, AdvancementType frameType) {
    return simpleDisplayWithBackground(icon, name, frameType, null);
  }

  protected DisplayInfo simpleDisplayWithBackground(ItemLike icon, String name, AdvancementType frameType,
      @Nullable Identifier background) {
    return display(new ItemStack(icon), name, frameType, background, true, true, false);
  }

  protected DisplayInfo display(ItemStack icon, String name, AdvancementType frameType, Identifier background,
      boolean showToast, boolean announceChat, boolean hidden) {
    String expandedName = "advancement." + this.modid + ":" + name;
    ItemStackTemplate iconTemplate = ItemStackTemplate.fromNonEmptyStack(icon);
    Optional<ClientAsset.ResourceTexture> backgroundTexture = Optional.ofNullable(background).map(id -> new ClientAsset.ResourceTexture(id, id));
    return new DisplayInfo(iconTemplate,
        Component.translatable(expandedName),
        Component.translatable(expandedName + ".desc"),
        backgroundTexture, frameType, showToast, announceChat, hidden);
  }

  protected String prefix(String name) {
    return this.modid + ":" + name;
  }

  protected Identifier modLoc(String name) {
    return Identifier.tryBuild(modid, name);
  }
}
