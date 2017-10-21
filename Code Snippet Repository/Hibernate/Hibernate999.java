	public BootstrapServiceRegistryImpl(
			boolean autoCloseRegistry,
			ClassLoaderService classLoaderService,
			LinkedHashSet<Integrator> providedIntegrators) {
		this.autoCloseRegistry = autoCloseRegistry;

		this.classLoaderServiceBinding = new ServiceBinding<ClassLoaderService>(
				this,
				ClassLoaderService.class,
				classLoaderService
		);

		final StrategySelectorImpl strategySelector = new StrategySelectorImpl( classLoaderService );
		this.strategySelectorBinding = new ServiceBinding<StrategySelector>(
				this,
				StrategySelector.class,
				strategySelector
		);

		this.integratorServiceBinding = new ServiceBinding<IntegratorService>(
				this,
				IntegratorService.class,
				new IntegratorServiceImpl( providedIntegrators, classLoaderService )
		);
	}
