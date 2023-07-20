package com.github.catppuccin.jetbrains

import java.awt.Color

fun mixColors(color1: Color, color2: Color, amount: Float = 0.5f): Color {
  val rgb1 = color1.getRGBColorComponents(null)
  val rgb2 = color2.getRGBColorComponents(null)

  return rgb1.zip(rgb2)
    .map { (x, y) -> x * amount + (y * (1 - amount)) }
    .let { Color(it[0], it[1], it[2]) }
}
