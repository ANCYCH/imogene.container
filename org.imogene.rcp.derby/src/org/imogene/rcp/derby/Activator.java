package org.imogene.rcp.derby;

import java.util.logging.Logger;

import org.apache.derby.drda.NetworkServerControl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	
	private static final Logger logger = Logger.getLogger(Activator.class.getName());
	
	private static BundleContext sContext;

	static BundleContext getContext() {
		return sContext;
	}
	
	private NetworkServerControl mNetworkServerControl;

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		logger.info("Starting Derby");
		Activator.sContext = bundleContext;
		mNetworkServerControl = new NetworkServerControl();
		mNetworkServerControl.start(null);
	}
	
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.sContext = null;
		
		mNetworkServerControl.shutdown();
	}

}
