package com.github.catppuccin.jetbrains.generation

import com.intellij.openapi.application.WriteAction

class ThemeManager(
  private val mainThemeGenerator: MainThemeGenerator = MainThemeGenerator(),
  private val editorThemeGenerator: EditorThemeGenerator = EditorThemeGenerator(),
) {
  fun generateAndWriteThemes() {
    WriteAction.run<Exception> {
      mainThemeGenerator.write()
      editorThemeGenerator.write()
    }
  }
}
