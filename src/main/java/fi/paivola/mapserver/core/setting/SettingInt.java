package fi.paivola.mapserver.core.setting;

import fi.paivola.mapserver.utils.RangeInt;
import static java.lang.Integer.parseInt;
import org.json.simple.JSONObject;

/**
 * Class for integer settings.
 * 
 * @author juhani
 */
public class SettingInt extends Setting {
    
    private int value;
    private RangeInt range;
    private int def;

    public SettingInt(String name) {
        super(name);
        this.type = "integer";
        this.range = new RangeInt(0, 17);
        this.value = 0;
        this.def = 0;
    }

    public SettingInt(String name, int value, RangeInt range) {
        super(name);
        this.type = "integer";
        this.range = range;
        this.value = value;
        this.def = 0;
    }
    
    public SettingInt setRange(int start, int end) {
        this.range = new RangeInt(start, end);
        return this;
    }

    @Override
    public Setting setValue(String value) {
        this.value = this.range.clamp(parseInt(value));
        return this;
    }

    @Override
    public String getValue() {
        return (new Integer(value)).toString();
    }

    @Override
    public Setting setDefault(String def) {
        this.def = this.range.clamp(parseInt(def));
        return this;
    }

    @Override
    public JSONObject getJSONObject() {
        JSONObject obj  = new JSONObject();
        
        obj.put("type", this.type);
        obj.put("name", this.name);
        obj.put("default", this.def);
        obj.put("value", this.value);
        obj.put("range", this.range.getJSONObject());
        
        return obj;
    }
    
}
