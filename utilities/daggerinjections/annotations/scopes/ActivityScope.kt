package com.hellopharmacy.utilities.daggerinjections.annotations.scopes

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

@Scope
@Retention(RetentionPolicy.CLASS)
annotation class ActivityScope