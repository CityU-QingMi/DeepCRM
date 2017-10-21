	protected UOWManager lookupUowManager(String uowManagerName) throws TransactionSystemException {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Retrieving WebSphere UOWManager from JNDI location [" + uowManagerName + "]");
			}
			return getJndiTemplate().lookup(uowManagerName, UOWManager.class);
		}
		catch (NamingException ex) {
			throw new TransactionSystemException(
					"WebSphere UOWManager is not available at JNDI location [" + uowManagerName + "]", ex);
		}
	}
