import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.events.KeyboardEvent

var currentIndex = 0;
var touchStartX = 0.0;

fun main() {
    showPage()
    window.addEventListener("keydown", {e -> 
        when ((e as KeyboardEvent).key) {
            "ArrowRight" -> next()
            "ArrowLeft" -> prev()
        
            else -> {}
        }
    })
}   

fun showPage() {
    val page = pages[currentIndex]

    document.body?.innerHTML = """
        <div class="booklet">
            <img class="page image" src="${page.imageUrl}" alt="${page.altText}" />
        </div>
        """
}