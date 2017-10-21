	@Override
	public final void afterPropertiesSet() throws PersistenceException {
		if (this.jpaVendorAdapter != null) {
			if (this.persistenceProvider == null) {
				this.persistenceProvider = this.jpaVendorAdapter.getPersistenceProvider();
			}
			Map<String, ?> vendorPropertyMap = this.jpaVendorAdapter.getJpaPropertyMap();
			if (vendorPropertyMap != null) {
				vendorPropertyMap.forEach((key, value) -> {
					if (!this.jpaPropertyMap.containsKey(key)) {
						this.jpaPropertyMap.put(key, value);
					}
				});
			}
			if (this.entityManagerFactoryInterface == null) {
				this.entityManagerFactoryInterface = this.jpaVendorAdapter.getEntityManagerFactoryInterface();
				if (!ClassUtils.isVisible(this.entityManagerFactoryInterface, this.beanClassLoader)) {
					this.entityManagerFactoryInterface = EntityManagerFactory.class;
				}
			}
			if (this.entityManagerInterface == null) {
				this.entityManagerInterface = this.jpaVendorAdapter.getEntityManagerInterface();
				if (!ClassUtils.isVisible(this.entityManagerInterface, this.beanClassLoader)) {
					this.entityManagerInterface = EntityManager.class;
				}
			}
			if (this.jpaDialect == null) {
				this.jpaDialect = this.jpaVendorAdapter.getJpaDialect();
			}
		}

		if (this.bootstrapExecutor != null) {
			this.nativeEntityManagerFactoryFuture = this.bootstrapExecutor.submit(
					this::buildNativeEntityManagerFactory);
		}
		else {
			this.nativeEntityManagerFactory = buildNativeEntityManagerFactory();
		}

		// Wrap the EntityManagerFactory in a factory implementing all its interfaces.
		// This allows interception of createEntityManager methods to return an
		// application-managed EntityManager proxy that automatically joins
		// existing transactions.
		this.entityManagerFactory = createEntityManagerFactoryProxy(this.nativeEntityManagerFactory);
	}
