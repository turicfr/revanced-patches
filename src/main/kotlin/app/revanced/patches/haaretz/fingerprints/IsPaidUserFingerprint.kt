package app.revanced.patches.haaretz.fingerprints

import app.revanced.patcher.fingerprint.method.impl.MethodFingerprint

object IsPaidUserFingerprint : MethodFingerprint(
    customFingerprint = { methodDef, _ ->
        methodDef.name == "isPaidUser"

    }
)
