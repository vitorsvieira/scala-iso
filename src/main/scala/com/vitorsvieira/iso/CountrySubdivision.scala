package com.vitorsvieira.iso

/**
  * ISO 3166-2 is part of the ISO 3166 standard
  * and defines codes for identifying the principal subdivisions
  * (e.g., provinces or states) of all countries coded in ISO 3166-1.
  * The official name of the standard is
  * "Codes for the representation of names of countries and their subdivisions – Part 2: Country subdivision code."
  */
trait CountrySubdivision extends Enum { self =>

  import com.vitorsvieira.iso.ISOCountry.ISOCountry

  sealed class EnumVal(val country: ISOCountry, val subdivision: String, val value: String) extends Value

  type ISOCountrySubdivision = EnumVal

  /**
    * Retrieves ISOCountrySubdivision based on xx-xx code.
    * https://www.iso.org/obp/ui/#search
    *
    * @param countryCode Country code, ie. US-NY, US-AL
    * @return ISOCountrySubdivision
    */
  def apply(countryCode: String): ISOCountrySubdivision = self.values.find(countryCode == _.toString) match {
    case Some(country) ⇒ country
    case None             ⇒ throw new ParseException(s"Invalid value '$countryCode' for ISOCountrySubdivision")
  }

  /**
    * Retrieves ISOCountrySubdivision based on xx-xx code.
    * https://www.iso.org/obp/ui/#search
    *
    * @param countryCode Country code, ie. US-NY, US-AL
    * @return Option[ISOCountrySubdivision]
    */
  def from(countryCode: String): Option[ISOCountrySubdivision] = self.values.find(countryCode == _.toString) match {
    case opt@Some(_) ⇒ opt
    case opt@None    ⇒ opt
  }
}
