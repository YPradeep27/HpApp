package com.hellopharmacy.utilities.daggerinjections.annotations.scopes

import javax.inject.Scope

/**
 * Defines Application level scope, any field or method annotated with this will have only a single instance
 *
 * Created on ***  on 28/12/2020.
 */
@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope