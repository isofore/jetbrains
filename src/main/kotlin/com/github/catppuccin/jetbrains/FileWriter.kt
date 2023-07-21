package com.github.catppuccin.jetbrains

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

interface FileWriter {
  fun write(name: String, data: Any): File
}

class DefaultFileWriter(private val dirPath: String) : FileWriter {
  private val objectMapper = ObjectMapper()

  private val parentDir: File

  init {
    parentDir = ensureParentDirExists()
  }

  override fun write(name: String, data: Any): File =
    File(dirPath, name).let { file ->
      objectMapper.writeValue(file, data)
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
