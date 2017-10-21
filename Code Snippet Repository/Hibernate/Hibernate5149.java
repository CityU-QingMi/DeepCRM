	public static Object[] assemble(
			final Serializable[] row,
			final Type[] types,
			final SharedSessionContractImplementor session,
			final Object owner) {
		Object[] assembled = new Object[row.length];
		for ( int i = 0; i < types.length; i++ ) {
			if ( row[i] == LazyPropertyInitializer.UNFETCHED_PROPERTY || row[i] == PropertyAccessStrategyBackRefImpl.UNKNOWN ) {
				assembled[i] = row[i];
			}
			else {
				assembled[i] = types[i].assemble( row[i], session, owner );
			}
		}
		return assembled;
	}
