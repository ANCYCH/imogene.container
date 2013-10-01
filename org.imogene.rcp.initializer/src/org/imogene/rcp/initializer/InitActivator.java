package org.imogene.rcp.initializer;

import java.io.File;
import java.util.logging.Logger;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class InitActivator implements BundleActivator {

	private static final Logger logger = Logger.getLogger(InitActivator.class.getName());

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		logger.info("Initialize properties");

		InitActivator.context = bundleContext;

		// Derby properties
		System.setProperty("derby.system.home", createPath("derby"));

		// Jetty properties
		System.setProperty("jetty.home", createPath("jetty"));

		// Web apps properties
		System.setProperty("imogene.binary.path", createPath("binaries"));
		System.setProperty("imogene.identity.path", createPath("identities"));

		// Synchronization properties
		System.setProperty("sync.home", createPath("sync"));
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		InitActivator.context = null;
	}

	private static String createPath(String folder) {
		File file = new File(Platform.getInstanceLocation().getURL().getFile(), folder);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}

}
