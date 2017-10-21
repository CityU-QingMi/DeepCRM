	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		// check first for the strategy instance
		strategy = (UUIDGenerationStrategy) params.get( UUID_GEN_STRATEGY );
		if ( strategy == null ) {
			// next check for the strategy class
			final String strategyClassName = params.getProperty( UUID_GEN_STRATEGY_CLASS );
			if ( strategyClassName != null ) {
				try {
					final ClassLoaderService cls = serviceRegistry.getService( ClassLoaderService.class );
					final Class strategyClass = cls.classForName( strategyClassName );
					try {
						strategy = (UUIDGenerationStrategy) strategyClass.newInstance();
					}
					catch ( Exception ignore ) {
						LOG.unableToInstantiateUuidGenerationStrategy(ignore);
					}
				}
				catch ( ClassLoadingException ignore ) {
					LOG.unableToLocateUuidGenerationStrategy( strategyClassName );
				}
			}
		}
		if ( strategy == null ) {
			// lastly use the standard random generator
			strategy = StandardRandomStrategy.INSTANCE;
		}

		if ( UUID.class.isAssignableFrom( type.getReturnedClass() ) ) {
			valueTransformer = UUIDTypeDescriptor.PassThroughTransformer.INSTANCE;
		}
		else if ( String.class.isAssignableFrom( type.getReturnedClass() ) ) {
			valueTransformer = UUIDTypeDescriptor.ToStringTransformer.INSTANCE;
		}
		else if ( byte[].class.isAssignableFrom( type.getReturnedClass() ) ) {
			valueTransformer = UUIDTypeDescriptor.ToBytesTransformer.INSTANCE;
		}
		else {
			throw new HibernateException( "Unanticipated return type [" + type.getReturnedClass().getName() + "] for UUID conversion" );
		}
	}
