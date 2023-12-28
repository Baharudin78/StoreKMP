package com.baharudin.learnkmp.data.core

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.core.Koin
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier

inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualified : Qualifier?,
    noinline definition : Definition<T>,
) : KoinDefinition<T> = factory(qualifier = qualified, definition = definition)