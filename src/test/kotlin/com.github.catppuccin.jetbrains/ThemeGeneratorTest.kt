package com.github.catppuccin.jetbrains

import com.catppuccin.Palette
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ThemeGeneratorTest {
  private val themeGenerator = ThemeGenerator("src/test/resources/themes")
  private val objectMapper = ObjectMapper().registerModule(KotlinModule())

  @Test
  fun frappe() {
    val frappe: JBTheme = objectMapper.readValue(readResource("/themes/frappe.theme.json"))
    val got = themeGenerator.toTheme(Palette.FRAPPE)
    assertThemes(frappe, got)
  }

  @Test
  fun latte() {
    val latte: JBTheme = objectMapper.readValue(readResource("/themes/latte.theme.json"))
    val got = themeGenerator.toTheme(Palette.LATTE)
    assertThemes(latte, got)
  }

  @Test
  fun macchiato() {
    val macchiato: JBTheme = objectMapper.readValue(readResource("/themes/macchiato.theme.json"))
    val got = themeGenerator.toTheme(Palette.MACCHIATO)
    assertThemes(macchiato, got)
  }

  @Test
  fun mocha() {
    val mocha: JBTheme = objectMapper.readValue(readResource("/themes/mocha.theme.json"))
    val got = themeGenerator.toTheme(Palette.MOCHA)
    assertThemes(mocha, got)
  }

  private fun assertThemes(expected: JBTheme, actual: JBTheme) {
    assertEquals(expected.name, actual.name)
    assertEquals(expected.author, actual.author)
    assertEquals(expected.editorScheme, actual.editorScheme)
    assertEquals(expected.colors, actual.colors)
    assertEquals(expected.ui, actual.ui)
    assertEquals(expected.icons, actual.icons)
  }

  private fun readResource(name: String) =
    this.javaClass.getResourceAsStream(name)!!.bufferedReader().readText()
}
