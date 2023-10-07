package com.lotzy.skcrew.spigot.skriptgui.elements.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.lotzy.skcrew.spigot.skriptgui.SkriptGUI;
import com.lotzy.skcrew.spigot.skriptgui.gui.GUI;
import javax.annotation.Nullable;
import org.bukkit.entity.Player;

@Name("SkriptGUI - GUI of Player")
@Description("The GUI that the player currently has open")
@Examples({"edit the player's gui:",
		"\tmake gui 1 with dirt named \"Edited Slot\""})
@Since("1.0")
public class ExprGUI extends SimplePropertyExpression<Player, GUI> {

	static {
		register(ExprGUI.class, GUI.class, "gui", "players");
	}

	@Override
	@Nullable
	public GUI convert(Player player) {
		return SkriptGUI.getGUIManager().getGUI(player);
	}

	@Override
	public Class<? extends GUI> getReturnType() {
		return GUI.class;
	}

	@Override
	protected String getPropertyName() {
		return "gui";
	}

}
