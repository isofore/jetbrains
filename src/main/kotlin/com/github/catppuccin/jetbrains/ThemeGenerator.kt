package com.github.catppuccin.jetbrains

import com.catppuccin.Flavour
import com.catppuccin.Palette
import java.awt.Color

interface ThemeGenerator<T> {
  val options: ThemeOptions

  fun write(generateResult: List<T> = generate())
  fun generate(flavours: List<Flavour> = Palette.toList()): List<T>
}

data class ThemeOptions(
  val accentColor: Color
)
