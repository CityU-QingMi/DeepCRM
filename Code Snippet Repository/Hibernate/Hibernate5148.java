	public static void beforeAssemble(
			final Serializable[] row,
			final Type[] types,
			final SharedSessionContractImplementor session) {
		for ( int i = 0; i < types.length; i++ ) {
			if ( row[i] != LazyPropertyInitializer.UNFETCHED_PROPERTY
				&& row[i] != PropertyAccessStrategyBackRefImpl.UNKNOWN ) {
				types[i].beforeAssemble( row[i], session );
			}
		}
	}
