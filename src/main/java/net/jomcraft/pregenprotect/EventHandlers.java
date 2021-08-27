package net.jomcraft.pregenprotect;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import pregenerator.common.manager.ServerManager;

public class EventHandlers {
	
	@SubscribeEvent
	public void onRenderTick(TickEvent.ServerTickEvent event) {
		if (event.phase == Phase.END && ServerLifecycleHooks.getCurrentServer().getTickCount() % 600 == 0) {

			final int mb = 1024 * 1024;

			int ram = (int) ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / mb);
			PreGenProtect.log.info("Used-Ram: " + ram);
			if (ram > 12000) {
				if (ServerManager.INSTANCE.isRunning())
					ServerManager.INSTANCE.pauseTask(null, x -> {});
				ServerLifecycleHooks.getCurrentServer().halt(false);
			}

		}

	}
	
}