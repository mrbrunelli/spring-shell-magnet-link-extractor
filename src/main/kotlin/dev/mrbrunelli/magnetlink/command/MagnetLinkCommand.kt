package dev.mrbrunelli.magnetlink.command

import dev.mrbrunelli.magnetlink.client.MagnetLinkClient
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@ShellComponent
class MagnetLinkCommand(private val magnetLinkClient: MagnetLinkClient) {
    @ShellMethod(key = ["extract"], value = "Extract all magnet links from an html page")
    fun extract(@ShellOption arg: String): String {
        if (arg.isEmpty() || !arg.startsWith("http")) {
            throw RuntimeException("please provide a valid url")
        }
        val links = magnetLinkClient.extractLinks(arg)
        return links.joinToString(separator = "\n")
    }
}