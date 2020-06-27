import dev.fritz2.binding.RootStore
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.html.render
import dev.fritz2.dom.mount
import dev.fritz2.dom.values
import kotlinx.coroutines.flow.map

fun main() {
    val store = object : RootStore<String>("Your Name") {
        val clear = handle { model ->
            console.log("Clearing")
            ""
        }
    }

    render {
        div {
            p {
                store.data.map { "input: $it" }.bind()
            }
            input {
                value = store.data
                changes.values().handledBy(store.update)
            }
            button {
                text("Clear")
                clicks handledBy store.clear
            }
        }

    }.mount("target")
}