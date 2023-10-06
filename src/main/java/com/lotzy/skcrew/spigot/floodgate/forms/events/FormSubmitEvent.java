package com.lotzy.skcrew.floodgate.forms.events;

import com.lotzy.skcrew.floodgate.forms.Form;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.geysermc.cumulus.response.FormResponse;
import org.jetbrains.annotations.NotNull;

public class FormSubmitEvent extends Event {
    
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final FormResponse response;
    private final Form form;

    public FormSubmitEvent(Player player, Form form, FormResponse res) {
        this.player = player;
        this.response = res;
        this.form = form;

    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Form getForm() {
        return this.form;
    }

    public FormResponse getResponse() {
        return this.response;
    }
}
