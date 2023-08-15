package com.playground.movie.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playground.movie.R
import com.playground.movie.utils.components.ButtonComponent
import com.playground.movie.utils.components.ClickableLoginTextComponent
import com.playground.movie.utils.components.DividerTextComponent
import com.playground.movie.utils.components.HeadingTextComponent
import com.playground.movie.utils.components.MyTextFieldComponent
import com.playground.movie.utils.components.NormalTextComponent
import com.playground.movie.utils.components.PasswordTextFieldComponent
import com.playground.movie.utils.components.UnderLinedTextComponent

@Composable
fun LoginScreen(
    email: String,
    pwd: String,
    loginButtonClick: (String) -> Unit,
    registerButtonClick: (String) -> Unit,
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            NormalTextComponent(value = stringResource(id = R.string.login))
            HeadingTextComponent(value = stringResource(id = R.string.welcome))
            Spacer(modifier = Modifier.height(20.dp))
            val emailId = remember { mutableStateOf(email) }
            val password = remember { mutableStateOf(pwd) }
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                emailId,
                painterResource(id = R.drawable.message)
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                password,
                painterResource(id = R.drawable.lock)
            )

            Spacer(modifier = Modifier.height(40.dp))
            UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(value = stringResource(id = R.string.login), onClick = {
                loginButtonClick.invoke("Login")
            })

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = registerButtonClick)
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen("", "", {}, {})
}