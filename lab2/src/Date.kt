import java.time.LocalDate
import kotlin.random.Random

data class Date(
    val  year: Int = LocalDate.now().year,
    val month: Int = LocalDate.now().monthValue,
    val day: Int = LocalDate.now().dayOfMonth,
)


fun Date.isLeapYear(): Boolean {
    var leap = false
    if (year % 4 == 0) {
        if (year % 100 == 0) {
            leap = year % 400 == 0
        } else
            leap = true
    } else
        leap = false
    return leap
}

fun isLeapYear2byYear(year: Int): Boolean {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}

fun Date.isValid() : Boolean {
    if(year <= 0){
        return false
    }
    if(month !in 1..12) {
        return false
    }

    val daysInMonth = when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31 // Months with 31 days
        4, 6, 9, 11 -> 30         // Months with 30 days
        2 -> if (isLeapYear2byYear(year)) 29 else 28
        else -> return false
    }

    if(day !in 1..daysInMonth) { return false}
    return true
}


fun generateRandomDate(): Date {
    val year = Random.nextInt(1800, 2100)
    val month = Random.nextInt(1, 15)
    val day = Random.nextInt(1, 38)
    return Date(year, month, day)
}

val customComparator: Comparator<Date> = Comparator { d1, d2 ->
    when {
        d1.month != d2.month -> d1.month.compareTo(d2.month)  // Sort by month
        d1.day != d2.day -> d1.day.compareTo(d2.day)          // Sort by day
        else -> d1.year.compareTo(d2.year)                    // Sort by year
    }
}