package app.revanced.patches.haaretz.annotations

import app.revanced.patcher.annotation.Compatibility
import app.revanced.patcher.annotation.Package

@Compatibility([Package("com.haaretz", arrayOf("4.7.4", "4.7.49"))])
@Target(AnnotationTarget.CLASS)
internal annotation class UnlockTrialCompatibility
