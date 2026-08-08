package auxy.paintedplanks.datagen

import com.google.gson.JsonParser
import net.minecraft.data.CachedOutput
import net.minecraft.data.DataProvider
import java.io.File
import java.nio.file.Path
import java.util.concurrent.CompletableFuture

data class DittoJsonJob(
    val sourceFile: File,
    val destinationPath: Path,
    val target: String,
    val replacement: String
)

class DittoJsonBatch(
    private val name: String,
    private val jobs: List<DittoJsonJob>
) : DataProvider {
    override fun run(output: CachedOutput): CompletableFuture<*> {
        val futures = jobs.map { job ->
            if (!job.sourceFile.exists()) {
                throw IllegalArgumentException("Source file does not exist: ${job.sourceFile.absolutePath}")
            }

            val updatedContent = job.sourceFile.readText().replace(job.target, job.replacement)
            val json = JsonParser.parseString(updatedContent)
            DataProvider.saveStable(output, json, job.destinationPath)
        }

        return CompletableFuture.allOf(*futures.toTypedArray())
    }

    override fun getName(): String = name
}
