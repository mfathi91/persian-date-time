![Omar Khayyam](http://www.untoldiran.com/image/khayam02.jpg)

# Persian Date Time

This library is an immutable implementation of Persian calendar based on ICU4J library. Since Java does not support Persian calendar, 
this library can be used in the applications that need Persian calendar system. Purpose of this library is to provide an immutable
and easy API (that is similar to JDK 8 date and time API) for Persian calendar system.

API of this library is aimed to be the same as Java 8 _LocalDateTime_ and _LocalDate_ classes. For example user can get an instance
of _PersianDate_ class:
```java
PersianDate today = PersianDate.now();
PersianDate persianDate1 = PersianDate.of(1396, 7, 1);
PersianDate persianDate2 = PersianDate.of(1370, PersianDate.Month.ORDIBEHESHT, 31);
```

It is possible to easily convert Persian date to corresponding Gregorian date:
```java
PersianDate persianDate = PersianDate.of(1397, 5, 11);
LocalDate gregDate = persianDate.toGregorian();
```
