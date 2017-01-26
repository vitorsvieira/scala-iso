scala-iso
=========

[![Build Status](https://travis-ci.org/vitorsvieira/scala-iso.svg?branch=master)](https://travis-ci.org/vitorsvieira/scala-iso)
[![Software License](https://img.shields.io/badge/license-Apache 2-brightgreen.svg?style=flat)](LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.vitorsvieira/scala-iso_2.12.svg)](https://maven-badges.herokuapp.com/maven-central/com.vitorsvieira/scala-iso_2.12)

## Motivation

  - One stop shop for ISO related types.

## Installation

To get started with SBT, simply add the following to your `build.sbt` or `Build.scala` file:

``` scala   
libraryDependencies += "com.vitorsvieira" %% "scala-iso" % "0.1.2"
```

## How to use

The easiest way to use the types available is just adding the following import statement:
 
```scala
import com.vitorsvieira.iso._
```

### ISOCountry
`ISOCountry` can be retrieved using an `alpha-2 code` or its numerical code.
```scala
scala> ISOCountry("US")
ISOCountry = US
scala> ISOCountry(840)
ISOCountry = US
//or just using the country type
scala> val us = ISOCountry.UNITED_STATES
```

Using the `from` method to return an `Option[ISOCountry]` in case the alpha-2 or numerical code does not exist.
```scala
scala> ISOCountry.from("US")
Option[ISOCountry] = Some(US)
scala> ISOCountry.from(840)
Option[ISOCountry] = Some(US)
```

Also, a list of countries can be returned using the `fromContinent` method passing a `ISOContinent` as argument.
```scala
scala> val countries = ISOCountry.fromContinent(ISOContinent.ANTARCTICA)
countries: Seq[ISOCountry] = Vector(AQ, BV, TF, HM, GS)
```

Every `ISOCountry` is composed by 5 properties:

- Alpha-2 code, Numerical Code, Formal English Name, Alpha-3 code, ISOContinent

```scala
ISOCountry("US", 840, "United States of America", "USA", ISOContinent.NORTH_AMERICA)
```

### ISOCountrySubdivision
```scala
val subdivision = ISOCountrySubdivision("US-NY")
subdivision: ISOCountrySubdivision = US-NY
//or
val ny = ISOCountrySubdivision.`New York`
ny: ISOCountrySubdivision = US-NY
```

Using the `from` method to return an `Option[ISOCountry]` in case the alpha-2 or numerical code does not exist.
```scala
scala> val optSubdivision = ISOCountrySubdivision.from("US-NY")
optSubdivision: Option[ISOCountrySubdivision] = Some(US-NY)
```

Also, a list of subdivisions can be returned using the `fromCountry` method passing a `ISOCountry` as argument.
```scala
scala> val subdivisions = ISOCountrySubdivision.fromCountry(ISOCountry.AUSTRALIA)
subdivisions: Seq[ISOCountrySubdivision] = Vector(AU-ACT, AU-NSW, AU-NT, AU-QLD, AU-SA, AU-TAS, AU-VIC, AU-WA)
```

Every `ISOCountrySubdivision` is composed by 3 properties:

- `ISOCountry`, Formal English Name, 3166-2 code

```scala
ISOCountrySubdivision(ISOCountry.UNITED_STATES, "New York", "US-NY")
```

### ISOCurrency
`ISOCurrency` can be retrieved using the currency code, numerical code, or `ISOCountry`.
```scala
ISOCurrency("USD")
ISOCurrency(840)
ISOCurrency(ISOCountry.UNITED_STATES)
//or
ISOCurrency.US_DOLLAR
```

`ISOCurrency` has the same `from` method to retrieve a `Option[ISOCurrency]` as the types above using the same parameters as the method `apply`.

Every `ISOCurrency` is composed by 4 properties:

- Currency code, Numerical Code, Minor Unit, collection of `ISOCountry`.

```scala
ISOCurrency(
  "AUD",
  36,
  2,
  ISOCountry.HEARD_ISLAND_AND_MCDONALD_ISLANDS,
  ISOCountry.TUVALU,
  ISOCountry.KIRIBATI,
  ISOCountry.AUSTRALIA,
  ISOCountry.NORFOLK_ISLAND,
  ISOCountry.NAURU,
  ISOCountry.CHRISTMAS_ISLAND,
  ISOCountry.COCOS_ISLANDS
)
```

### CountryCallingCodes
```scala
CountryCallingCode(ISOCountry.UNITED_STATES)
CountryCallingCode("1")
CountryCallingCode(1)
//or just
CountryCallingCode.`+1`
```

Every `CountryCallingCodes` is composed by 3 properties:

- Calling code, Numerical Code, collection of `ISOCountry`

```scala
CountryCallingCode("1", 1, ISOCountry.UNITED_STATES, ISOCountry.CANADA)
```

## Roadmap

- [x] ISO 3166-1 - codes for the names of countries, dependent territories, and special areas of geographical interest.
- [x] ISO 3166-2 - codes for identifying the principal subdivisions (e.g., provinces or states) of all countries coded in ISO 3166-1.
- [x] ISO 4217   - codes for currencies in circulation, composed of a country's two-character Internet country code plus a third character denoting the currency unit.
- [x] E.164 - country calling codes or country dial in codes are telephone dialing prefixes for the member countries of the International Telecommunication Union.
- [ ] Language / Locales. 
- [ ] Banking / Financial.
- [ ] File formats.
- [ ] etc...
- [ ] and other regulated standards direct or indirectly related to ISO.


## References

- [ISO 3166 - Spec](http://www.iso.org/iso/home/standards/country_codes.htm)
- [ISO 3166 - Country Codes Collection](http://www.iso.org/iso/home/store/publication_item.htm?pid=PUB500001%3aen)
- [ISO 4217 - Spec](http://www.iso.org/iso/home/standards/currency_codes.htm)
- [E.164 - The International Public Telecommunication Numbering Plan](https://www.itu.int/rec/dologin_pub.asp?lang=e&id=T-REC-E.164-201011-I!!PDF-E&type=items)

## License ##

This code is open source software licensed under the
[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0) license.
