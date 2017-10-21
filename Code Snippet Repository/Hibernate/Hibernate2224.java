	@Override
	public ParameterMetadataImpl getParameterMetadata(String nativeQuery) {
		final ParamLocationRecognizer recognizer = ParamLocationRecognizer.parseLocations( nativeQuery );

		final int size = recognizer.getOrdinalParameterLocationList().size();
		final OrdinalParameterDescriptor[] ordinalDescriptors = new OrdinalParameterDescriptor[ size ];
		for ( int i = 0; i < size; i++ ) {
			final Integer position = recognizer.getOrdinalParameterLocationList().get( i );
			ordinalDescriptors[i] = new OrdinalParameterDescriptor( i, null, position );
		}

		final Map<String, NamedParameterDescriptor> namedParamDescriptorMap = new HashMap<String, NamedParameterDescriptor>();
		final Map<String, ParamLocationRecognizer.NamedParameterDescription> map = recognizer.getNamedParameterDescriptionMap();

		for ( final String name : map.keySet() ) {
			final ParamLocationRecognizer.NamedParameterDescription description = map.get( name );
			namedParamDescriptorMap.put(
					name,
					new NamedParameterDescriptor(
							name,
							null,
							description.buildPositionsArray(),
							description.isJpaStyle()
					)
			);
		}

		return new ParameterMetadataImpl( ordinalDescriptors, namedParamDescriptorMap );
	}
