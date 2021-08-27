package net.jomcraft.pregenprotect;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import pregenerator.common.manager.ServerManager;

public class EventHandlers {
	
	/*public static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	
	//public static volatile MinecraftServer server = null; 
	
	//public static volatile boolean end = false;
	
	static {
		executor.scheduleAtFixedRate(() -> {
			
			if(Thread.interrupted())
				return;
			MinecraftServer l = ServerLifecycleHooks.getCurrentServer();
		if(!l.isRunning())
			return;
		
			//if(server != null) {
			//synchronized (server) {
			//ServerManager.INSTANCE.pauseTask(null, null);
					//PreGenProtect.log.error("asdsadasd " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024));
			
			try {
			int ram = (int) ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024);
			
			if(ram > 12000) {
				if(ServerManager.INSTANCE.isRunning())
					ServerManager.INSTANCE.pauseTask(null, x -> {});
				ServerLifecycleHooks.getCurrentServer().halt(false);
			}
			PreGenProtect.log.error(ram);
			} catch (Exception e) {
				PreGenProtect.log.error(e);
			}
					//
			
				//}
			////}
		}, 60, 30, TimeUnit.SECONDS);
	}*/
	
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
	
	/*

	

	@SubscribeEvent
	public void serverStarting(FMLServerStartingEvent event) {
		
		//synchronized (server) {
			server = ((MinecraftServer) LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER));
		//}
	}
	
	@SubscribeEvent
	public void serverStarting(FMLServerStoppingEvent event) {
		
		//synchronized (server) {
		//end = true;
			executor.shutdownNow();
		//}
	}*/

	
}