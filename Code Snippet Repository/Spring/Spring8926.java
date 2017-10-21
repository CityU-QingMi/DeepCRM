	@Override
	public void afterPropertiesSet() throws TransactionSystemException {
		initUserTransactionAndTransactionManager();

		// Fetch UOWManager handle from JNDI, if necessary.
		if (this.uowManager == null) {
			if (this.uowManagerName != null) {
				this.uowManager = lookupUowManager(this.uowManagerName);
			}
			else {
				this.uowManager = lookupDefaultUowManager();
			}
		}
	}
