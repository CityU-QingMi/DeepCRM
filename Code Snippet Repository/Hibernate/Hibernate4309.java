	@Deprecated
	@SuppressWarnings("")
	default Query<R> setParameters(Object[] values, Type[] types) {
		assert values.length == types.length;
		for ( int i = 0; i < values.length; i++ ) {
			setParameter( i, values[i], types[i] );
		}

		return this;
	}
