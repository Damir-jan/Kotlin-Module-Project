class Archive (var name: String) {
    private val notes = mutableListOf<Note>()

    fun addNote(note: Note) {
        notes.add(note)
    }
    fun getNotes(): List<Note> {
        return notes.toList()
    }
}