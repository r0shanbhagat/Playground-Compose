package com.playground.movie.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.playground.movie.R
import com.playground.movie.ui.navigation.AppRouter
import com.playground.movie.utils.components.ButtonComponent
import com.playground.movie.utils.components.CheckboxComponent
import com.playground.movie.utils.components.DividerTextComponent
import com.playground.movie.utils.components.HeadingTextComponent
import com.playground.movie.utils.components.MyTextFieldComponent
import com.playground.movie.utils.components.NormalTextComponent
import com.playground.movie.utils.components.PasswordTextFieldComponent

@Composable
fun SignUpScreen(
    navController: NavHostController
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

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.first_name),
                painterResource(id = R.drawable.profile)
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.last_name),
                painterResource = painterResource(id = R.drawable.profile)
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.message)
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.ic_lock)
            )

            CheckboxComponent(
                value = stringResource(id = R.string.terms_and_conditions),
                onTextSelected = {
                    navController.navigate(AppRouter.TermsAndConditionsScreen.path)
                })

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(value = stringResource(id = R.string.register), onClick = {
                navController.navigate(
                    AppRouter.MovieListScreen.path
                )
            })

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

        }

    }


}
