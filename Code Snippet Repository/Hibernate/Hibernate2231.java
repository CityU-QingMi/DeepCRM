	private ParameterMetadataImpl buildParameterMetadata(ParameterTranslations parameterTranslations, String hql) {
		final long start = traceEnabled ? System.nanoTime() : 0;
		final ParamLocationRecognizer recognizer = ParamLocationRecognizer.parseLocations( hql );

		if ( traceEnabled ) {
			final long end = System.nanoTime();
			LOG.tracev( "HQL param location recognition took {0} nanoseconds ({1})", ( end - start ), hql );
		}

		int ordinalParamCount = parameterTranslations.getOrdinalParameterCount();
		final int[] locations = ArrayHelper.toIntArray( recognizer.getOrdinalParameterLocationList() );
		if ( parameterTranslations.supportsOrdinalParameterMetadata() && locations.length != ordinalParamCount ) {
			throw new HibernateException( "ordinal parameter mismatch" );
		}
		ordinalParamCount = locations.length;

		final OrdinalParameterDescriptor[] ordinalParamDescriptors = new OrdinalParameterDescriptor[ordinalParamCount];
		for ( int i = 0; i < ordinalParamCount; i++ ) {
			ordinalParamDescriptors[ i ] = new OrdinalParameterDescriptor(
					i,
					parameterTranslations.supportsOrdinalParameterMetadata()
							? parameterTranslations.getOrdinalParameterExpectedType( i )
							: null,
					locations[ i ]
			);
		}

		final Map<String, NamedParameterDescriptor> namedParamDescriptorMap = new HashMap<String, NamedParameterDescriptor>();
		final Map<String, ParamLocationRecognizer.NamedParameterDescription> map = recognizer.getNamedParameterDescriptionMap();
		for ( final String name : map.keySet() ) {
			final ParamLocationRecognizer.NamedParameterDescription description = map.get( name );
			namedParamDescriptorMap.put(
					name,
					new NamedParameterDescriptor(
							name,
							parameterTranslations.getNamedParameterExpectedType( name ),
							description.buildPositionsArray(),
							description.isJpaStyle()
					)
			);
		}
		return new ParameterMetadataImpl( ordinalParamDescriptors, namedParamDescriptorMap );
	}
