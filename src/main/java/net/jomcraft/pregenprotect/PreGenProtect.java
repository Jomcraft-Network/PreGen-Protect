package net.jomcraft.pregenprotect;

import java.io.InputStream;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.toml.TomlParser;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = PreGenProtect.MODID)
public class PreGenProtect {

	public static final String MODID = "pregen-protect";
	public static final Logger log = LogManager.getLogger(PreGenProtect.MODID);
	public static final String VERSION = getModVersion();
	public static PreGenProtect instance;
	
	public PreGenProtect() {
		instance = this;

			MinecraftForge.EVENT_BUS.register(PreGenProtect.class);

			MinecraftForge.EVENT_BUS.register(new EventHandlers());
			FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
			FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup2);
		
	}
	
	public static PreGenProtect getInstance() {
		return instance;
	}

	private void clientSetup(FMLClientSetupEvent event) {
	
	}
	
	
	
	private void clientSetup2(FMLCommonSetupEvent event) {
		
	}

	
	@SuppressWarnings("unchecked")
	public static String getModVersion() {
		//Stupid FG 3 workaround
		TomlParser parser = new TomlParser();
		InputStream stream = PreGenProtect.class.getClassLoader().getResourceAsStream("META-INF/mods.toml");
		CommentedConfig file = parser.parse(stream);

		return ((ArrayList<CommentedConfig>) file.get("mods")).get(0).get("version");
	}
}