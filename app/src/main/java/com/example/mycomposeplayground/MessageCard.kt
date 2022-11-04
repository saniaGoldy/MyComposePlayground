package com.example.mycomposeplayground

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MessageCard(msg: Message) {
    Box {
        Surface(
            shadowElevation = 7.dp,
            modifier = Modifier
                .padding(5.dp)
                .wrapContentSize()
                .clip(RoundedCornerShape(5)),
            color = MaterialTheme.colorScheme.background
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.big_nob_icon),
                    modifier = Modifier
                        .padding(3.dp)
                        .size(60.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colorScheme.onBackground, CircleShape),
                    contentDescription = "${msg.author} icon"
                )

                Spacer(modifier = Modifier.width(8.dp))

                // We keep track if the message is expanded or not in this
                // variable
                var isExpanded by remember { mutableStateOf(false) }

                // surfaceColor will be updated gradually from one color to the other
                val surfaceColor by animateColorAsState(
                    if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                )

                Column {
                    Text(
                        text = msg.author,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Surface(
                        shape = RoundedCornerShape(10),
                        shadowElevation = 5.dp,
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable { isExpanded = !isExpanded }
                            .animateContentSize(),
                        color = surfaceColor
                    ) {
                        Text(
                            text = msg.body,
                            modifier = Modifier.padding(all = 5.dp),
                            color = if (isExpanded) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CardPreview(){
    MessageCard(SampleData.conversationSample[3])
}