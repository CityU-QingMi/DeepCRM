	public static void deepCopy(
			final Object[] values,
			final Type[] types,
			final boolean[] copy,
			final Object[] target,
			final SharedSessionContractImplementor session) {
		for ( int i = 0; i < types.length; i++ ) {
			if ( copy[i] ) {
				if ( values[i] == LazyPropertyInitializer.UNFETCHED_PROPERTY
					|| values[i] == PropertyAccessStrategyBackRefImpl.UNKNOWN ) {
					target[i] = values[i];
				}
				else {
					target[i] = types[i].deepCopy( values[i], session
						.getFactory() );
				}
			}
		}
	}
