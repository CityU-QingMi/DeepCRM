	@Deprecated
	public Type[] collectPositionalBindTypes() {
		Type[] types = new Type[ positionalParameterBindings.size() ];

		// NOTE : bindings should be ordered by position by nature of a TreeMap...
		// NOTE : we also assume the contiguity of the positions

		for ( Map.Entry<Integer, QueryParameterBinding> entry : positionalParameterBindings.entrySet() ) {
			final int position = entry.getKey();

			Type type = entry.getValue().getBindType();
			if ( type == null ) {
				log.debugf( "Binding for positional-parameter [%s] did not define type, using SerializableType", position );
				type = SerializableType.INSTANCE;
			}

			types[ position ] = type;
		}

		return types;
	}
