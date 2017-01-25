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
import com.vitorsvieira.iso.CountryCallingCode.CountryCallingCode
import com.vitorsvieira.iso.ISOCountry.ISOCountry

class CountryCallingCodeSpec extends WordSpec {

  "CountryCallingCode enum object" should {

    "return CountryCallingCode from currency code" in {
      assert(CountryCallingCode.apply("1").isInstanceOf[CountryCallingCode])
      assert(CountryCallingCode("1").isInstanceOf[CountryCallingCode])
    }

    "return Option[CountryCallingCode] from currency code" in {
      assert(CountryCallingCode.from("1").isInstanceOf[Option[CountryCallingCode]])
      assert(CountryCallingCode.from("1").isDefined)
    }

    "return CountryCallingCode using the Currency numeric code" in {
      assert(CountryCallingCode.apply(1).isInstanceOf[CountryCallingCode])
      assert(CountryCallingCode(1).isInstanceOf[CountryCallingCode])
    }

    "return Option[CountryCallingCode] using the Currency numeric code" in {
      assert(CountryCallingCode.from(1).isInstanceOf[Option[CountryCallingCode]])
      assert(CountryCallingCode.from(1).isDefined)
    }

    "return CountryCallingCode using the ISOCountry" in {
      assert(CountryCallingCode.apply(ISOCountry.JAPAN).isInstanceOf[CountryCallingCode])
      assert(CountryCallingCode(ISOCountry.JAPAN).isInstanceOf[CountryCallingCode])
    }

    "return Option[CountryCallingCode] using ISOCountry" in {
      assert(CountryCallingCode.from(ISOCountry.JAPAN).isInstanceOf[Option[CountryCallingCode]])
      assert(CountryCallingCode.from(ISOCountry.JAPAN).isDefined)
    }

    "return None when Currency code does not exist" in {
      assert(CountryCallingCode.from("XXXX").isEmpty)
    }

    "return None when Currency numeric code does not exist" in {
      assert(CountryCallingCode.from(9999).isEmpty)
    }

    "return CountryCallingCode code value property" in {
      assert(CountryCallingCode(1).value == "1")
    }

    "return CountryCallingCode numericalCode property" in {
      assert(CountryCallingCode("1").numericalCode == 1)
    }

    "return CountryCallingCode countries property" in {
      assert(CountryCallingCode("81").countries == Seq(ISOCountry.JAPAN))
    }

    "return ParseException when currency code does not exist" in {
      val caught = intercept[ParseException] {
        CountryCallingCode("XXXX")
      }
      assert(caught.getMessage.indexOf("-1") == -1)
      assert(caught.getMessage == "Invalid value 'XXXX' for CountryCallingCode")
    }

    "return ParseException when numeric code does not exist" in {
      val caught = intercept[ParseException] {
        CountryCallingCode(9999)
      }
      assert(caught.getMessage.indexOf("-1") == -1)
      assert(caught.getMessage == "Invalid country calling code '9999' for CountryCallingCode")
    }

    "return ParseException when ISOCountry does not exist" in {
      val caught = intercept[ParseException] {
        CountryCallingCode(new ISOCountry("", 0, "", "", ISOContinent.ANTARCTICA))
      }
      assert(caught.getMessage.indexOf("-1") == -1)
      assert(caught.getMessage == "Invalid country '' for CountryCallingCode")
    }
  }
}
