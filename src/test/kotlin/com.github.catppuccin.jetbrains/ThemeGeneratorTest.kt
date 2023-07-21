package com.github.catppuccin.jetbrains

import com.catppuccin.Palette
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class FakeFileWriter : FileWriter {
  private val files = mutableListOf<String>()

  override fun write(name: String, data: Any): File {
    files.add(name)
    return File.createTempFile("test", null)
  }

  fun files(): List<String> = files

  fun reset() {
    files.clear()
  }
}

class ThemeGeneratorTest {
  private val writer = FakeFileWriter()
  private val themeGenerator = ThemeGenerator(writer)
  private val objectMapper = ObjectMapper().registerModule(KotlinModule())

  @BeforeEach
  fun resetWriter() {
    writer.reset()
  }

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

  @Test
  fun writesSingle() {
    val theme = themeGenerator.toTheme(Palette.FRAPPE)

    themeGenerator.writeThemes(listOf(theme))

    assertEquals(1, writer.files().size)
    assertEquals("frappe.theme.json", writer.files().first())
  }

  @Test
  fun writesAll() {
    val themes = themeGenerator.generateThemes()
    themeGenerator.writeThemes(themes)

    val expected = listOf(
      "frappe.theme.json",
      "latte.theme.json",
      "macchiato.theme.json",
      "mocha.theme.json",
    )

    assertEquals(expected.sorted(), writer.files().sorted())
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
