	@Nullable
	protected TransactionManager findTransactionManager(UserTransaction ut) {
		if (ut instanceof TransactionManager) {
			if (logger.isDebugEnabled()) {
				logger.debug("JTA UserTransaction object [" + ut + "] implements TransactionManager");
			}
			return (TransactionManager) ut;
		}

		// Check fallback JNDI locations.
		for (String jndiName : FALLBACK_TRANSACTION_MANAGER_NAMES) {
			try {
				TransactionManager tm = getJndiTemplate().lookup(jndiName, TransactionManager.class);
				if (logger.isDebugEnabled()) {
					logger.debug("JTA TransactionManager found at fallback JNDI location [" + jndiName + "]");
				}
				return tm;
			}
			catch (NamingException ex) {
				if (logger.isDebugEnabled()) {
					logger.debug("No JTA TransactionManager found at fallback JNDI location [" + jndiName + "]", ex);
				}
			}
		}

		// OK, so no JTA TransactionManager is available...
		return null;
	}
