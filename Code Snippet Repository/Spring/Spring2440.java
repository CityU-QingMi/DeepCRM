	@Override
	public void afterSingletonsInstantiated() {
		try {
			logger.info("Registering beans for JMX exposure on startup");
			registerBeans();
			registerNotificationListeners();
		}
		catch (RuntimeException ex) {
			// Unregister beans already registered by this exporter.
			unregisterNotificationListeners();
			unregisterBeans();
			throw ex;
		}
	}
