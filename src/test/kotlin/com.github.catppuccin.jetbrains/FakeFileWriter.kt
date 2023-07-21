package com.github.catppuccin.jetbrains

import java.io.File

class FakeFileWriter : FileWriter {
  private val files = mutableMapOf<String, String>()

  override fun write(name: String, text: String): File {
    files[name] = text
    return File.createTempFile("test", null)
  }

  override fun write(name: String, data: Any): File {
    files[name] = ""
    return File.createTempFile("test", null)
  }

  fun files(): Map<String, String> = files

  fun reset() {
    files.clear()
  }

  fun readResource(name: String) =
    this.javaClass.getResourceAsStream(name)!!
      .bufferedReader()
      .readText()
      .replace(Regex("\n\\s+$", RegexOption.MULTILINE), "") // remove empty lines
}

