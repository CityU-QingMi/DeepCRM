	@Deprecated
	@SuppressWarnings({"", "", ""})
	public static ClassLoaderServiceImpl fromConfigSettings(Map configValues) {
		final List<ClassLoader> providedClassLoaders = new ArrayList<ClassLoader>();

		final Collection<ClassLoader> classLoaders = (Collection<ClassLoader>) configValues.get( AvailableSettings.CLASSLOADERS );
		if ( classLoaders != null ) {
			for ( ClassLoader classLoader : classLoaders ) {
				providedClassLoaders.add( classLoader );
			}
		}

		addIfSet( providedClassLoaders, AvailableSettings.APP_CLASSLOADER, configValues );
		addIfSet( providedClassLoaders, AvailableSettings.RESOURCES_CLASSLOADER, configValues );
		addIfSet( providedClassLoaders, AvailableSettings.HIBERNATE_CLASSLOADER, configValues );
		addIfSet( providedClassLoaders, AvailableSettings.ENVIRONMENT_CLASSLOADER, configValues );

		return new ClassLoaderServiceImpl( providedClassLoaders,TcclLookupPrecedence.AFTER );
	}
