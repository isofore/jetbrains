package com.github.catppuccin.jetbrains

import com.catppuccin.Flavour
import com.catppuccin.Palette
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Options
import com.github.jknack.handlebars.Template
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import java.awt.Color

class EditorThemeGenerator(private val fileWriter: FileWriter = DefaultFileWriter("src/main/resources/themes")) {
  private val template: Template

  init {
    val loader = ClassPathTemplateLoader().apply {
      suffix = ".xml"
    }
    template = Handlebars(loader).apply {
      registerHelpers(HelperSource)
      setPrettyPrint(true)
    }.compile("template")
  }

  fun write(contexts: List<EditorThemeContext> = generate()) =
    contexts.forEach { context ->
      val output = template.apply(context)

      val suffix = if (context.italics) "" else "-no-italics"
      val fileName = "${context.key}$suffix.xml"
      fileWriter.write(fileName, output)
    }

  fun generate(flavours: List<Flavour> = Palette.toList()): List<EditorThemeContext> =
    flavours.cartesianProduct(listOf(true, false)).map { (flavour, italics) ->
      val isLatte = flavour == Palette.LATTE

      val hexValues = flavour.toList().associate {
        it.key to it.value.toHex().replace("#", "").lowercase()
      }

      EditorThemeContext(
        key = flavour.name,
        name = "Catppuccin ${flavour.name.capitalize()}${if (!italics) " (no italics)" else ""}",
        italics = italics,
        isLatte = isLatte,
        hexValues = hexValues,
      )
    }
}

data class EditorThemeContext(
  val key: String,
  val name: String,
  val italics: Boolean,
  val isLatte: Boolean,
  val parentScheme: String = if (isLatte) "Default" else "Darcula",
  val hexValues: Map<String, String>,
)


object HelperSource {
  @JvmStatic
  fun isLatte(lightColor: String, darkColor: String, options: Options): String {
    val context = options.context.model() as EditorThemeContext
    return if (context.isLatte) lightColor else darkColor
  }

  @JvmStatic
  fun opacity(color: String, opacity: Double, options: Options): String {
    val context = options.context.model() as EditorThemeContext
    val hexValues = context.hexValues

    val base = Color.decode("#${hexValues["base"]}") ?: throw IllegalArgumentException("Invalid hex value for base")
    val color = Color.decode("#$color") ?: throw IllegalArgumentException("Invalid hex value for color")

    return mixColors(color, base, opacity.toFloat())
      .toHex()
      .lowercase()
      .replace("#", "")
  }

  @JvmStatic
  fun mix(color1: String, color2: String, amount: Double): String {
    val color1 = Color.decode("#$color1")
    val color2 = Color.decode("#$color2")

    return mixColors(color1, color2, amount.toFloat())
      .toHex()
      .lowercase()
      .replace("#", "")
  }
}

fun <S, T> List<S>.cartesianProduct(other: List<T>): List<Pair<S, T>> = this.flatMap { first ->
  other.map { second ->
    first to second
  }
}


