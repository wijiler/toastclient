package me.remainingtoast.toastclient.api.module

import com.lukflug.panelstudio.settings.Toggleable
import me.remainingtoast.toastclient.ToastClient
import me.remainingtoast.toastclient.api.setting.type.*
import java.awt.Color

open class Module : com.lukflug.panelstudio.settings.KeybindSetting,Toggleable {

    var name: String = ""
    private var category: Category = Category.NONE
    private var color: Color = Color.BLACK

    private var enabled = false
    private var drawn = false
    private var bind = 0

    private var alwaysEnabled = false

    constructor(name: String, category: Category) {
        this.name = name
        this.category = category
        enabled = false
        drawn = true
    }

    constructor(name: String, category: Category, bool: Boolean) {
        this.name = name
        this.category = category
        this.alwaysEnabled = bool
        enabled = false
        drawn = true
    }

    constructor(name: String, category: Category, key: Int) {
        this.name = name
        this.category = category
        this.bind = key
        enabled = false
        drawn = true
    }

    open fun setColor(newColor: Color) {
        color = newColor
    }

    open fun getCategory(): Category {
        return this.category
    }

    open fun getColor(): Color {
        return color
    }

    open fun isEnabled(): Boolean {
        return enabled
    }

    open fun disable() {
        setEnabled(false)
    }

    open fun enable() {
        setEnabled(true)
    }

    open fun setEnabled(newVal: Boolean) {
        this.enabled = newVal
        if (newVal) {
            onEnable()
        } else {
            onDisable()
        }
    }

    open fun isDrawn(): Boolean {
        return drawn
    }

    open fun setDrawn(drawn: Boolean) {
        this.drawn = drawn
    }

    open fun getBind(): Int {
        return bind
    }

    open fun setBind(bind: Int) {
        this.bind = bind
    }

    override fun toggle() {
        setEnabled(!isEnabled())
    }

    open fun getHudInfo(): String {
        return ""
    }

    protected open fun onEnable() {}

    protected open fun onDisable() {}

    open fun onUpdate() {}

    open fun onOverlayRender() {}

    /** Setting registry functions below!  */
    protected open fun registerBoolean(name: String, description: String, value: Boolean): BooleanSetting {
        val setting = BooleanSetting(name, description, this, value)
        ToastClient.SETTING_MANAGER.addSetting(setting)
        return setting
    }

    protected open fun registerBoolean(name: String, value: Boolean): BooleanSetting {
        val setting = BooleanSetting(name, "", this, value)
        ToastClient.SETTING_MANAGER.addSetting(setting)
        return setting
    }

    protected open fun registerColor(
        name: String,
        description: String,
        rainbowEnabled: Boolean,
        alphaEnabled: Boolean,
        value: Color,
        rainbow: Boolean
    ): ColorSetting {
        val setting = ColorSetting(value, rainbow, name, rainbowEnabled, alphaEnabled, description, this)
        ToastClient.SETTING_MANAGER.addSetting(setting)
        return setting
    }

    protected open fun registerDouble(
        name: String,
        description: String,
        value: Double,
        min: Double,
        max: Double,
        isLimited: Boolean
    ): DoubleSetting {
        val setting = DoubleSetting(value, name, description, this, min, max, isLimited)
        ToastClient.SETTING_MANAGER.addSetting(setting)
        return setting
    }

    protected open fun <T : Enum<T>> registerEnum(value: T, name: String, description: String): EnumSetting<T> {
        val setting: EnumSetting<T> = EnumSetting<T>(value, name, description, this)
        ToastClient.SETTING_MANAGER.addSetting(setting)
        return setting
    }

    protected open fun registerInteger(
        name: String,
        description: String,
        value: Int,
        min: Int,
        max: Int,
        isLimited: Boolean
    ): IntegerSetting {
        val setting = IntegerSetting(value, name, description, this, min, max, isLimited)
        ToastClient.SETTING_MANAGER.addSetting(setting)
        return setting
    }

    protected open fun registerKeybind(name: String, description: String, value: Int): KeybindSetting {
        val setting: KeybindSetting = KeybindSetting(value, name, description, this)
        ToastClient.SETTING_MANAGER.addSetting(setting)
        return setting
    }

    override fun getKey(): Int {
        return bind
    }

    override fun getKeyName(): String {
        return KeybindSetting.getKeyName(bind)
    }

    override fun setKey(key: Int) {
        bind = key
    }

    override fun isOn(): Boolean {
        return if(alwaysEnabled) true else enabled
    }
}