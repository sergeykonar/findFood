package com.example.data.models

import com.example.domain.models.Eatable
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "search", strict = false)

class FoodItem {

    @field:ElementList(name = "potravina", required = false, inline = true)
    @field:Attribute(name = "jedn")
    lateinit var food: ArrayList<Potravina>

    public class Potravina: Eatable {

        @field:Attribute(name = "guid_potravina")
        lateinit var guid_potravina: String

        @field:Attribute(name = "id_stav", required = false)
        lateinit var id_stav: String

        @field:Attribute(name = "zamek", required = false)
        lateinit var zamek: String

        @field:Attribute(name = "kategorie", required = false)
        lateinit var kategorie: String

        @field:Attribute(name = "napoj", required = false)
        lateinit var napoj: String

        @field:Element(name = "nazev", required = false)
        lateinit var nazev: String

        @field:Element(name = "oblibena", required = false)
        lateinit var oblibena: String

        @field:Element(name = "foto", required = false)
        var photo: String? = null

        @field:Element(name = "foto_thumb", required = false)
        lateinit var photo_thumb: String

        @field:Element(name = "autor", required = false)
        lateinit var autor: String

        @field:Element(name = "energie", required = false)
        @field:Attribute(name = "jedn")
        lateinit var energy: String

    }
}