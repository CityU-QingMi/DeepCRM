	@Override
	public void stop(BundleContext context) throws Exception {
		osgiServiceUtil.stop();
		osgiServiceUtil = null;
		
		persistenceProviderService.unregister();
		persistenceProviderService = null;
		sessionFactoryService.unregister();
		sessionFactoryService = null;
	}
