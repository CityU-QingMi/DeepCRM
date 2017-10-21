	@Override
	public Class getIdentifierGeneratorClass(String strategy) {
		if ( "hilo".equals( strategy ) ) {
			throw new UnsupportedOperationException( "Support for 'hilo' generator has been removed" );
		}
		String resolvedStrategy = "native".equals( strategy ) ?
				getDialect().getNativeIdentifierGeneratorStrategy() : strategy;

		Class generatorClass = generatorStrategyToClassNameMap.get( resolvedStrategy );
		try {
			if ( generatorClass == null ) {
				final ClassLoaderService cls = serviceRegistry.getService( ClassLoaderService.class );
				generatorClass = cls.classForName( resolvedStrategy );
			}
		}
		catch ( ClassLoadingException e ) {
			throw new MappingException( String.format( "Could not interpret id generator strategy [%s]", strategy ) );
		}
		return generatorClass;
	}
