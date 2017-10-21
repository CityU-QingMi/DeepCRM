	public BootstrapServiceRegistry build() {
		final ClassLoaderService classLoaderService;
		if ( providedClassLoaderService == null ) {
			// Use a set.  As an example, in JPA, OsgiClassLoader may be in both
			// the providedClassLoaders and the overridenClassLoader.
			final Set<ClassLoader> classLoaders = new HashSet<ClassLoader>();

			if ( providedClassLoaders != null )  {
				classLoaders.addAll( providedClassLoaders );
			}
			
			classLoaderService = new ClassLoaderServiceImpl( classLoaders,tcclLookupPrecedence );
		}
		else {
			classLoaderService = providedClassLoaderService;
		}

		final IntegratorServiceImpl integratorService = new IntegratorServiceImpl(
				providedIntegrators,
				classLoaderService
		);

		return new BootstrapServiceRegistryImpl(
				autoCloseRegistry,
				classLoaderService,
				strategySelectorBuilder.buildSelector( classLoaderService ),
				integratorService
		);
	}
