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
import androidx.compose.ui.unit.dp
import com.playground.movie.R
import com.playground.movie.utils.components.ButtonComponent
import com.playground.movie.utils.components.CheckboxComponent
import com.playground.movie.utils.components.DividerTextComponent
import com.playground.movie.utils.components.HeadingTextComponent
import com.playground.movie.utils.components.MyTextFieldComponent
import com.playground.movie.utils.components.NormalTextComponent
import com.playground.movie.utils.components.PasswordTextFieldComponent

@Composable
fun SignUpScreen(
    navigateToLogin: (String, String) -> Unit,
    navigateToTermsPage: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))

            val firstName = remember { mutableStateOf(String()) }
            val lastName = remember { mutableStateOf(String()) }
            val emailId = remember { mutableStateOf(String()) }
            val password = remember { mutableStateOf(String()) }
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.first_name),
                firstName,
                painterResource(id = R.drawable.profile)
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.last_name),
                lastName,
                painterResource = painterResource(id = R.drawable.profile)
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                emailId,
                painterResource = painterResource(id = R.drawable.message)
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                password,
                painterResource = painterResource(id = R.drawable.ic_lock)
            )

            CheckboxComponent(
                value = stringResource(id = R.string.terms_and_conditions),
                onTextSelected = navigateToTermsPage
            )

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(value = stringResource(id = R.string.register), onClick = {
                //Send send Email and password to login
                navigateToLogin(emailId.value, password.value)
            })

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

        }

    }


}
