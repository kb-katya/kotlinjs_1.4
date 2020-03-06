package data

data class Student (
    val firstname: String,
    val surname: String,
    var presence: Boolean
)

val studentList =
    arrayListOf(
        Student("Жестовский", "Ян", true),
        Student("Дёмина", "Анастасия", true),
        Student("Бейсембаев", "Имир", true),
        Student("Жараспаева", "Джанна", true),
        Student("Буйволова", "Екатерина", true),
        Student("Воробьёв", "Данила", true),
        Student("Купряков", "Даниил", true),
        Student("Султанов", "Алмат", true),
        Student("Сексембаев", "Тимур", true)
    )