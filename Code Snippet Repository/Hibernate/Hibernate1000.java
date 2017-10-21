	public BootstrapServiceRegistryImpl(
			boolean autoCloseRegistry,
			ClassLoaderService classLoaderService,
			StrategySelector strategySelector,
			IntegratorService integratorService) {
		this.autoCloseRegistry = autoCloseRegistry;

		this.classLoaderServiceBinding = new ServiceBinding<ClassLoaderService>(
				this,
				ClassLoaderService.class,
				classLoaderService
		);

		this.strategySelectorBinding = new ServiceBinding<StrategySelector>(
				this,
				StrategySelector.class,
				strategySelector
		);

		this.integratorServiceBinding = new ServiceBinding<IntegratorService>(
				this,
				IntegratorService.class,
				integratorService
		);
	}
