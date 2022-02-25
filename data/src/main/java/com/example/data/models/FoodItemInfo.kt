package com.example.data.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "potravina", strict = false)
class FoodItemInfo(){

    @field:Attribute(name = "guid_potravina")
    lateinit var guid_potravina: String

    @field:Element(name = "nazev", required = false)
    lateinit var nazev: String

    @field:Element(name ="stav", required = false)
    lateinit var stav: String

    @field:Element(name ="kategorie", required = false)
    lateinit var kategorie: String

    @field:Element(name ="znacka", required = false)
    lateinit var znacka: String

    @field:Element(name ="oblibena", required = false)
    lateinit var oblibena: String

    @field:Element(name = "zakladniJednotka", required = false)
    lateinit var zakladniJednotka: String

    @field:Element(name = "zamek", required = false)
    lateinit var zamek: String

    @field:ElementList(name = "hodnoty", required = false, inline = true)
    lateinit var hodnoty: ArrayList<Hodnoty>

    public class Hodnoty{
        @field:Element(name = "energie", required = false)
        var energie: String? = null

        @field:Element(name = "bilkoviny", required = false)
        var bilkoviny: String? = null

        @field:Element(name = "sacharidy", required = false)
        var sacharidy: String? = null

        @field:Element(name = "cukry", required = false)
        var cukry: String? = null

        @field:Element(name = "tuky", required = false)
        var tuky: String? = null

        @field:Element(name = "nasyceneMastneKyseliny", required = false)
        lateinit var nasyceneMastneKyseliny: String

        @field:Element(name = "transmastneKyseliny", required = false)
        lateinit var transmastneKyseliny: String

        @field:Element(name = "cholesterol", required = false)
        lateinit var cholesterol: String

        @field:Element(name = "vlaknina", required = false)
        lateinit var vlaknina: String

        @field:Element(name = "sodik", required = false)
        lateinit var sodik: String

        @field:Element(name = "vapnik", required = false)
        lateinit var vapnik: String

        @field:Element(name = "sul", required = false)
        var sul: String? = null

        @field:Element(name = "voda", required = false)
        lateinit var voda: String

        @field:Element(name = "mononenasycene", required = false)
        lateinit var mononenasycene: String

        @field:Element(name = "polynenasycene", required = false)
        lateinit var polynenasycene: String

        @field:Element(name = "gi", required = false)
        lateinit var gi: String

        @field:Element(name = "phe", required = false)
        lateinit var phe: String

        @field:Element(name = "alcohol", required = false)
        lateinit var alcohol: String

        @field:Element(name = "tekutiny", required = false)
        lateinit var tekutiny: String

    }
}
