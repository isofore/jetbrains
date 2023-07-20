package com.github.catppuccin.jetbrains

import org.junit.jupiter.api.Test
import java.awt.Color
import kotlin.test.assertEquals

// https://github.com/scientific-dev/colormath.js/blob/668a359b0208b0748c45701e44970f1e9c6e3138/src/methods.ts#L96

class ColorMixTest {

  val color1 = Color(189, 30, 30)
  val color2 = Color(30, 30, 189)
  val color3 = Color.decode("#e01616")
  val color4 = Color.decode("#6d1e6d")

  @Test
  fun mix12() {
    assertEquals(Color(110, 30, 110), mixColors(color1, color2))
  }

  @Test
  fun mix12CustomAmount() {
    assertEquals(Color(149, 30, 70), mixColors(color1, color2, 0.75f))
  }

  @Test
  fun mix12CustomAmount2() {
    assertEquals(Color(189, 30, 30), mixColors(color1, color2, 1.0f))
  }

  @Test
  fun mix34() {
    assertEquals(Color.decode("#a71a42"), mixColors(color3, color4))
  }

  @Test
  fun mix34CustomAmount() {
    assertEquals(Color.decode("#c3182c"), mixColors(color3, color4, 0.75f))
  }

  @Test
  fun mix34CustomAmount2() {
    assertEquals(Color.decode("#e01616"), mixColors(color3, color4, 1.0f))
  }
}
