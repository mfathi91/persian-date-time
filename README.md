![Omar Khayyam](http://www.untoldiran.com/image/khayam02.jpg)

# Persian Date Time

This library is an implementation of [Solar Hijri calendar](https://en.wikipedia.org/wiki/Solar_Hijri_calendar) (also known as Jalali calendar or Persian calendar). It is an immutable and thread-safe implementation of Persian calendar system, and can be used in multi-threaded
programs.

Since Java does not support Persian calendar, this library can be used in the applications that need Persian calendar system. Purpose of this library is to provide an immutable and easy API (that is similar to JDK 8 date and time API) for Persian calendar system.

## Instructions

### Maven
Include the following to your dependency list:
```xml
<dependency>
  <groupId>com.github.mfathi91</groupId>
  <artifactId>persian-date-time</artifactId>
  <version>2.0.1</version>
</dependency>
```

### Usage
One can get an instance of _PersianDate_ for the present date:
```java
PersianDate today = PersianDate.now();
```

To get an instance of PersianDate for a desired date:
```java
PersianDate persianDate1 = PersianDate.of(1396, 7, 1);
PersianDate persianDate2 = PersianDate.of(1370, PersianMonth.ORDIBEHESHT, 31);
```
PersianDate class also checks whether the desired date exists or not. For example year _1388_ is not a leap year, so month _ESFAND_ has 29 days and the following code snippet will throw an exception:
```java
PersianDate notRealDate = PersianDate.of(1388, PersianMonth.ESFAND, 30);    // An exception will be thrown
```

It is possible to easily convert Persian date to corresponding Gregorian date:
```java
// Convert Persian date to Gregorian date
PersianDate persianDate1 = PersianDate.of(1397, 5, 11);
LocalDate gregDate1 = persianDate1.toGregorian();

// Convert Gregorian date to Persian date
LocalDate gregDate2 = LocalDate.of(2015, 4, 17);
PersianDate persianDate2 = PersianDate.gregorianToPersian(gregDate2);
```
The conversion algorithm from Solar Hijri calendar to Gregorian calendar and vice versa, is adopted from [here](http://www.fourmilab.ch/documents/calendar/).

### Requirements
This version of Persian Date Time requires:
 * Java SE 8

## License
This library is released under MIT license.