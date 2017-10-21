	public void commitAll() throws JMSException {
		for (Session session : this.sessions) {
			try {
				session.commit();
			}
			catch (TransactionInProgressException ex) {
				// Ignore -> can only happen in case of a JTA transaction.
			}
			catch (javax.jms.IllegalStateException ex) {
				if (this.connectionFactory != null) {
					try {
						Method getDataSourceMethod = this.connectionFactory.getClass().getMethod("getDataSource");
						Object ds = ReflectionUtils.invokeMethod(getDataSourceMethod, this.connectionFactory);
						while (ds != null) {
							if (TransactionSynchronizationManager.hasResource(ds)) {
								// IllegalStateException from sharing the underlying JDBC Connection
								// which typically gets committed first, e.g. with Oracle AQ --> ignore
								return;
							}
							try {
								// Check for decorated DataSource a la Spring's DelegatingDataSource
								Method getTargetDataSourceMethod = ds.getClass().getMethod("getTargetDataSource");
								ds = ReflectionUtils.invokeMethod(getTargetDataSourceMethod, ds);
							}
							catch (NoSuchMethodException nsme) {
								ds = null;
							}
						}
					}
					catch (Throwable ex2) {
						if (logger.isDebugEnabled()) {
							logger.debug("No working getDataSource method found on ConnectionFactory: " + ex2);
						}
						// No working getDataSource method - cannot perform DataSource transaction check
					}
				}
				throw ex;
			}
		}
	}
