fun next(){
    if (currentIndex < pages.lastIndex) {
        currentIndex++
        showPage()
    }
}

fun prev(){
    if (currentIndex > 0) {
        currentIndex--
        showPage()
    }
}

fun goBackToStart() {
    currentIndex = 0
    showPage()
}