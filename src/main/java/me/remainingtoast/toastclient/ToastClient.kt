package me.remainingtoast.toastclient

import me.remainingtoast.toastclient.api.module.ModuleManager
import me.remainingtoast.toastclient.api.setting.SettingManager
import me.remainingtoast.toastclient.api.util.mc
import me.remainingtoast.toastclient.api.gui.ToastGUI
import me.remainingtoast.toastclient.client.module.gui.ClickGUIModule
import me.zero.alpine.bus.EventBus
import me.zero.alpine.bus.EventManager
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.options.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

class ToastClient : ModInitializer {

    companion object {
        val MODNAME = "Toast Client"
        val MODVER = "2.0.1"
        val SETTING_MANAGER = SettingManager()
        val MODULE_MANAGER = ModuleManager()
        val CLICKGUI = ToastGUI()

        var CMD_PREFIX = "."

        @JvmField
        val EVENT_BUS: EventBus = EventManager()
    }

    override fun onInitialize() {
        println("${MODNAME.toUpperCase()} $MODVER STARTING")

        Runtime.getRuntime().addShutdownHook(Thread {
            println("${MODNAME.toUpperCase()} SAVING AND SHUTTING DOWN")
        })
    }
}