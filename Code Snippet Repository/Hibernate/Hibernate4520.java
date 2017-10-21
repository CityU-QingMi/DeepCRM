	public ParameterMetadataImpl(
			OrdinalParameterDescriptor[] ordinalDescriptors,
			Map<String,NamedParameterDescriptor> namedDescriptorMap) {
		if ( ordinalDescriptors == null ) {
			this.ordinalDescriptors = EMPTY_ORDINALS;
		}
		else {
			final OrdinalParameterDescriptor[] copy = new OrdinalParameterDescriptor[ ordinalDescriptors.length ];
			System.arraycopy( ordinalDescriptors, 0, copy, 0, ordinalDescriptors.length );
			this.ordinalDescriptors = copy;
		}

		if ( namedDescriptorMap == null ) {
			this.namedDescriptorMap = java.util.Collections.emptyMap();
		}
		else {
			final int size = (int) ( ( namedDescriptorMap.size() / .75 ) + 1 );
			final Map<String,NamedParameterDescriptor> copy = new HashMap<>( size );
			copy.putAll( namedDescriptorMap );
			this.namedDescriptorMap = java.util.Collections.unmodifiableMap( copy );
		}
	}
