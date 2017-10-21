	protected UOWManager lookupDefaultUowManager() throws TransactionSystemException {
		try {
			logger.debug("Retrieving WebSphere UOWManager from default JNDI location [" + DEFAULT_UOW_MANAGER_NAME + "]");
			return getJndiTemplate().lookup(DEFAULT_UOW_MANAGER_NAME, UOWManager.class);
		}
		catch (NamingException ex) {
			logger.debug("WebSphere UOWManager is not available at default JNDI location [" +
					DEFAULT_UOW_MANAGER_NAME + "] - falling back to UOWManagerFactory lookup");
			return UOWManagerFactory.getUOWManager();
		}
	}
