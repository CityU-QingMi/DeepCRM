	public static Object[] replace(
			final Object[] original,
			final Object[] target,
			final Type[] types,
			final SharedSessionContractImplementor session,
			final Object owner,
			final Map copyCache) {
		Object[] copied = new Object[original.length];
		for ( int i = 0; i < types.length; i++ ) {
			if ( original[i] == LazyPropertyInitializer.UNFETCHED_PROPERTY
				|| original[i] == PropertyAccessStrategyBackRefImpl.UNKNOWN ) {
				copied[i] = target[i];
			}
			else if ( target[i] == LazyPropertyInitializer.UNFETCHED_PROPERTY ) {
				// Should be no need to check for target[i] == PropertyAccessStrategyBackRefImpl.UNKNOWN
				// because PropertyAccessStrategyBackRefImpl.get( object ) returns
				// PropertyAccessStrategyBackRefImpl.UNKNOWN, so target[i] == original[i].
				//
				// We know from above that original[i] != LazyPropertyInitializer.UNFETCHED_PROPERTY &&
				// original[i] != PropertyAccessStrategyBackRefImpl.UNKNOWN;
				// This is a case where the entity being merged has a lazy property
				// that has been initialized. Copy the initialized value from original.
				if ( types[i].isMutable() ) {
					copied[i] = types[i].deepCopy( original[i], session.getFactory() );
				}
				else {
					copied[i] = original[i];
				}
			}
			else {
				copied[i] = types[i].replace( original[i], target[i], session, owner, copyCache );
			}
		}
		return copied;
	}
