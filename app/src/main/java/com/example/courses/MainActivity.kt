package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.items

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicApp()
                }
            }
        }
    }
}

@Composable
fun TopicCard (topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier,
    ) {
        val grain = painterResource(R.drawable.ic_grain)
        // aspectRatio
        // https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/package-summary#(androidx.compose.ui.Modifier).aspectRatio(kotlin.Float,kotlin.Boolean)
        Row(
            modifier = Modifier
                .background(Color.LightGray),
        ){
            Box {
                Image(
                    painter = painterResource(topic.imageResourceId),
                    contentDescription = stringResource(id = topic.stringResourceId),
                    modifier = Modifier
                        .height(68.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .padding(start=16.dp, end=16.dp,top=16.dp, ),
            ){
                Text(
//                text = LocalContext.current.getString(affirmation.stringResourceId),
                    text = stringResource(id = topic.stringResourceId),
                    modifier = Modifier,
//                        .width(100.dp),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier,
//                        .background(Color.Red),
//                        .fillMaxWidth()
                    horizontalArrangement = Arrangement
                        .spacedBy(
                            space = 8.dp,
                            alignment = Alignment.CenterHorizontally
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                   Image(
                        painter = grain,
                        contentDescription = "grain img",
                        modifier = Modifier
                            .height(24.dp),
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = topic.availableCourses.toString(),
//                        text = "test",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
        }
    }
}

@Composable
fun TopicApp() {
    val topics = DataSource.topics // .loadTopics()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
        if(topics != null){
            items(DataSource.topics){ topic ->
                TopicCard(topic)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoursesTheme {
        TopicApp()
    }
}