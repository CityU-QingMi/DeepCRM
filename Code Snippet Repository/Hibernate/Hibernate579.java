	private MetadataBuilder getCustomBuilderOrDefault(MetadataBuilderImpl defaultBuilder) {
		final ClassLoaderService cls = serviceRegistry.getService( ClassLoaderService.class );
		final java.util.Collection<MetadataBuilderFactory> discoveredBuilderFactories = cls.loadJavaServices( MetadataBuilderFactory.class );

		MetadataBuilder builder = null;
		List<String> activeFactoryNames = null;

		for ( MetadataBuilderFactory discoveredBuilderFactory : discoveredBuilderFactories ) {
			final MetadataBuilder returnedBuilder = discoveredBuilderFactory.getMetadataBuilder( this, defaultBuilder );
			if ( returnedBuilder != null ) {
				if ( activeFactoryNames == null ) {
					activeFactoryNames = new ArrayList<String>();
				}
				activeFactoryNames.add( discoveredBuilderFactory.getClass().getName() );
				builder = returnedBuilder;
			}
		}

		if ( activeFactoryNames != null && activeFactoryNames.size() > 1 ) {
			throw new HibernateException(
					"Multiple active MetadataBuilder definitions were discovered : " +
							StringHelper.join( ", ", activeFactoryNames )
			);
		}

		return builder != null ? builder : defaultBuilder;
	}
