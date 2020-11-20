package com.hayes.turkeyevent.util;

import org.bukkit.ChatColor;

public class TextUtil {
    public static String convertColor(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String join(Object[] args){
        return join(args, " ", 0);
    }

    public static String join(Object[] args, String combiner){
        return join(args, combiner, 0);
    }

    public static String join(Object[] args, int start){
        return join(args, " ", start);
    }

    public static String join(Object[] args, String combiner, int start){
        if(args.length == 0) return "";

        StringBuilder sb = new StringBuilder();
        for(int i = start; i < args.length; i++){
            if(args[i] == null) continue;
            String s = args[i].toString();
            if(s.equals("")) continue;

            sb.append(convertColor(s));
            if(i != args.length-1){
                sb.append(combiner);
            }
        }

        return sb.substring(0, sb.length());
    }

    public static net.md_5.bungee.api.ChatColor getColor(String hex){
        return net.md_5.bungee.api.ChatColor.of(hex);
    }
}

