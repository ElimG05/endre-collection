import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.events.KeyboardEvent
import kotlin.math.abs


var currentIndex = 0
var touchStartX = 0.0 

fun swipePage() {
    window.addEventListener("touchstart", {e -> 
        val touchEvent = e.asDynamic().changedTouches[0]
        //the position where the swipe starts
        touchStartX = touchEvent.clientX as Double
    })
    window.addEventListener("touchend", {e ->
        val touchEvent = e.asDynamic().changedTouches[0]
        //check where the swipe ends
        val touchEndX = touchEvent.clientX as Double
        val diffX = touchEndX - touchStartX
        if (abs(diffX) > 50) {
            if (diffX > 0)
                prev() //negative = swipe left
                else 
                    next() //positive = swipe right,
        }
    })
}

fun showPage() {
    val page = pages[currentIndex]

    val backtoStart = 
    if (currentIndex == pages.lastIndex) {
            """
        <button id="back_to_start">Tilbake til forsiden</button>
        """
    }
        else { ""
    }

    document.body?.innerHTML = """
        <div class="booklet">
            <img class="page_image" src="${page.imageUrl}" alt="${page.altText}" />
            $backtoStart
        </div>
        """

    document.getElementById("back_to_start")?.addEventListener("click", {goBackToStart()})
}

fun main() {
    showPage()
    swipePage()
    window.addEventListener("keydown", {e -> 
        when ((e as KeyboardEvent).key) {
            "ArrowRight" -> next()
            "ArrowLeft" -> prev()
        
            else -> {}
        }
    })
}   