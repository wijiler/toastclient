package dev.toastmc.client.command.cmds

import dev.toastmc.client.command.Command
import dev.toastmc.client.command.CommandManifest
import dev.toastmc.client.util.MessageUtil
import net.minecraft.client.network.ClientPlayerEntity
import java.text.DecimalFormat

@CommandManifest(
        label = "Coords",
        description = "Copy player coordinates to clipboard.",
        aliases = []
)
class Coords : Command() {
    override fun run(args: Array<String>) {
        mc.keyboard.clipboard = formatPlayerCoords(mc.player)
        MessageUtil.sendMessage("Copied coordinates to clipboard!", MessageUtil.Color.GREEN)
    }

    private fun formatPlayerCoords(player: ClientPlayerEntity?): String? {
        val format = DecimalFormat("#.#")
        val x: String = format.format(player?.x)
        val y: String = format.format(player?.y)
        val z: String = format.format(player?.z)
        return "$x, $y, $z"
    }
}