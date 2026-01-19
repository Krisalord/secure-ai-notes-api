package io.github.krisalord.routes

import io.github.krisalord.models.dto.notes.CreateNoteRequest
import io.github.krisalord.models.dto.notes.UpdateNoteRequest
import io.github.krisalord.services.NoteService
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import org.bson.types.ObjectId

fun Route.noteRoutes(noteService: NoteService) {
    authenticate("auth-jwt") {
        get("/notes") {
            val principal = call.principal<JWTPrincipal>()!!
            val userId = ObjectId(principal.payload.getClaim("userId").asString())

            val notes = noteService.getUserNotes(userId)
            call.respond(notes)
        }

        post("/notes") {
            val principal = call.principal<JWTPrincipal>()!!
            val userId = ObjectId(principal.payload.getClaim("userId").asString())

            val request = call.receive<CreateNoteRequest>()
            val note = noteService.createNote(userId, request.content)

            call.respond(HttpStatusCode.Created, note)
        }

        put("/notes/{id}") {
            val principal = call.principal<JWTPrincipal>()!!
            val userId = ObjectId(principal.payload.getClaim("userId").asString())

            val noteId = call.parameters["id"]
                ?.let { ObjectId(it) }
                ?: return@put call.respond(HttpStatusCode.BadRequest)

            val request = call.receive<UpdateNoteRequest>()

            noteService.updateNote(userId, noteId, request.content)

            call.respond(HttpStatusCode.OK)
        }

        delete("/notes/{id}") {
            val principal = call.principal<JWTPrincipal>()!!
            val userId = ObjectId(principal.payload.getClaim("userId").asString())

            val noteId = call.parameters["id"]
                ?.let { ObjectId(it) }
                ?: return@delete call.respond(HttpStatusCode.BadRequest)

            noteService.deleteNote(userId, noteId)

            call.respond(HttpStatusCode.NoContent)
        }

    }
}