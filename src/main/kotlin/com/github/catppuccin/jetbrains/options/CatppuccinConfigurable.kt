package com.github.catppuccin.jetbrains.options

import com.github.catppuccin.jetbrains.generation.ThemeManager
import com.intellij.openapi.application.WriteAction
import com.intellij.openapi.options.SearchableConfigurable
import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.components.JBLabel
import java.awt.FlowLayout
import javax.swing.JComponent
import javax.swing.JPanel

class CatppuccinConfigurable : SearchableConfigurable {
  private var mainJComponent: JComponent? = null
  private var accentColorComboBox: ComboBox<AccentColor>? = null

  override fun createComponent(): JComponent? {
    if (mainJComponent == null) {
      mainJComponent = JPanel(FlowLayout(FlowLayout.LEFT)).apply {
        accentColorComboBox = ComboBox(AccentColor.values()).apply {
          selectedItem = state
        }
        add(JBLabel("Accent color"))
        add(accentColorComboBox)
      }
    }
    return mainJComponent
  }

  override fun isModified(): Boolean {
    return state != accentColorComboBox?.selectedItem
  }

  override fun apply() {
    state = accentColorComboBox?.selectedItem as AccentColor
    ThemeManager().generateAndWriteThemes()
  }

  override fun reset() {
    accentColorComboBox?.selectedItem = state
  }

  override fun disposeUIResources() {
    mainJComponent = null
  }

  override fun getId(): String = "com.github.catppuccin.jetbrains.options.CatppuccinConfigurable"

  override fun getDisplayName(): String = "Catppuccin"

  private var state: AccentColor
    get() = CatppuccinOptions.instance.state.accentColor
    set(value) {
      CatppuccinOptions.instance.state.accentColor = value
    }
}
