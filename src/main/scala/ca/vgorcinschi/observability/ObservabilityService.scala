package ca.vgorcinschi.observability

import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging

trait ObservabilityService {
  def observeDir(dirName: String, size: Long): Unit
}

final class ObservabilityServiceImpl extends ObservabilityService with LazyLogging {
  override def observeDir(dirName: String, size: Long): Unit = logger.info(s"The size of the $dirName is $size bytes")
}

final class NoOpObservabilityService extends ObservabilityService {
  override def observeDir(dirName: String, size: Long): Unit = {}
}

object ObservabilityService {
  def fromConfig(config: Config): ObservabilityService =
    if (config.getBoolean("observability.enabled")) new ObservabilityServiceImpl else new NoOpObservabilityService
}
