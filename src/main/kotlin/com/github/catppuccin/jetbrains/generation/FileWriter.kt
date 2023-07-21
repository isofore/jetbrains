package com.github.catppuccin.jetbrains.generation

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.net.URI
import java.nio.file.Files
import java.nio.file.Path

interface FileWriter {
  fun write(name: String, text: String): File
  fun writeAny(name: String, data: Any): File
}

class DefaultFileWriter(
  private val dirPath: URI = object {}::class.java.getResource("/themes")?.toURI()
    ?: URI("src/main/resources/themes")
) : FileWriter {
  private val objectMapper = ObjectMapper()

  private val parentDir: File

  init {
    parentDir = ensureParentDirExists()
  }

  override fun writeAny(name: String, data: Any): File =
    File(parentDir, name).let { file ->
      objectMapper.writeValue(file, data)
      file
    }

  override fun write(name: String, text: String): File = File(parentDir, name).let { file ->
    file.writeText(text)
    file
  }

  private fun ensureParentDirExists(): File {
    Files.createDirectories(Path.of(dirPath))
    val parentDir = File(dirPath)
    require(File(dirPath).exists())
    require(File(dirPath).isDirectory)
    return parentDir
  }
}
