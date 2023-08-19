package app.revanced.patches.haaretz.fingerprints

import app.revanced.patcher.fingerprint.method.impl.MethodFingerprint

object GetOnboardingDaysLeftFingerprint : MethodFingerprint(
    customFingerprint = { methodDef, _ ->
        methodDef.name == "getOnboardingDaysLeft"
    }
)
