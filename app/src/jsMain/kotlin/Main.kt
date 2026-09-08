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
    val img = document.getElementById("page_image")

    img?.setAttribute("src", page.imageUrl)
    img?.setAttribute("alt", page.altText)

    val backtoStart = document.getElementById("btn")

    if (currentIndex == pages.lastIndex) {
        backtoStart?.innerHTML = """
        <button id="back_to_start">Tilbake til forsiden</button>
        """

        document.getElementById("back_to_start")?.addEventListener("click", {goBackToStart()})
    }
        else { 
            backtoStart?.innerHTML = ""
    }
}

fun main() {
    document.body?.innerHTML = """
        <div class="booklet">
            <img id="page_image"/>
             <div id="btn"></div>
        </div>
        """
        
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