package com.github.catppuccin.jetbrains.options

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil.copyBean

@State(
  name = "com.github.catppuccin.jetbrains.options.CatppuccinOptions",
  storages = [Storage("CatppuccinOptions.xml")]
)
class CatppuccinOptions : PersistentStateComponent<CatppuccinOptions> {

  var accentColor: AccentColor = AccentColor.DEFAULT

  override fun getState(): CatppuccinOptions = this

  override fun loadState(state: CatppuccinOptions) {
    copyBean(state, this)
  }

  companion object {
    val instance: CatppuccinOptions
      get() =
        ApplicationManager.getApplication().getService(CatppuccinOptions::class.java)
  }
}

enum class AccentColor(val value: String) {
  ROSEWATER("rosewater"),
  FLAMINGO("flamingo"),
  PINK("pink"),
  MAUVE("mauve"),
  RED("red"),
  MAROON("maroon"),
  PEACH("peach"),
  YELLOW("yellow"),
  GREEN("green"),
  TEAL("teal"),
  SKY("sky"),
  SAPPHIRE("sapphire"),
  BLUE("blue"),
  LAVENDER("lavender");

  companion object {
    val DEFAULT = MAUVE
  }
}
