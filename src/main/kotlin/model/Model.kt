package model

class Model {
    private val archives = mutableListOf<Archive>()

    fun getArchives(): List<Archive> {
        return archives.toList()
    }

    fun addArchive(archiveName: String) {
        archives.add(Archive(archiveName))
    }

    fun addNoteToArchive(archive: Archive, note: Note) {
        archive.notes.add(note)
    }
}
