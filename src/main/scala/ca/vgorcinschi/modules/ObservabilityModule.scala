package ca.vgorcinschi.modules

import akka.actor.ActorRef
import ca.vgorcinschi.observability.ObservabilityService
import ca.vgorcinschi.observability.actors.DirectorySizeScanner
import com.softwaremill.macwire.wireWith
import com.softwaremill.macwire.akkasupport.wireActor

trait ObservabilityModule extends ConfigurationModule with ActorSystemModule {
  lazy val observabilityService: ObservabilityService = wireWith(ObservabilityService.fromConfig _)

  lazy val directorySizeActor: ActorRef = wireActor[DirectorySizeScanner](DirectorySizeScanner.name)
}
