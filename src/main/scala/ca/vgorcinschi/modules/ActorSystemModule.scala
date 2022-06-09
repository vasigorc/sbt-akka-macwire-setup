package ca.vgorcinschi.modules

import akka.actor.ActorSystem

trait ActorSystemModule extends ConfigurationModule {
  lazy val actorSystem: ActorSystem = ActorSystem("sbt-doc-macwire-issue", config)
}
