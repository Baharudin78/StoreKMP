package com.baharudin.learnkmp

import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.baharudin.learnkmp.core.Context
import com.baharudin.learnkmp.di.appModule
import com.baharudin.learnkmp.di.getDataStoreModuleByPlatform
import com.baharudin.learnkmp.presentation.screen.splash.SplashScreen
import org.koin.compose.KoinApplication
import org.koin.dsl.module

@Composable
fun StoreKMPTheme(
    content : @Composable ()-> Unit
) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(primary = Color.Black),
        shapes = MaterialTheme.shapes.copy(
            small = AbsoluteCutCornerShape(0.dp),
            medium = AbsoluteCutCornerShape(0.dp),
            large = AbsoluteCutCornerShape(0.dp)
        )
    ){
        content()
    }
}

@Composable
fun App(){
    KoinApplication(application = {
        modules(getDataStoreModuleByPlatform(), appModule)
    }) {
        StoreKMPTheme {
            Navigator(SplashScreen())
        }
    }
}

expect fun getPlatformName() : String