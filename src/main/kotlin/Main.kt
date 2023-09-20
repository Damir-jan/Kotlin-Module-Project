fun main() {
    val app = NotesApp()

        while (true) {
            when {
                app.currentNote != null -> {
                    app.showNote()
                    app.handleNoteMenu()
                }
                app.currentArchive != null -> {
                    app.showArchive()
                    app.handleArchiveMenu()
                }
                else -> {
                app.showMainMenu()
                    app.handleMainMenu()
                }
            }
        }
    }

