![Persian Date Time](https://user-images.githubusercontent.com/29010410/41397561-dc9a470a-6fc9-11e8-923a-112393900b0c.JPG)
----------------------------------------------------
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.mfathi91/persian-date-time/badge.svg)](http://search.maven.org/#search|ga|1|com.github.mfathi91)
[![codecov](https://codecov.io/gh/mfathi91/persian-date-time/branch/master/graph/badge.svg)](https://codecov.io/gh/mfathi91/persian-date-time)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/47c6af41e1fe49c3adad26a5629314cd)](https://www.codacy.com/app/mfathi91/persian-date-time?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mfathi91/persian-date-time&amp;utm_campaign=Badge_Grade)
[![Javadocs](http://javadoc.io/badge/com.github.mfathi91/persian-date-time.svg?color=brightgreen)](http://javadoc.io/doc/com.github.mfathi91/persian-date-time)



This library is an implementation of [Solar Hijri calendar](https://en.wikipedia.org/wiki/Solar_Hijri_calendar) (also known as Jalali calendar or Persian calendar). It is an immutable and thread-safe implementation of Persian calendar system, and can be used in multi-threaded programs.

As Java does not support Persian calendar, this library can be used in the applications that need Persian calendar system. Purpose of this library is to provide an immutable and easy API (that is similar to JDK 8 date and time API) for Persian calendar system. This class has been implemented the same as internal JDK calendars such as JapaneseDate.

## Instructions

### Maven
Include the following to your dependency list:
```xml
<dependency>
  <groupId>com.github.mfathi91</groupId>
  <artifactId>persian-date-time</artifactId>
  <version>4.1.0</version>
</dependency>
```

### Usage
To get the current Persian date:
```java
PersianDate today = PersianDate.now();
```
To get a desired Persian date:
```java
PersianDate persianDate1 = PersianDate.of(1396, 7, 15);
PersianDate persianDate2 = PersianDate.of(1396, PersianMonth.MEHR, 15);
```
To parse Persian date from a string:
```java
PersianDate persianDate1 = PersianDate.parse("1400-06-15");    // From the standard format
PersianDate persianDate1 = PersianDate.parse("1400/06/15", DateTimeFormatter.ofPattern("yyyy/MM/dd"));    // From a desired format
```
To convert Persian date to Gregorian date:
```java
PersianDate persianDate = PersianDate.of(1397, 5, 11);
LocalDate gregDate1 = persianDate.toGregorian();    // => '2018-08-02'
```
To convert from Gregorian date to Persian date:
```java
LocalDate gregDate = LocalDate.of(2015, 4, 17);
PersianDate persianDate = PersianDate.fromGregorian(gregDate);  //  => '1394/01/28'
```
Conversion between Persian date and any other JDK supported calendar, such as _HijrahDate_:
```java
// Convert to Persian date
PersianDate persianDate = PersianDate.from(hijrahDate);
// Convert from Persian date
HijrahDate hijrahDate = HijrahDate.from(persianDate);
```
To format a Persian date based on a _DateTimeFormatter_ class:
```java
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
dtf.format(PersianDate.now());    // => e.g. '1396/05/10'
// another possible approach to format
PersianDate.now().format(dtf);    // => e.g. '1396/05/10'
```
The conversion algorithm from Solar Hijri calendar to Gregorian calendar and vice versa, is adopted from [here](https://github.com/soroush/libcalendars).



### Requirements
This version of Persian Date Time requires:
 * Java SE 8

## License
This library is released under MIT license.
