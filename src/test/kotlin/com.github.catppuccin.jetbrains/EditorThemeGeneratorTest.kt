package com.github.catppuccin.jetbrains

import com.catppuccin.Palette
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EditorThemeGeneratorTest {
  private val writer = FakeFileWriter()
  private val options = ThemeOptions(Palette.FRAPPE.mauve)
  private val themeGenerator = EditorThemeGenerator(options, writer)

  private val frappeTheme = writer.readResource("/themes/frappe.xml")
  private val frappeThemeNoItalics =
    writer.readResource("/themes/frappe-no-italics.xml")

  private val latteTheme = writer.readResource("/themes/latte.xml")
  private val latteThemeNoItalics =
    writer.readResource("/themes/latte-no-italics.xml")

  private val macchiatoTheme = writer.readResource("/themes/macchiato.xml")
  private val macchiatoThemeNoItalics =
    writer.readResource("/themes/macchiato-no-italics.xml")

  private val mochaTheme = writer.readResource("/themes/mocha.xml")
  private val mochaThemeNoItalics =
    writer.readResource("/themes/mocha-no-italics.xml")

  private val allThemes = mapOf(
    "frappe.xml" to frappeTheme,
    "frappe-no-italics.xml" to frappeThemeNoItalics,
    "latte.xml" to latteTheme,
    "latte-no-italics.xml" to latteThemeNoItalics,
    "macchiato.xml" to macchiatoTheme,
    "macchiato-no-italics.xml" to macchiatoThemeNoItalics,
    "mocha.xml" to mochaTheme,
    "mocha-no-italics.xml" to mochaThemeNoItalics,
  )

  @BeforeEach
  fun resetWriter() {
    writer.reset()
  }

  @Test
  fun writeFrappe() {
    themeGenerator.generate(listOf(Palette.FRAPPE)).let(themeGenerator::write)

    assertEquals(2, writer.files().size)
    assertEquals(frappeTheme, writer.files()["frappe.xml"])
    assertEquals(
      frappeThemeNoItalics,
      writer.files()["frappe-no-italics.xml"]
    )
  }

  @Test
  fun writeAll() {
    themeGenerator.generate().let(themeGenerator::write)

    val files = writer.files()

    assertEquals(allThemes.size, files.size)

    allThemes.forEach { (name, content) ->
      assertEquals(content, files[name])
    }
  }
}
