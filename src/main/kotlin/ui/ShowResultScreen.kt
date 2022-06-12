package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import model.Student
import ui.component.MyText
import java.util.*
import kotlin.collections.ArrayList

@ExperimentalUnitApi
@Composable
fun ShowResultScreen(
    reader: Scanner,
    onBackClick: () -> Unit
) {
    val students: MutableState<ArrayList<Student>> = mutableStateOf(ArrayList())
    for (i in 1..reader.nextLine().toInt()) {
        val fields = reader.nextLine().split(",")
        students.value.add(
            Student(
                firstName = fields[0],
                lastName = fields[1],
                nationalCode = fields[2],
                average = fields[3]
            )
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(36.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = rememberLazyListState()
    ) {
        items(students.value) {
            Card(
                modifier = Modifier.wrapContentSize(Alignment.Center)
                    .padding(16.dp)
                    .background(
                        shape = RoundedCornerShape(50.dp),
                        color = Color.Cyan
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    MyText(textSize = 16f, text = "نام: ${it.firstName}")
                    MyText(textSize = 16f, text = "نام خانوادگی: ${it.lastName}")
                    MyText(textSize = 16f, text = "کد ملی: ${it.nationalCode}")
                    MyText(textSize = 16f, text = "معدل: ${it.average}")
                }
            }
        }
    }
}