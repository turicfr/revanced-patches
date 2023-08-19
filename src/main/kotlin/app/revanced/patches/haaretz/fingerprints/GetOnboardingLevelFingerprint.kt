package app.revanced.patches.haaretz.fingerprints

import app.revanced.patcher.fingerprint.method.impl.MethodFingerprint

object GetOnboardingLevelFingerprint : MethodFingerprint(
    customFingerprint = { methodDef, _ ->
        methodDef.name == "getOnboardingLevel"
    }
)
