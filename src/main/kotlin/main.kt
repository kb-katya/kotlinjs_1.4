import data.Student
import data.studentList
import kotlinx.html.*
import kotlinx.html.dom.*
import kotlinx.html.js.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLLIElement
import org.w3c.dom.events.Event
import kotlin.browser.document
import kotlin.dom.clear

var ascending = true

fun main() {
    val colors = setOf("black", "red", "blue", "green", "yellow")
    document.getElementById("root")!!
        .append {
            h1 {
                +"Students"
                onClickFunction = onClickFunction()
            }
            ol {
                attributes += "id" to "listStudents"
                studentList.mapIndexed { index, student ->
                    getStudent(index, student)
                }
            }
            colors.mapIndexed { index, color ->
                p {
                    input {
                        attributes += "id" to "color-${index}"
                        checked = color == "black"
                        type = InputType.radio
                        name = "btn-radio"
                        value = color
                        onClickFunction = onClickRadio(color)
                    }
                    style = "color: $color"
                    +color
                }
            }
        }
}

private fun onClickRadio(color: String): (Event) -> Unit {
    return {
        document.getElementById("root")!!
            .setAttribute("style", "color: $color")
    }
}

private fun onClickFunction(id: String, student: Student): (Event) -> Unit {
    return {
        student.presence = !student.presence
        document.getElementById(id)!!.apply {
            className = if (student.presence)
                "" else "student-not_active"
        }
    }
}

private fun onClickFunction(): (Event) -> Unit {
    return {
        val listStudents = document.getElementById("listStudents")!!
        listStudents.clear()
        listStudents.append {
            if (ascending)
                studentList.sortBy { it.firstname }
            else
                studentList.sortByDescending { it.firstname }
            ascending = !ascending
            studentList.mapIndexed { index, student ->
                getStudent(index, student)
            }
        }
    }
}

private fun TagConsumer<HTMLElement>.getStudent(
    index: Int,
    student: Student
): HTMLLIElement {
    return li {
        val id = "student-${index}"
        +"${student.firstname} ${student.surname}"
        onClickFunction = onClickFunction(id, student)
        attributes += "id" to id
        classes = setOf(
            if (student.presence)
                "" else "student-not_active"
        )
    }
}