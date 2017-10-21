	public LocalSessionFactoryBuilder setJtaTransactionManager(Object jtaTransactionManager) {
		Assert.notNull(jtaTransactionManager, "Transaction manager reference must not be null");

		if (jtaTransactionManager instanceof JtaTransactionManager) {
			boolean webspherePresent = ClassUtils.isPresent("com.ibm.wsspi.uow.UOWManager", getClass().getClassLoader());
			if (webspherePresent) {
				getProperties().put(AvailableSettings.JTA_PLATFORM,
						"org.hibernate.engine.transaction.jta.platform.internal.WebSphereExtendedJtaPlatform");
			}
			else {
				JtaTransactionManager jtaTm = (JtaTransactionManager) jtaTransactionManager;
				if (jtaTm.getTransactionManager() == null) {
					throw new IllegalArgumentException(
							"Can only apply JtaTransactionManager which has a TransactionManager reference set");
				}
				getProperties().put(AvailableSettings.JTA_PLATFORM,
						new ConfigurableJtaPlatform(jtaTm.getTransactionManager(), jtaTm.getUserTransaction(),
								jtaTm.getTransactionSynchronizationRegistry()));
			}
		}
		else if (jtaTransactionManager instanceof TransactionManager) {
			getProperties().put(AvailableSettings.JTA_PLATFORM,
					new ConfigurableJtaPlatform((TransactionManager) jtaTransactionManager, null, null));
		}
		else {
			throw new IllegalArgumentException(
					"Unknown transaction manager type: " + jtaTransactionManager.getClass().getName());
		}

		// Hibernate 5.1/5.2: manually enforce connection release mode AFTER_STATEMENT (the JTA default)
		try {
			// Try Hibernate 5.2
			AvailableSettings.class.getField("CONNECTION_HANDLING");
			getProperties().put("hibernate.connection.handling_mode", "DELAYED_ACQUISITION_AND_RELEASE_AFTER_STATEMENT");
		}
		catch (NoSuchFieldException ex) {
			// Try Hibernate 5.1
			try {
				AvailableSettings.class.getField("ACQUIRE_CONNECTIONS");
				getProperties().put("hibernate.connection.release_mode", "AFTER_STATEMENT");
			}
			catch (NoSuchFieldException ex2) {
				// on Hibernate 5.0.x or lower - no need to change the default there
			}
		}

		return this;
	}
