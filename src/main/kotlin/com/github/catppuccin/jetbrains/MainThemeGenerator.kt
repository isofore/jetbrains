package com.github.catppuccin.jetbrains

import com.catppuccin.Flavour
import com.catppuccin.Palette
import java.awt.Color

class MainThemeGenerator(
  override val options: ThemeOptions,
  private val fileWriter: FileWriter = DefaultFileWriter("src/main/resources/themes")
) : ThemeGenerator<JBTheme> {
  override fun write(generateResult: List<JBTheme>) = generateResult.forEach { (key, theme) ->
    fileWriter.write("$key.theme.json", theme)
  }

  override fun generate(flavours: List<Flavour>): List<JBTheme> =
    flavours.map(::toTheme)

  fun toTheme(flavour: Flavour): JBTheme {
    val isLatte = flavour == Palette.LATTE

    val colors: Map<String, String> = flavour.toList().associate { it.key to it.value.toHex() }

    return JBTheme(
      key = flavour.name,
      name = "Catppuccin ${flavour.name.capitalize()}",
      dark = !isLatte,
      author = "Catppuccin Org <releases@catppuccin.com>",
      editorScheme = "/themes/${flavour.name}.xml",
      colors = colors + mapOf(
        "accentColor" to flavour.mauve.toHex(),
        "secondaryAccentColor" to flavour.yellow.toHex(),
        "primaryForeground" to flavour.text.toHex(),
        "primaryBackground" to flavour.base.toHex(),
        "secondaryBackground" to flavour.surface0.toHex(),
        "hoverBackground" to flavour.surface1.toHex(),
        "selectionBackground" to flavour.surface1.toHex(),
        "selectionInactiveBackground" to flavour.base.toHex(),
        "borderColor" to flavour.surface1.toHex(),
        "separatorColor" to flavour.surface1.toHex(),
      ),
      ui = mapOf(
        "*" to mapOf(
          "arc" to "7",
          "background" to "primaryBackground",
          "selectionForeground" to "primaryForeground",
          "selectionInactiveForeground" to "primaryForeground",
          "selectionBackground" to "selectionBackground",
          "selectionInactiveBackground" to "selectionInactiveBackground",
          "inactiveBackground" to "primaryBackground",
          "disabledBackground" to "primaryBackground",
          "borderColor" to "primaryBackground",
          "separatorColor" to "separatorColor",
        ),
        "List" to mapOf(
          "background" to "mantle",
        ),
        "Borders" to mapOf(
          "color" to "primaryBackground",
          "ContrastBorderColor" to "secondaryBackground",
        ),
        "ActionButton" to mapOf(
          "hoverBackground" to "hoverBackground",
          "hoverBorderColor" to "hoverBackground",
          "pressedBackground" to "hoverBackground",
          "pressedBorderColor" to "hoverBackground",
        ),
        "Bookmark" to mapOf(
          "iconBackground" to "accentColor",
          "Mnemonic" to mapOf(
            "iconForeground" to "primaryForeground",
            "iconBackground" to "secondaryBackground",
            "iconBorderColor" to "accentColor",
          ),
          "MnemonicAssigned" to mapOf(
            "foreground" to "primaryForeground",
            "background" to "secondaryBackground",
            "borderColor" to "secondaryAccentColor",
          ),
          "MnemonicAvailable" to emptyMap<String, String>(),
          "MnemonicCurrent" to mapOf(
            "foreground" to "primaryForeground",
            "background" to "selectionBackground",
            "borderColor" to "accentColor",
          ),
        ),
        "Button" to mapOf(
          "foreground" to "primaryForeground",
          "startBorderColor" to "secondaryBackground",
          "endBorderColor" to "secondaryBackground",
          "startBackground" to "secondaryBackground",
          "endBackground" to "secondaryBackground",
          "focusedBorderColor" to "secondaryBackground",
          "disabledBorderColor" to "primaryBackground",
          "default" to mapOf(
            "foreground" to "surface1",
            "startBackground" to "accentColor",
            "endBackground" to "accentColor",
            "startBorderColor" to "accentColor",
            "endBorderColor" to "accentColor",
            "focusColor" to "accentColor",
            "focusedBorderColor" to "surface1",
          ),
        ),
        "Counter" to mapOf(
          "foreground" to "primaryBackground",
          "background" to "accentColor",
        ),
        "ComboBox" to mapOf(
          "modifiedItemForeground" to "accentColor",
          "ArrowButton" to mapOf(
            "background" to "secondaryBackground",
            "nonEditableBackground" to "secondaryBackground",
            "disabledIconColor" to "lavender",
            "iconColor" to "accentColor",
          ),
          "selectionBackground" to "selectionBackground",
          "nonEditableBackground" to "secondaryBackground",
        ),
        "CompletionPopup" to mapOf(
          "selectionBackground" to "selectionBackground",
          "selectionInactiveBackground" to "selectionBackground",
          "matchForeground" to "flamingo",
        ),
        "Component" to mapOf(
          "focusColor" to "accentColor",
          "borderColor" to "primaryBackground",
          "focusedBorderColor" to "selectionBackground",
          "disabledBorderColor" to "selectionBackground",
          "errorFocusColor" to "red",
          "inactiveErrorFocusColor" to "red",
          "warningFocusColor" to "yellow",
          "inactiveWarningFocusColor" to "yellow",
        ),
        "RunWidget" to mapOf(
          "separatorColor" to "separatorColor",
          "foreground" to "text",
          "background" to "secondaryBackground"
        ),
        "DragAndDrop" to mapOf(
          "borderColor" to "selectionBackground",
        ),
        "Editor" to mapOf(
          "background" to "primaryBackground",
          "shortcutForeground" to "accentColor",
        ),
        "EditorTabs" to mapOf(
          "background" to "primaryBackground",
          "underlinedTabBackground" to "secondaryBackground",
          "underlineColor" to "accentColor",
          "underlineHeight" to 1,
          "hoverBackground" to "surface0",
          "inactiveUnderlineColor" to "accentColor",
        ),
        "FileColor" to mapOf(
          "Blue" to fileColor(isLatte, flavour.base, flavour.blue),
          "Green" to fileColor(isLatte, flavour.base, flavour.green),
          "Orange" to fileColor(isLatte, flavour.base, flavour.peach),
          "Yellow" to fileColor(isLatte, flavour.base, flavour.yellow),
          "Rose" to fileColor(isLatte, flavour.base, flavour.red),
          "Violet" to fileColor(isLatte, flavour.base, flavour.lavender),
        ),
        "Link" to mapOf(
          "activeForeground" to "accentColor",
          "hoverForeground" to "accentColor",
          "visitedForeground" to "secondaryAccentColor",
          "pressedForeground" to "secondaryAccentColor",
        ),
        "MainToolbar" to mapOf(
          "background" to "primaryBackground",
          "inactiveBackground" to "primaryBackground",
          "Dropdown" to mapOf(
            "hoverBackground" to "hoverBackground",
            "pressedBackground" to "hoverBackground",
          ),
          "Icon" to mapOf(
            "hoverBackground" to "hoverBackground",
            "pressedBackground" to "hoverBackground",
          ),
        ),
        "MemoryIndicator" to mapOf(
          "allocatedBackground" to "surface0",
          "usedBackground" to "surface1",
        ),
        "NotificationsToolwindow" to mapOf(
          "newNotification" to mapOf(
            "background" to "primaryBackground",
            "hoverBackground" to "hoverBackground",
          ),
        ),
        "Notification" to mapOf(
          "background" to "primaryBackground",
          "borderColor" to "mauve",
          "errorBorderColor" to "red",
          "errorBackground" to "primaryBackground",
          "errorForeground" to "primaryForeground",
          "ToolWindow" to mapOf(
            "warningForeground" to "primaryForeground",
            "warningBackground" to "primaryBackground",
            "warningBorderColor" to "peach",
            "errorForeground" to "primaryForeground",
            "errorBorderColor" to "red",
            "errorBackground" to "primaryBackground",
            "informativeForeground" to "primaryForeground",
            "informativeBackground" to "primaryBackground",
            "informativeBorderColor" to "secondaryAccentColor",
          ),
        ),
        "PasswordField" to mapOf(
          "background" to "secondaryBackground",
        ),
        "Plugins" to mapOf(
          "SearchField" to mapOf(
            "background" to "secondaryBackground",
          ),
          "SectionHeader" to mapOf(
            "foreground" to "primaryForeground",
          ),
          "hoverBackground" to "hoverBackground",
          "lightSelectionBackground" to "hoverBackground",
          "Button" to mapOf(
            "installBorderColor" to "secondaryAccentColor",
            "installForeground" to "secondaryAccentColor",
            "installBackground" to "primaryBackground",
            "installFillForeground" to "primaryBackground",
            "installFillBackground" to "secondaryAccentColor",
            "installFocusedBackground" to "primaryBackground",
            "updateBorderColor" to "accentColor",
            "updateForeground" to "primaryBackground",
            "updateBackground" to "accentColor",
          ),
          "Tab" to mapOf(
            "selectedBackground" to "hoverBackground",
            "selectedForeground" to "primaryForeground",
            "hoverBackground" to "hoverBackground",
          ),
        ),
        "ProgressBar" to mapOf(
          "failedEndColor" to "maroon",
          "failedColor" to "red",
          "trackColor" to "selectionBackground",
          "progressColor" to "accentColor",
          "indeterminateStartColor" to "accentColor",
          "indeterminateEndColor" to "secondaryAccentColor",
          "passedEndColor" to "teal",
          "passedColor" to "green",
        ),
        "Popup" to mapOf(
          "borderColor" to "mauve",
          "Header" to mapOf(
            "activeBackground" to "secondaryBackground",
            "inactiveBackground" to "secondaryBackground",
          ),
        ),
        "ScrollBar" to mapOf(
          "Mac" to mapOf(
            "hoverThumbColor" to "secondaryAccentColor",
            "Transparent" to mapOf(
              "hoverThumbColor" to "secondaryAccentColor",
            ),
          ),
        ),
        "SearchEverywhere" to mapOf(
          "SearchField" to mapOf(
            "background" to "secondaryBackground",
          ),
          "Tab" to mapOf(
            "selectedBackground" to "secondaryBackground",
            "selectedForeground" to "primaryForeground",
          ),
        ),
        "SearchMatch" to mapOf(
          "startBackground" to "accentColor",
          "endBackground" to "accentColor",
        ),
        "Separator" to mapOf(
          "separatorColor" to "separatorColor",
        ),
        "SidePanel" to mapOf(
          "background" to "mantle",
        ),
        "StatusBar" to mapOf(
          "borderColor" to "borderColor",
          "hoverBackground" to "hoverBackground",
        ),
        "TabbedPane" to mapOf(
          "tabSelectionHeight" to 1,
          "focusColor" to "hoverBackground",
          "hoverColor" to "hoverBackground",
          "underlineColor" to "accentColor",
          "contentAreaColor" to "primaryBackground",
        ),
        "Table" to mapOf(
          "gridColor" to "hoverBackground",
          "hoverBackground" to "selectionBackground",
          "lightSelectionBackground" to "secondaryBackground",
        ),
        "TableHeader" to mapOf(
          "bottomSeparatorColor" to "primaryBackground",
        ),
        "TextField" to mapOf(
          "background" to "secondaryBackground",
        ),
        "TextArea" to mapOf(
          "background" to "mantle",
        ),
        "ToggleButton" to mapOf(
          "onBackground" to "green",
          "onForeground" to "hoverBackground",
          "offBackground" to "selectionBackground",
          "offForeground" to "hoverBackground",
          "buttonColor" to "primaryForeground",
        ),
        "ToolBar" to mapOf(
          "background" to "primaryBackground",
          "borderHandleColor" to "secondaryAccentColor",
        ),
        "ToolWindow" to mapOf(
          "background" to "mantle",
          "Button" to mapOf(
            "hoverBackground" to "hoverBackground",
            "selectedBackground" to "hoverBackground",
          ),
          "Header" to mapOf(
            "background" to "primaryBackground",
            "inactiveBackground" to "primaryBackground",
            "borderColor" to "secondaryBackground",
          ),
          "HeaderTab" to mapOf(
            "underlineColor" to "pink",
            "inactiveUnderlineColor" to "text",
            "underlineHeight" to 1,
            "underlinedTabBackground" to "surface1",
            "selectedInactiveBackground" to "base",
            "hoverBackground" to "hoverBackground",
          ),
        ),
        "Tree" to mapOf(
          "rowHeight" to 24,
          "background" to "mantle",
          "modifiedItemForeground" to "accentColor",
          "hoverBackground" to "secondaryBackground",
          "selectionBackground" to "selectionBackground",
          "selectionInactiveBackground" to "selectionInactiveBackground",
        ),
        "ValidationTooltip" to mapOf(
          "errorBackground" to "mantle",
          "errorBorderColor" to "red",
          "warningBackground" to "mantle",
          "warningBorderColor" to "yellow",
        ),
        "VersionControl" to mapOf(
          "FileHistory" to mapOf(
            "Commit" to mapOf(
              "selectedBranchBackground" to "secondaryBackground",
            ),
          ),
          "GitLog" to mapOf(
            "headIconColor" to "yellow",
            "localBranchIconColor" to "green",
            "remoteBranchIconColor" to "secondaryAccentColor",
            "tagIconColor" to "accentColor",
            "otherIconColor" to "teal",
          ),
          "Log" to mapOf(
            "Commit" to mapOf(
              "hoveredBackground" to "selectionBackground",
              "currentBranchBackground" to "secondaryBackground",
            ),
          ),
          "RefLabel" to mapOf(
            "foreground" to "primaryForeground",
          ),
        ),
        "WelcomeScreen" to mapOf(
          "SidePanel" to mapOf(
            "background" to "secondaryBackground",
          ),
          "separatorColor" to "separatorColor",
          "Projects" to mapOf(
            "background" to "hoverBackground",
            "selectionBackground" to "secondaryBackground",
            "selectionInactiveBackground" to "secondaryBackground",
            "actions" to mapOf(
              "background" to "hoverBackground",
            ),
          ),
        ),
      ),
      icons = Icons(
        ColorPalette(
          actionsBlue = flavour.blue.toHex(),
          actionsGreen = flavour.green.toHex(),
          actionsRed = flavour.red.toHex(),
          actionsYellow = flavour.yellow.toHex(),
          actionsGrey = flavour.overlay0.toHex(),
          actionsGreyInlineDark = flavour.blue.toHex(),
          actionsGreyInline = flavour.blue.toHex(),
          objectsBlue = flavour.blue.toHex(),
          objectsGreen = flavour.green.toHex(),
          objectsGrey = flavour.overlay0.toHex(),
          objectsPink = flavour.pink.toHex(),
          objectsPurple = flavour.mauve.toHex(),
          objectsRed = flavour.red.toHex(),
          objectsRedStatus = flavour.red.toHex(),
          objectsYellow = flavour.yellow.toHex(),
          objectsYellowDark = flavour.flamingo.toHex(),
          objectsBlackText = flavour.surface0.toHex(),
          treeIconColor = flavour.blue.toHex(),
          checkboxBackgroundDefault = flavour.surface1.toHex(),
          checkboxBackgroundSelected = flavour.surface1.toHex(),
          checkboxBackgroundDisabled = flavour.surface0.toHex(),
          checkboxForegroundSelected = flavour.mauve.toHex(),
          checkboxForegroundDisabled = flavour.overlay0.toHex(),
          checkboxBorderDefault = flavour.surface0.toHex(),
          checkboxBorderSelected = flavour.surface0.toHex(),
          checkboxBorderDisabled = flavour.surface0.toHex(),
        )
      )
    )
  }

  private fun fileColor(
    isLatte: Boolean,
    base: Color,
    color: Color,
    latteOpacity: Float = 0.2f,
    nonLatteOpacity: Float = 0.15f
  ): String {
    val opacityAmount = if (isLatte) latteOpacity else nonLatteOpacity
    return opacity(base, color, opacityAmount).toHex().lowercase().replace("#", "")
  }

  private fun opacity(base: Color, color: Color, amount: Float): Color =
    mixColors(color, base, amount)
}
