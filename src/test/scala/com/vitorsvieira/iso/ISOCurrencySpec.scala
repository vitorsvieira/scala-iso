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
import com.vitorsvieira.iso.ISOCurrency.ISOCurrency

class ISOCurrencySpec extends WordSpec {

  "ISOCurrency enum object" should {

    "return ISOCurrency from currency code" in {
      assert(ISOCurrency.apply("USD").isInstanceOf[ISOCurrency])
      assert(ISOCurrency("USD").isInstanceOf[ISOCurrency])
    }

    "return Option[ISOCurrency] from currency code" in {
      assert(ISOCurrency.from("USD").isInstanceOf[Option[ISOCurrency]])
      assert(ISOCurrency.from("USD").isDefined)
    }

    "return ISOCurrency using the Currency numeric code" in {
      assert(ISOCurrency.apply(840).isInstanceOf[ISOCurrency])
      assert(ISOCurrency(840).isInstanceOf[ISOCurrency])
    }

    "return Option[ISOCurrency] using the Currency numeric code" in {
      assert(ISOCurrency.from(840).isInstanceOf[Option[ISOCurrency]])
      assert(ISOCurrency.from(840).isDefined)
    }

    "return ISOCurrency using the ISOCountry" in {
      assert(ISOCurrency.apply(ISOCountry.JAPAN).isInstanceOf[ISOCurrency])
      assert(ISOCurrency(ISOCountry.JAPAN).isInstanceOf[ISOCurrency])
    }

    "return Option[ISOCurrency] using ISOCountry" in {
      assert(ISOCurrency.from(ISOCountry.JAPAN).isInstanceOf[Option[ISOCurrency]])
      assert(ISOCurrency.from(ISOCountry.JAPAN).isDefined)
    }

    "return None when Currency code does not exist" in {
      assert(ISOCurrency.from("XXXX").isEmpty)
    }

    "return None when Currency numeric code does not exist" in {
      assert(ISOCurrency.from(9999).isEmpty)
    }

    "return ISOCurrency code value property" in {
      assert(ISOCurrency.apply(840).value == "USD")
    }

    "return ISOCurrency numericalCode property" in {
      assert(ISOCurrency.apply("USD").numericalCode == 840)
    }

    "return ISOCurrency minorUnit property" in {
      assert(ISOCurrency.apply("USD").minorUnit == 2)
    }
    "return ISOCurrency countries property" in {
      assert(ISOCurrency.apply("JPY").countries == Seq(ISOCountry.JAPAN))
    }

    "return ParseException when currency code does not exist" in {
      val caught = intercept[ParseException] {
        ISOCurrency("XXXX")
      }
      assert(caught.getMessage.indexOf("-1") == -1)
      assert(caught.getMessage == "Invalid currency code 'XXXX' for ISOCurrency")
    }

    "return ParseException when numeric code does not exist" in {
      val caught = intercept[ParseException] {
        ISOCurrency(9999)
      }
      assert(caught.getMessage.indexOf("-1") == -1)
      assert(caught.getMessage == "Invalid numeric code '9999' for ISOCurrency")
    }

    "return ParseException when ISOCountry does not exist" in {
      val caught = intercept[ParseException] {
        ISOCurrency(new ISOCountry("", 0, "", "", ISOContinent.ANTARCTICA))
      }
      assert(caught.getMessage.indexOf("-1") == -1)
      assert(caught.getMessage == "Invalid country '' for ISOCurrency")
    }
  }
}
