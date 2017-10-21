	@Override
	public BatchBuilder initiateService(Map configurationValues, ServiceRegistryImplementor registry) {
		final Object builder = configurationValues.get( BUILDER );
		if ( builder == null ) {
			return new BatchBuilderImpl(
					ConfigurationHelper.getInt( Environment.STATEMENT_BATCH_SIZE, configurationValues, 1 )
			);
		}

		if ( BatchBuilder.class.isInstance( builder ) ) {
			return (BatchBuilder) builder;
		}

		final String builderClassName = builder.toString();
		try {
			return (BatchBuilder) registry.getService( ClassLoaderService.class ).classForName( builderClassName ).newInstance();
		}
		catch (Exception e) {
			throw new ServiceException( "Could not build explicit BatchBuilder [" + builderClassName + "]", e );
		}
	}
