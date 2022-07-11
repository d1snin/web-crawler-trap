package dev.d1s.webcrawlertrap.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import java.util.concurrent.ThreadLocalRandom

@RestController
class WebCrawlerTrapController {

    private val formatter = HexFormat.of()

    @GetMapping("/**", produces = [MediaType.TEXT_HTML_VALUE])
    fun handle() = """
    <!DOCTYPE html>
    <html>
    <head>
    </head>
    <body>
    <a href="${
        run {
            val buffer = ByteArray(64)

            ThreadLocalRandom.current().nextBytes(buffer)

            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath("/${formatter.formatHex(buffer)}")
                .build()
                .toUriString()
        }
    }">:troll:</a>
    </body>
    </html> 
    """.trimIndent()
}