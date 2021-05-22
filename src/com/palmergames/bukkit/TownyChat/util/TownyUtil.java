package com.palmergames.bukkit.TownyChat.util;

import com.palmergames.bukkit.towny.Towny;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TownyUtil {

	public TownyUtil() {
	}
	
	public static boolean removePlayerMode(Towny towny, Player player, String mode, boolean notify) {
		if (!towny.hasPlayerMode(player, mode)) {
			return false;
		}
		
		List<String> modes = towny.getPlayerMode(player);
		int newmodecount = modes.size();
		Iterator<String> iter = modes.iterator();
		while (iter.hasNext()) {
			String s = iter.next();
			if (mode.equalsIgnoreCase(s)) {
				iter.remove();
				towny.setPlayerMode(player, modes.toArray(new String[newmodecount-1]), notify);
				return true;
			}
		}
		return false;
	}

	public static void removeAndSetPlayerMode(Towny towny, Player player, String removeMode, String addMode, boolean notify) {
		List<String> modes = towny.getPlayerMode(player);
		List<String> newModes = new ArrayList<>();
		boolean modesChanged = false;
		if (removeMode != null && towny.hasPlayerMode(player, removeMode)) {
			for (String mode : modes) {
				if (!mode.equalsIgnoreCase(removeMode))
					newModes.add(mode);
			}
			modesChanged = true;
		}
		if (addMode != null) {
			newModes.add(addMode);
			modesChanged = true;
		}
		if (modesChanged) {
			if (newModes.isEmpty())
				towny.removePlayerMode(player);
			else 
				towny.setPlayerMode(player, newModes.toArray(new String[0]), notify);
		}
	}
}
