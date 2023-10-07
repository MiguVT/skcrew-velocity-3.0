package com.lotzy.skcrew.spigot.floodgate.forms.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SectionSkriptEvent;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import com.lotzy.skcrew.spigot.floodgate.forms.Form;
import com.lotzy.skcrew.spigot.floodgate.forms.SkriptForm;
import com.lotzy.skcrew.spigot.floodgate.forms.sections.SecCreateCustomForm;
import com.lotzy.skcrew.spigot.floodgate.forms.sections.SecFormResult;
import javax.annotation.Nullable;
import org.bukkit.event.Event;
import org.geysermc.cumulus.util.glue.CustomFormGlue;

@Name("Forms - Dropdown")
@Description("Create dropdown on custom form")
@Examples("Dropdown named \"dropdown\" with elements \"option 1\",\"option 2\"")
@RequiredPlugins("Floodgate")
@Since("1.0")
public class EffDropdown extends Effect {

    static {
        Skript.registerEffect(EffDropdown.class,
            "dropdown (with name|named) %string% (with|and) [elements] %strings%",
            "dropdown (with name|named) %string% (with|and) [elements] %strings%(, | (with|and) ) [def[ault] [(element [index]|index)]] %number%"
        );
    }
    
    int pattern;
    
    Expression<String> name;
    Expression<String> elements;
    Expression<Number> def;
    
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        if (!getParser().isCurrentSection(SecCreateCustomForm.class)) {
            
            SkriptEvent skriptEvent = getParser().getCurrentSkriptEvent();
            if (!(skriptEvent instanceof SectionSkriptEvent) || !((SectionSkriptEvent) skriptEvent).isSection(SecFormResult.class)) {
                Skript.error("You can't make a dropdown outside of a Custom form creation section.",ErrorQuality.SEMANTIC_ERROR);
                return false;
            }
        }
        pattern = matchedPattern;
        
        
        name = (Expression<String>)exprs[0];
        elements = (Expression<String>)exprs[1];
        if(matchedPattern > 0) 
            def = (Expression<Number>)exprs[2]; 
        
        return true;
    }

    @Override
    protected void execute(Event e) {
        Form form = SkriptForm.getFormManager().getForm(e);
        switch(pattern) {
            case 0:
                ((CustomFormGlue.Builder)form.getForm().get())
                    .dropdown(name.getSingle(e), elements.getArray(e));
                break;
            case 1:
                String[] el = elements.getArray(e);
                int def = this.def.getSingle(e).intValue();
                if(def > el.length)
                    def = 1;
                def--;
                ((CustomFormGlue.Builder)form.getForm().get())
                    .dropdown(name.getSingle(e),def,el);
        }
    }
    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "create form dropdown";
    }

}
