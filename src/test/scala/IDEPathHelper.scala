import java.io.File
import java.nio.file.Path

object IDEPathHelper {

	val mavenBinariesDirectory = System.getProperty("java.class.path")
		.split(File.pathSeparator)
		.collectFirst { case cpe if cpe.endsWith("test-classes") => Path.of(cpe) }
		.getOrElse(throw new IllegalStateException("Couldn't locate test-classes"))

	private val mavenTargetDirectory = mavenBinariesDirectory.getParent
	private val projectRootDir = mavenTargetDirectory.getParent
	private val mavenSrcTestDirectory = projectRootDir.resolve("src").resolve("test")

	val mavenSourcesDirectory = mavenSrcTestDirectory.resolve("scala")
	val mavenResourcesDirectory = mavenSrcTestDirectory.resolve("resources")
	val resultsDirectory = mavenTargetDirectory.resolve("gatling")
	val recorderConfigFile = mavenResourcesDirectory.resolve("recorder.conf")
}
