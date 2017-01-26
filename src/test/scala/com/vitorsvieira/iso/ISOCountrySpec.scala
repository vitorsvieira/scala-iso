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

import org.scalatest._
import Matchers._
import com.vitorsvieira.iso.ISOCountry.ISOCountry

class ISOCountrySpec extends WordSpec {

  "ISOCountry enum object" should {

    "return ISOCountry from an alpha-2 code" in {
      assert(ISOCountry.apply("US").isInstanceOf[ISOCountry])
      assert(ISOCountry("US").isInstanceOf[ISOCountry])
    }

    "return Option[ISOCountry] from an alpha-2 code" in {
      assert(ISOCountry.from("US").isInstanceOf[Option[ISOCountry]])
      assert(ISOCountry.from("US").isDefined)
    }

    "return ISOCountry using the country numeric code" in {
      assert(ISOCountry.apply(840).isInstanceOf[ISOCountry])
      assert(ISOCountry(840).isInstanceOf[ISOCountry])
    }

    "return Option[ISOCountry] using the country numeric code" in {
      assert(ISOCountry.from(840).isInstanceOf[Option[ISOCountry]])
      assert(ISOCountry.from(840).isDefined)
    }

    "return None when country alpha-2 code does not exist" in {
      assert(ISOCountry.from("XXX").isEmpty)
    }

    "return None when country numeric code does not exist" in {
      assert(ISOCountry.from(999).isEmpty)
    }

    "return ISOCountry alpha-2 value property" in {
      assert(ISOCountry(840).value == "US")
    }

    "return list of ISOCountry based on ISOContinent" in {
      assert(ISOCountry.fromContinent(ISOContinent.OCEANIA) ==
        Vector.apply[ISOCountry](
          ISOCountry("AS"),
          ISOCountry("AU"),
          ISOCountry("CK"),
          ISOCountry("FJ"),
          ISOCountry("PF"),
          ISOCountry("GU"),
          ISOCountry("KI"),
          ISOCountry("MH"),
          ISOCountry("FM"),
          ISOCountry("NR"),
          ISOCountry("NC"),
          ISOCountry("NZ"),
          ISOCountry("NU"),
          ISOCountry("NF"),
          ISOCountry("MP"),
          ISOCountry("PW"),
          ISOCountry("PG"),
          ISOCountry("PN"),
          ISOCountry("WS"),
          ISOCountry("SB"),
          ISOCountry("TK"),
          ISOCountry("TO"),
          ISOCountry("TV"),
          ISOCountry("UM"),
          ISOCountry("VU"),
          ISOCountry("WF")))
    }

    "return ISOCountry numericalCode property" in {
      assert(ISOCountry("US").numericalCode == 840)
    }
    "return ISOCountry englishName property" in {
      assert(ISOCountry("US").englishName == "United States of America")
    }
    "return ISOCountry alpha3Code property" in {
      assert(ISOCountry("US").alpha3Code == "USA")
    }
    "return ISOCountry continent property" in {
      assert(ISOCountry("US").continent == ISOContinent.NORTH_AMERICA)
    }

    "return ParseException when alpha-2 code does not exist" in {
      val caught = intercept[ParseException] {
        ISOCountry("XXX")
      }
      assert(caught.getMessage.indexOf("-1") == -1)
      assert(caught.getMessage == "Invalid alpha-2 code 'XXX' for ISOCountry")
    }

    "return ParseException when numeric code does not exist" in {
      val caught = intercept[ParseException] {
        ISOCountry(999)
      }
      assert(caught.getMessage.indexOf("-1") == -1)
      assert(caught.getMessage == "Invalid numeric code '999' for ISOCountry")
    }
  }
}
