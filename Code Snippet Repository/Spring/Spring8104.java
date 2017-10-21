	public void activate() throws IllegalStateException, NamingException {
		logger.info("Activating simple JNDI environment");
		synchronized (initializationLock) {
			if (!initialized) {
				Assert.state(!NamingManager.hasInitialContextFactoryBuilder(),
							"Cannot activate SimpleNamingContextBuilder: there is already a JNDI provider registered. " +
							"Note that JNDI is a JVM-wide service, shared at the JVM system class loader level, " +
							"with no reset option. As a consequence, a JNDI provider must only be registered once per JVM.");
				NamingManager.setInitialContextFactoryBuilder(this);
				initialized = true;
			}
		}
		activated = this;
	}
