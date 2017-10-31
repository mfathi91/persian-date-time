![Omar Khayyam](http://www.untoldiran.com/image/khayam02.jpg)

# Persian Date Time

This library is an implementation of [Solar Hijri calendar](https://en.wikipedia.org/wiki/Solar_Hijri_calendar) (also known as Jalali calendar or Persian calendar). It is an immutable and thread-safe implementation of Persian calendar system, and can be used in multi-threaded programs.

Since Java does not support Persian calendar, this library can be used in the applications that need Persian calendar system. Purpose of this library is to provide an immutable and easy API (that is similar to JDK 8 date and time API) for Persian calendar system. This class has been implemented the same as internal JDK calendars such as JapaneseDate.

## Instructions

### Maven
Include the following to your dependency list:
```xml
<dependency>
  <groupId>com.github.mfathi91</groupId>
  <artifactId>persian-date-time</artifactId>
  <version>3.0.0</version>
</dependency>
```

### Usage
One can get an instance of _PersianDate_ for the present date:
```java
PersianDate today = PersianDate.now();
```

To get an instance of PersianDate for a desired date:
```java
PersianDate persianDate1 = PersianDate.of(1396, 7, 15);
PersianDate persianDate2 = PersianDate.of(1370, PersianMonth.MEHR, 15);
```

It is possible to easily convert Persian date to other calendar systems dates, such as Gregorian date:
```java
PersianDate persianDate = PersianDate.of(1397, 5, 11);
LocalDate gregDate1 = persianDate.toGregorian();    => '2018-08-02'
// another approach to convert
LocalDate gregDate2 = LocalDate.from(persianDate);  => '2018-08-02'
```
Converting other calendar system dates, such as Gregorian date would be like the following:
```java
LocalDate gregDate = LocalDate.of(2015, 4, 17);
PersianDate persianDate = PersianDate.ofGregorian(gregDate);    => '1394/01/28'
```
The conversion algorithm from Solar Hijri calendar to Gregorian calendar and vice versa, is adopted from [here](http://www.fourmilab.ch/documents/calendar/).

It is possible to format an instance of PersianDate using _DateTimeFormatter_ class:
```java
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
dtf.format(PersianDate.now());    => e.g. '1396/05/10'
// another approach to format
PersianDate.now().format(dtf);    => e.g. '1396/05/10'
```

### Requirements
This version of Persian Date Time requires:
 * Java SE 8

## License
This library is released under MIT license.