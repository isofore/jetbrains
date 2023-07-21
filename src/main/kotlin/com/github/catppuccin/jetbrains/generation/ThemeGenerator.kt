package com.github.catppuccin.jetbrains.generation

import com.catppuccin.Flavour
import com.catppuccin.Palette
import com.github.catppuccin.jetbrains.options.CatppuccinOptions

interface ThemeGenerator<T> {
  val options: CatppuccinOptions

  fun write(generateResult: List<T> = generate())
  fun generate(flavours: List<Flavour> = Palette.toList()): List<T>
}
