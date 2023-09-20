class NotesApp {

         val archives = mutableListOf<Archive>()
         var currentArchive: Archive? = null
         var currentNote: Note? = null

        fun showMainMenu() {
            println("Главное меню:")
            println("1. Выбрать архив")
            println("2. Создать архив")
            println("3. Выйти")
        }

        fun handleMainMenu() {
            when (readLine()) {
                "1" -> selectArchive()
                "2" -> createArchive()
                "3" -> exitApp()
                else -> println("Неверный выбор")
            }
        }

        fun showArchive() {
            println("Архив: ${currentArchive?.name}")
            println("1. Просмотреть заметки")
            println("2. Добавить заметку")
            println("3. Вернуться в главное меню")
        }

        fun handleArchiveMenu() {
            when (readLine()) {
                "1" -> viewNotes()
                "2" -> createNote()
                "3" -> returnToMainMenu()
                else -> println("Неверный выбор")
            }
        }

        fun showNote() {
            println("Заметка: ${currentNote?.title}")
            println("Содержание:")
            println(currentNote?.content)
            println("1. Вернуться к списку заметок")
        }

    fun handleNoteMenu() {
        when (readLine()) {
            "1" -> returnToNotesList()
            else -> println("Неверный выбор")
        }
    }

    private fun returnToNotesList() {
        currentNote = null
        currentArchive?.let { currentArchive = it }
    }

        private fun selectArchive() {
            println("Выберите архив:")
            archives.forEachIndexed { index, archive ->
                println("${index + 1}. ${archive.name}")
            }
            val choice = readLine()?.toIntOrNull() ?: return
            if (choice in 1..archives.size) {
                currentArchive = archives[choice - 1]
            } else {
                println("Неверный выбор")

            }
        }

        private fun createArchive() {
            println("Введите имя нового архива:")
            val name = readLine()
            if (!name.isNullOrBlank()) {
                val archive = Archive(name)
                archives.add(archive)
                currentArchive = archive
            } else {
                println("Вы ничего не ввели, просьба ввести снова")
            }
        }

    private fun viewNotes() {
        val notes = currentArchive?.getNotes()
        if (notes != null && notes.isNotEmpty()) {
            while (true) {
                println("Заметки в архиве:")
                notes.forEachIndexed { index, note ->
                    println("${index + 1}. ${note.title}")
                }
                println("Введите номер заметки для просмотра (или '0' для выхода):")
                val choice = readLine()?.toIntOrNull() ?: continue

                if (choice == 0) {
                    currentArchive = null

                    break
                } else if (choice in 1..notes.size) {
                    currentNote = notes[choice - 1]
                    break
                } else {
                    println("Неверный выбор. Попробуйте снова.")
                }
            }
        } else {
            println("В архиве нет заметок")
            currentArchive = null
        }
    }

        private fun createNote() {
            println("Введите заголовок заметки:")
            val title = readLine()
            println("Введите содержание заметки:")
            val content = readLine()
            if (!title.isNullOrBlank() && !content.isNullOrBlank() && currentArchive != null) {
                val note = Note(title, content)
                currentArchive?.addNote(note)
            } else {
                println("Неверные данные или архив не выбран")
            }
        }

        private fun returnToMainMenu() {
            currentArchive = null
        }

        /*private fun returnToArchive() {
            currentNote = null
        }*/

        private fun exitApp() {
            println("До свидания!")
            System.exit(0)
        }
    }
