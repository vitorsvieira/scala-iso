/*
 * Copyright 2017 Vitor S. Vieira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vitorsvieira.iso

/**
 * ISO 3166-2 is part of the ISO 3166 standard
 * and defines codes for identifying the principal subdivisions
 * (e.g., provinces or states) of all countries coded in ISO 3166-1.
 * The official name of the standard is
 * "Codes for the representation of names of countries and their subdivisions – Part 2: Country subdivision code."
 */
trait CountrySubdivision extends Enum { self ⇒

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
    case None          ⇒ throw new ParseException(s"Invalid value '$countryCode' for ISOCountrySubdivision")
  }

  /**
   * Retrieves ISOCountrySubdivision based on xx-xx code.
   * https://www.iso.org/obp/ui/#search
   *
   * @param countryCode Country code, ie. US-NY, US-AL
   * @return Option[ISOCountrySubdivision]
   */
  def from(countryCode: String): Option[ISOCountrySubdivision] = self.values.find(countryCode == _.toString) match {
    case opt @ Some(_) ⇒ opt
    case opt @ None    ⇒ opt
  }
}
