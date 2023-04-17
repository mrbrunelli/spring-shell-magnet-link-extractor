package dev.mrbrunelli.magnetlink

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MagnetLinkApplication

fun main(args: Array<String>) {
	runApplication<MagnetLinkApplication>(*args)
}
