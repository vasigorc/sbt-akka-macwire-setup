package ca.vgorcinschi

import ca.vgorcinschi.modules.ObservabilityModule
import ca.vgorcinschi.observability.actors.DirectorySizeScanner.StartScanning

object Main extends App with ObservabilityModule {

  directorySizeActor ! StartScanning
}
