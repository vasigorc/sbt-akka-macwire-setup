package ca.vgorcinschi.observability.actors

import akka.actor.{Actor, ActorLogging, Timers}
import akka.pattern.pipe
import ca.vgorcinschi.observability.ObservabilityService
import com.typesafe.config.Config
import org.apache.commons.io.FileUtils

import java.io.File
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.concurrent.duration._
import scala.util.control.NonFatal

class DirectorySizeScanner(config: Config, observabilityService: ObservabilityService)
    extends Actor
    with ActorLogging
    with Timers {

  import ca.vgorcinschi.observability.actors.DirectorySizeScanner._

  private val directoryName: String = config.getString("directory-to-scan")
  private implicit val ec: ExecutionContextExecutor = context.dispatcher

  override def receive: Receive = {
    case StartScanning =>
      timers.startTimerAtFixedRate("ScanDirectory", GetDirectorySize, 1.minute)
    case GetDirectorySize =>
      Future {
        val snapshotsDirectory = new File(directoryName)
        val size = FileUtils.sizeOfDirectory(snapshotsDirectory)
        DirectorySizeScanned(size)
      }.recover { case NonFatal(e) =>
        DirectorySizeFailed(e.getLocalizedMessage)
      }.pipeTo(self)
    case DirectorySizeScanned(size)  => observabilityService.observeDir(directoryName, size)
    case DirectorySizeFailed(reason) => log.error(s"Failed to scan/size the directory: $reason")
  }
}

object DirectorySizeScanner {
  val name: String = this.getClass.getSimpleName
  // commands
  sealed trait DirectorySizeScannerCommand
  final case object StartScanning extends DirectorySizeScannerCommand
  final case object GetDirectorySize extends DirectorySizeScannerCommand

  // events
  sealed trait DirectorySizeScannerEvent
  final case class DirectorySizeScanned(size: Long) extends DirectorySizeScannerEvent
  final case class DirectorySizeFailed(reason: String) extends DirectorySizeScannerEvent
}
