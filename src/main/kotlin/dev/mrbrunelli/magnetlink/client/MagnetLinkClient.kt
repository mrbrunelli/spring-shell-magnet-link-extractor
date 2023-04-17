package dev.mrbrunelli.magnetlink.client

import dev.mrbrunelli.magnetlink.model.MagnetLink
import org.jsoup.Jsoup
import org.springframework.stereotype.Service
import java.net.URLDecoder

@Service
class MagnetLinkClient {
    fun extractLinks(url: String): List<MagnetLink> {
        val doc = Jsoup.connect(url).get()
        val links = mutableListOf<MagnetLink>()
        val linkElements = doc.select("a")

        for (linkElement in linkElements) {
            val href = linkElement.attr("href")
            if (href.startsWith("magnet:?xt", ignoreCase = true)) {
                val title = getTitle(href)
                if (title.isNotEmpty()) {
                    links.add(MagnetLink(title = title, link = href))
                }
            }
        }

        return links
    }

    private fun getTitle(link: String): String {
        val regex = Regex("dn=(.*?)&")
        return when (val title = regex.find(link)?.groups?.get(1)?.value) {
            null -> ""
            else -> URLDecoder.decode(title, "UTF-8")
        }
    }
}