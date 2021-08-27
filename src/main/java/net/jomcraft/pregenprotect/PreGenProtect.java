package net.jomcraft.pregenprotect;

import java.io.InputStream;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.toml.TomlParser;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

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
	}

	public static PreGenProtect getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public static String getModVersion() {
		// Stupid FG 3 workaround
		final TomlParser parser = new TomlParser();
		final InputStream stream = PreGenProtect.class.getClassLoader().getResourceAsStream("META-INF/mods.toml");
		final CommentedConfig file = parser.parse(stream);

		return ((ArrayList<CommentedConfig>) file.get("mods")).get(0).get("version");
	}
}