package ca.vgorcinschi.modules

import com.typesafe.config.{Config, ConfigFactory}

trait ConfigurationModule {
  lazy val config: Config = ConfigFactory.load().getConfig("default")
}
