	@Override
	public void initialize() {
		// Set sessionTransacted=true in case of a non-JTA transaction manager.
		if (!this.sessionTransactedCalled &&
				this.transactionManager instanceof ResourceTransactionManager &&
				!TransactionSynchronizationUtils.sameResourceFactory(
						(ResourceTransactionManager) this.transactionManager, obtainConnectionFactory())) {
			super.setSessionTransacted(true);
		}

		// Use bean name as default transaction name.
		if (this.transactionDefinition.getName() == null) {
			String beanName = getBeanName();
			if (beanName != null) {
				this.transactionDefinition.setName(beanName);
			}
		}

		// Proceed with superclass initialization.
		super.initialize();
	}
