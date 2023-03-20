package com.example.weatherapplication.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapplication.R

/**
 * @author Kunal Rathod
 * @since 20 March 2023
 * this function hold the searchBar implementation
 * @param hint what we need to set in text
 * @param onHideKeyBoard to hide the keyboard after search action performed
 * @param onSearchText callback to pass the query which user has searched
 * */

@Composable
fun SearchBarCompose(
    modifier: Modifier = Modifier,
    hint: String = "",
    onHideKeyBoard: () -> Unit,
    onSearchText: (String) -> Unit,
) {

    var query by remember {
        mutableStateOf("")
    }

    var isHintDisplay by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(value = query,
            onValueChange = {
                query = it
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search, keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(
                    Color.White, CircleShape
                )
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplay = !it.isFocused
                })

        if (isHintDisplay) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }

        IconButton(modifier = Modifier
            .align(Alignment.CenterEnd)
            .padding(end = 12.dp), onClick = {
            if (query != "") {
                onSearchText.invoke(query)
                onHideKeyBoard.invoke()
            }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "",
            )
        }
    }


}

@Preview
@Composable
fun getPreviewForSeachBar() {
    //SearchBarCompose()
}