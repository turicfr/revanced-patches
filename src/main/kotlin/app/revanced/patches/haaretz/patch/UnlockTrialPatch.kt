package app.revanced.patches.haaretz.patch

import app.revanced.patcher.annotation.Description
import app.revanced.patcher.annotation.Name
import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.PatchException
import app.revanced.patcher.patch.annotations.Patch
import app.revanced.patches.haaretz.fingerprints.GetOnboardingLevelFingerprint
import app.revanced.patches.haaretz.fingerprints.IsPaidUserFingerprint
import app.revanced.patches.haaretz.fingerprints.IsPayerFingerprint
import app.revanced.patches.haaretz.annotations.UnlockTrialCompatibility

@Patch
@Name("Unlock trial")
@Description("TODO.")
@UnlockTrialCompatibility
class UnlockTrialPatch : BytecodePatch(
    listOf(
        GetOnboardingLevelFingerprint,
        IsPaidUserFingerprint,
        IsPayerFingerprint
    )
) {
    override fun execute(context: BytecodeContext) {
        val onboardingLevel = GetOnboardingLevelFingerprint.result
            ?: throw PatchException("GetOnboardingLevelFingerprint not found")
        onboardingLevel.mutableMethod.addInstructions(
            0, """
            const/16 v0, 1
            return v0
        """.trimIndent()
        )

        val isPaidUser =
            IsPaidUserFingerprint.result ?: throw PatchException("IsPaidUserFingerprint not found")
        isPaidUser.mutableMethod.addInstructions(
            0, """
            const/4 v0, 0x1
            return v0
        """.trimIndent()
        )

        val isPayer =
            IsPayerFingerprint.result ?: throw PatchException("IsPayerFingerprint not found")
        isPayer.mutableMethod.addInstructions(
            0, """
            const/4 v0, 0x1
            return v0
        """.trimIndent()
        )

        /*val daysLeft = GetOnboardingDaysLeftFingerprint.result
            ?: return PatchResultError("GetOnboardingDaysLeftFingerprint not found")
        daysLeft.mutableMethod.addInstructions(0,
            """
                const/16 v0, 999
                return v0
            """.trimIndent()
        )*/
    }
}
