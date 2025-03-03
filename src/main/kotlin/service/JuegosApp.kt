package service

import database.ConexionBD
import model.Juego

class JuegosApp {
    private val db = ConexionBD.getDatabase("fbatlos")
    private val collection = db.getCollection("coll_juegos")

    fun listarJuegos(): List<Juego> {

        val listado =  collection.find().map { doc ->
            Juego(
                titulo = doc.getString("titulo"),
                genero = doc.getString("genero"),
                precio = doc.getDouble("precio"),
                fecha_lanzamiento = doc.getDate("fecha_lanzamiento")
            )
        }.toList()

        return listado
    }


    fun buscarPorGenero(genero: String): List<Juego> {
        val buscarGenero = collection.find(org.bson.Document("genero", genero))
            .sort(org.bson.Document("titulo", 1))
            .map { doc ->
                Juego(
                    titulo = doc.getString("titulo"),
                    genero = doc.getString("genero"),
                    precio = doc.getDouble("precio"),
                    fecha_lanzamiento = doc.getDate("fecha_lanzamiento")
                )
            }.toList()

        if (buscarGenero.isEmpty()){
            println("No se ha encontrado ese genero")
        }

        return buscarGenero
    }


    fun registrarJuego(juego: Juego): String {
        if (juego.titulo.isBlank() || juego.precio <= 0){
            return "Error: Falta algun campo del juego."
        }

        val existe = collection.find(org.bson.Document("titulo", juego.titulo)).first() != null

        if (existe) {

            return ("Error: Ya existe un juego con el titulo '${juego.titulo}'")

            /* No se puede hacer ciclica porque hace la inyeccion de todas la veces fallada
            println("Dame otro titulo: ")
            juego.titulo = readLine() ?: ""

            registrarJuego(juego)
             */
        }

        collection.insertOne(
            org.bson.Document()
                .append("titulo", juego.titulo)
                .append("genero", juego.genero)
                .append("precio", juego.precio)
                .append("fecha_lanzamiento", juego.fecha_lanzamiento)
        )
        return "Juego registrado exitosamente."
    }


    fun eliminarPorGenero(genero: String): String {
        val result = collection.deleteMany(org.bson.Document("genero", genero))
        return "Se han eliminado ${result.deletedCount} juegos del genero '$genero'."
    }


    fun modificarJuego(titulo: String, nuevosDatos: Juego): String {
        val resultado = collection.updateOne(
            org.bson.Document("titulo", titulo),
            org.bson.Document("\$set", org.bson.Document()
                .append("genero", nuevosDatos.genero)
                .append("precio", nuevosDatos.precio)
                .append("fecha_lanzamiento", nuevosDatos.fecha_lanzamiento)
            )
        )
        return if (resultado.matchedCount > 0) {
            "Juego actualizado exitosamente."
        } else {
            "No se encontro un juego con el titulo '$titulo'."
        }
    }
}


