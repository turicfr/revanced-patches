package app.revanced.patches.haaretz

import app.revanced.extensions.exception
import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.patches.haaretz.fingerprints.GetOnboardingLevelFingerprint
import app.revanced.patches.haaretz.fingerprints.IsPaidUserFingerprint
import app.revanced.patches.haaretz.fingerprints.IsPayerFingerprint

@Patch(
    name = "Unlock trial",
    description = "TODO.",
    compatiblePackages = [CompatiblePackage("com.haaretz")]
)
@Suppress("unused")
object UnlockTrialPatch :
    BytecodePatch(setOf(GetOnboardingLevelFingerprint, IsPaidUserFingerprint, IsPayerFingerprint)) {
    override fun execute(context: BytecodeContext) {
        GetOnboardingLevelFingerprint.result?.mutableMethod?.addInstructions(
            0, """
                const/16 v0, 1
                return v0
            """.trimIndent()
        ) ?: throw GetOnboardingLevelFingerprint.exception

        IsPaidUserFingerprint.result?.mutableMethod?.addInstructions(
            0, """
            const/4 v0, 0x1
            return v0
        """.trimIndent()
        ) ?: throw IsPaidUserFingerprint.exception

        IsPayerFingerprint.result?.mutableMethod?.addInstructions(
            0, """
            const/4 v0, 0x1
            return v0
        """.trimIndent()
        ) ?: throw IsPayerFingerprint.exception

       /* GetOnboardingDaysLeftFingerprint.result?.mutableMethod?.addInstructions(
            0, """
                const/16 v0, 999
                return v0
            """.trimIndent()
        ) ?: throw GetOnboardingDaysLeftFingerprint.exception*/
    }
}
