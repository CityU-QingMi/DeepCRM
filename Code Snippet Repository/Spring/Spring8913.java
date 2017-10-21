	@Nullable
	protected UserTransaction findUserTransaction() {
		String jndiName = DEFAULT_USER_TRANSACTION_NAME;
		try {
			UserTransaction ut = getJndiTemplate().lookup(jndiName, UserTransaction.class);
			if (logger.isDebugEnabled()) {
				logger.debug("JTA UserTransaction found at default JNDI location [" + jndiName + "]");
			}
			this.userTransactionObtainedFromJndi = true;
			return ut;
		}
		catch (NamingException ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("No JTA UserTransaction found at default JNDI location [" + jndiName + "]", ex);
			}
			return null;
		}
	}
