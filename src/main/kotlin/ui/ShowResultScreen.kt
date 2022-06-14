package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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

    Box(Modifier.fillMaxSize()) {
        IconButton(
            modifier = Modifier.align(Alignment.TopStart).padding(36.dp),
            onClick = onBackClick
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource("angle_left.svg"),
                contentDescription = "back"
            )
        }
        LazyColumn(
            modifier = Modifier.align(Alignment.Center).padding(horizontal = 36.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(students.value) {
                Card(
                    backgroundColor = Color(0xff5e00e5),
                    shape = RoundedCornerShape(20.dp),
                    elevation = 5.dp,
                    modifier = Modifier
                        .wrapContentHeight(Alignment.CenterVertically).width(500.dp)
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        MyText(textSize = 16f, text = "نام : ${it.firstName}")
                        MyText(textSize = 16f, text = "نام خانوادگی : ${it.lastName}")
                        MyText(textSize = 16f, text = "کد ملی : ${it.nationalCode}")
                        MyText(textSize = 16f, text = "معدل : ${it.average}")
                    }
                }
            }
        }

    }

}