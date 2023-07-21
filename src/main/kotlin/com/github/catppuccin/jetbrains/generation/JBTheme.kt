package com.github.catppuccin.jetbrains.generation

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue

@JsonInclude(JsonInclude.Include.NON_NULL)
data class JBTheme(
  @JsonIgnore val key: String = "",
  val name: String,
  val dark: Boolean,
  val additionalEditorSchemes: List<Any>? = null,
  val author: String,
  val editorScheme: String? = null,
  val background: BackgroundImage? = null,
  val colors: Map<String, String>? = null,
  val emptyFrameBackground: BackgroundImage? = null,
  val iconColorsOnSelection: Map<String, String>? = null,
  val icons: Icons? = null,
  val ui: Map<String, Any>,
)

data class BackgroundImage(
  val author: BackgroundImageAnchor? = null,
  val fill: BackgroundFillingOptions? = null,
  val image: String,
  val transparency: Int? = null
)

enum class BackgroundImageAnchor(@JsonValue val value: String) {
  BOTTOM_CENTER("bottom_center"),
  BOTTOM_LEFT("bottom_left"),
  BOTTOM_RIGHT("bottom_right"),
  CENTER("center"),
  MIDDLE_LEFT("middle_left"),
  MIDDLE_RIGHT("middle_right"),
  TOP_CENTER("top_center"),
  TOP_LEFT("top_left"),
  TOP_RIGHT("top_right"),
}

enum class BackgroundFillingOptions(@JsonValue val value: String) {
  PLAIN("plain"),
  SCALE("scale"),
  TILE("tile"),
}

data class Icons(@JsonProperty("ColorPalette") val colorPalette: ColorPalette?)

data class ColorPalette(
  val deuteranopia: ColorPaletteBase? = null,
  val protanopia: ColorPaletteBase? = null,
  @JsonProperty("Actions.Blue") val actionsBlue: String? = null,
  @JsonProperty("Actions.Green") val actionsGreen: String? = null,
  @JsonProperty("Actions.Grey") val actionsGrey: String? = null,
  @JsonProperty("Actions.GreyInline") val actionsGreyInline: String? = null,
  @JsonProperty("Actions.GreyInline.Dark") val actionsGreyInlineDark: String? = null,
  @JsonProperty("Actions.Red") val actionsRed: String? = null,
  @JsonProperty("Actions.Yellow") val actionsYellow: String? = null,
  @JsonProperty("Checkbox.Background.Default") val checkboxBackgroundDefault: String? = null,
  @JsonProperty("Checkbox.Background.Default.Dark") val checkboxBackgroundDefaultDark: String? = null,
  @JsonProperty("Checkbox.Background.Disabled") val checkboxBackgroundDisabled: String? = null,
  @JsonProperty("Checkbox.Background.Disabled.Dark") val checkboxBackgroundDisabledDark: String? = null,
  @JsonProperty("Checkbox.Background.Selected") val checkboxBackgroundSelected: String? = null,
  @JsonProperty("Checkbox.Background.Selected.Dark") val checkboxBackgroundSelectedDark: String? = null,
  @JsonProperty("Checkbox.Border.Default") val checkboxBorderDefault: String? = null,
  @JsonProperty("Checkbox.Border.Default.Dark") val checkboxBorderDefaultDark: String? = null,
  @JsonProperty("Checkbox.Border.Disabled") val checkboxBorderDisabled: String? = null,
  @JsonProperty("Checkbox.Border.Disabled.Dark") val checkboxBorderDisabledDark: String? = null,
  @JsonProperty("Checkbox.Border.Selected") val checkboxBorderSelected: String? = null,
  @JsonProperty("Checkbox.Border.Selected.Dark") val checkboxBorderSelectedDark: String? = null,
  @JsonProperty("Checkbox.Focus.Thin.Default") val checkboxFocusThinDefault: String? = null,
  @JsonProperty("Checkbox.Focus.Thin.Default.Dark") val checkboxFocusThinDefaultDark: String? = null,
  @JsonProperty("Checkbox.Focus.Thin.Selected") val checkboxFocusThinSelected: String? = null,
  @JsonProperty("Checkbox.Focus.Thin.Selected.Dark") val checkboxFocusThinSelectedDark: String? = null,
  @JsonProperty("Checkbox.Focus.Wide") val checkboxFocusWide: String? = null,
  @JsonProperty("Checkbox.Focus.Wide.Dark") val checkboxFocusWideDark: String? = null,
  @JsonProperty("Checkbox.Foreground.Disabled") val checkboxForegroundDisabled: String? = null,
  @JsonProperty("Checkbox.Foreground.Disabled.Dark") val checkboxForegroundDisabledDark: String? = null,
  @JsonProperty("Checkbox.Foreground.Selected") val checkboxForegroundSelected: String? = null,
  @JsonProperty("Checkbox.Foreground.Selected.Dark") val checkboxForegroundSelectedDark: String? = null,
  @JsonProperty("Objects.BlackText") val objectsBlackText: String? = null,
  @JsonProperty("Objects.Blue") val objectsBlue: String? = null,
  @JsonProperty("Objects.Green") val objectsGreen: String? = null,
  @JsonProperty("Objects.Grey") val objectsGrey: String? = null,
  @JsonProperty("Objects.Pink") val objectsPink: String? = null,
  @JsonProperty("Objects.Purple") val objectsPurple: String? = null,
  @JsonProperty("Objects.Red") val objectsRed: String? = null,
  @JsonProperty("Objects.RedStatus") val objectsRedStatus: String? = null,
  @JsonProperty("Objects.Yellow") val objectsYellow: String? = null,
  @JsonProperty("Objects.YellowDark") val objectsYellowDark: String? = null,
  @JsonProperty("Tree.iconColor") val treeIconColor: String? = null,
)

data class ColorPaletteBase(
  @JsonProperty("Actions.Blue") val actionsBlue: String? = null,
  @JsonProperty("Actions.Green") val actionsGreen: String? = null,
  @JsonProperty("Actions.Grey") val actionsGrey: String? = null,
  @JsonProperty("Actions.GreyInline") val actionsGreyInline: String? = null,
  @JsonProperty("Actions.GreyInline.Dark") val actionsGreyInlineDark: String? = null,
  @JsonProperty("Actions.Red") val actionsRed: String? = null,
  @JsonProperty("Actions.Yellow") val actionsYellow: String? = null,
  @JsonProperty("Checkbox.Background.Default") val checkboxBackgroundDefault: String? = null,
  @JsonProperty("Checkbox.Background.Default.Dark") val checkboxBackgroundDefaultDark: String? = null,
  @JsonProperty("Checkbox.Background.Disabled") val checkboxBackgroundDisabled: String? = null,
  @JsonProperty("Checkbox.Background.Disabled.Dark") val checkboxBackgroundDisabledDark: String? = null,
  @JsonProperty("Checkbox.Background.Selected") val checkboxBackgroundSelected: String? = null,
  @JsonProperty("Checkbox.Background.Selected.Dark") val checkboxBackgroundSelectedDark: String? = null,
  @JsonProperty("Checkbox.Border.Default") val checkboxBorderDefault: String? = null,
  @JsonProperty("Checkbox.Border.Default.Dark") val checkboxBorderDefaultDark: String? = null,
  @JsonProperty("Checkbox.Border.Disabled") val checkboxBorderDisabled: String? = null,
  @JsonProperty("Checkbox.Border.Disabled.Dark") val checkboxBorderDisabledDark: String? = null,
  @JsonProperty("Checkbox.Border.Selected") val checkboxBorderSelected: String? = null,
  @JsonProperty("Checkbox.Border.Selected.Dark") val checkboxBorderSelectedDark: String? = null,
  @JsonProperty("Checkbox.Focus.Thin.Default") val checkboxFocusThinDefault: String? = null,
  @JsonProperty("Checkbox.Focus.Thin.Default.Dark") val checkboxFocusThinDefaultDark: String? = null,
  @JsonProperty("Checkbox.Focus.Thin.Selected") val checkboxFocusThinSelected: String? = null,
  @JsonProperty("Checkbox.Focus.Thin.Selected.Dark") val checkboxFocusThinSelectedDark: String? = null,
  @JsonProperty("Checkbox.Focus.Wide") val checkboxFocusWide: String? = null,
  @JsonProperty("Checkbox.Focus.Wide.Dark") val checkboxFocusWideDark: String? = null,
  @JsonProperty("Checkbox.Foreground.Disabled") val checkboxForegroundDisabled: String? = null,
  @JsonProperty("Checkbox.Foreground.Disabled.Dark") val checkboxForegroundDisabledDark: String? = null,
  @JsonProperty("Checkbox.Foreground.Selected") val checkboxForegroundSelected: String? = null,
  @JsonProperty("Checkbox.Foreground.Selected.Dark") val checkboxForegroundSelectedDark: String? = null,
  @JsonProperty("Objects.BlackText") val objectsBlackText: String? = null,
  @JsonProperty("Objects.Blue") val objectsBlue: String? = null,
  @JsonProperty("Objects.Green") val objectsGreen: String? = null,
  @JsonProperty("Objects.Grey") val objectsGrey: String? = null,
  @JsonProperty("Objects.Pink") val objectsPink: String? = null,
  @JsonProperty("Objects.Purple") val objectsPurple: String? = null,
  @JsonProperty("Objects.Red") val objectsRed: String? = null,
  @JsonProperty("Objects.RedStatus") val objectsRedStatus: String? = null,
  @JsonProperty("Objects.Yellow") val objectsYellow: String? = null,
  @JsonProperty("Objects.YellowDark") val objectsYellowDark: String? = null,
  @JsonProperty("Tree.iconColor") val treeIconColor: String? = null,
)

data class RunWidget(
  val separatorColor: String,
  val foreground: String,
  val background: String
)
