![Persian Date Time](https://user-images.githubusercontent.com/29010410/41397561-dc9a470a-6fc9-11e8-923a-112393900b0c.JPG)
----------------------------------------------------
[![Maven Central](https://img.shields.io/maven-central/v/com.github.mfathi91/persian-date-time)](https://central.sonatype.com/artifact/com.github.mfathi91/persian-date-time)
[![CI](https://github.com/mfathi91/persian-date-time/actions/workflows/ci.yml/badge.svg)](https://github.com/mfathi91/persian-date-time/actions/workflows/ci.yml)
[![Javadocs](https://javadoc.io/badge/com.github.mfathi91/persian-date-time.svg?color=brightgreen)](https://javadoc.io/doc/com.github.mfathi91/persian-date-time)



This library is an implementation of [Solar Hijri calendar](https://en.wikipedia.org/wiki/Solar_Hijri_calendar) (also known as Jalali calendar or Persian calendar). It is an immutable and thread-safe implementation of Persian calendar system, and can be used in multi-threaded programs.

As Java does not support Persian calendar, this library can be used in the applications that need Persian calendar system. Purpose of this library is to provide an immutable and easy API (that is similar to JDK 8 date and time API) for Persian calendar system. This class has been implemented the same as internal JDK calendars such as JapaneseDate.

## Instructions

### Maven
Include the following to your dependency list:
```xml
<dependency>
  <groupId>com.github.mfathi91</groupId>
  <artifactId>persian-date-time</artifactId>
  <version>4.3.0</version>
</dependency>
```

### Usage
`PersianDate`: The base class for Persian date handling. Here are some trivial examples on how to use different 
functionalities of `PersianDate`
```java
// Instantiate 
PersianDate today = PersianDate.now();
PersianDate persianDate1 = PersianDate.of(1396, 7, 15);
PersianDate persianDate2 = PersianDate.of(1396, PersianMonth.MEHR, 15);

// Convert
PersianDate persianDate5 = PersianDate.of(1397, 5, 11);
LocalDate gregDate = persianDate5.toGregorian();    // => '2018-08-02'
PersianDate persianDate6 = PersianDate.fromGregorian(gregDate);  //  => '1397/05/11'

// Parse
PersianDate persianDate3 = PersianDate.parse("1400-06-15");    // From the standard format
PersianDate persianDate4 = PersianDate.parse("1400/06/15", DateTimeFormatter.ofPattern("yyyy/MM/dd"));    // From a desired format

// Format
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
dtf.format(PersianDate.now());    // => e.g. '1396/05/10'
```
`PersianDateTime`: A wrapper class around `PersianDate` and `LocalTime` to make the date-time handling more 
convenient. Here are some trivial examples of `PersianDateTime`
```java
// Instantiate
PersianDateTime now = PersianDateTime.now();    // => 'now' will contain the instantiated date and time
PersianDateTime persianDateTime2 = PersianDateTime.of(PersianDate.of(1400, PersianMonth.DEY, 15), LocalTime.of(17, 55, 19));
PersianDateTime persianDateTime3 = PersianDateTime.of(1400, PersianMonth.DEY, 15, 17, 55, 19);

// Parse
PersianDateTime persianDateTime4 = PersianDateTime.parse("1401-06-10T08:35:11");    // From the standard format
PersianDateTime persianDateTime5 = PersianDateTime.parse("1400-06-15_11-38-43", DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));    // From a desired format

// Format
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
dtf.format(PersianDateTime.now());    // => e.g. '1396/05/10 14:05:11'
```
Leap years and the conversion between Solar Hijri calendar and Gregorian calendar follow
[Borkowski's algorithm](https://www.astro.uni.torun.pl/~kb/Papers/EMP/PersianC-EMP.htm), which matches the official
Iranian calendar. Supported years are 1 to 1999.

### Requirements
This version of Persian Date Time requires:
 * Java 8 or later

## License
This library is released under MIT license.
