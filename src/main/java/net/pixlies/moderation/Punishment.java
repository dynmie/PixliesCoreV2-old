package net.pixlies.moderation;

import lombok.Data;
import net.pixlies.economy.Wallet;
import net.pixlies.utils.Palette;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class Punishment {

    private PunishmentType type;
    private UUID punisher;
    private long date;
    private String reason;
    private long until;

    public Punishment(Map<String, Object> mapped) {
        this.type = PunishmentType.valueOf((String) mapped.get("type"));
        this.punisher = UUID.fromString((String) mapped.get("punisher"));
        this.date = (long) mapped.get("date");
        this.reason = (String) mapped.get("reason");
        this.until = (long) mapped.get("until");
    }

    public static Map<String, Punishment> getFromMongo(Map<String, Map<String, Object>> map) {
        Map<String, Punishment> walletMap = new HashMap<>();
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet())
            walletMap.put(entry.getKey(), new Punishment(entry.getValue()));
        return walletMap;
    }

    public Map<String, Object> mapForMongo() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type.name());
        map.put("punisher", punisher.toString());
        map.put("date", date);
        map.put("reason", reason);
        map.put("until", until);
        return map;
    }

    public static Map<String, Map<String, Object>> mapAllForMongo(Map<String, Punishment> punishmentMap) {
        Map<String, Map<String, Object>> map = new HashMap<>();
        for (Map.Entry<String, Punishment> entry : punishmentMap.entrySet())
            map.put(entry.getKey(), entry.getValue().mapForMongo());
        return map;
    }

}