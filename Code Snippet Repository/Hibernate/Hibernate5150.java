	public static Serializable[] disassemble(
			final Object[] row,
			final Type[] types,
			final boolean[] nonCacheable,
			final SharedSessionContractImplementor session,
			final Object owner) {
		Serializable[] disassembled = new Serializable[row.length];
		for ( int i = 0; i < row.length; i++ ) {
			if ( nonCacheable!=null && nonCacheable[i] ) {
				disassembled[i] = LazyPropertyInitializer.UNFETCHED_PROPERTY;
			}
			else if ( row[i] == LazyPropertyInitializer.UNFETCHED_PROPERTY || row[i] == PropertyAccessStrategyBackRefImpl.UNKNOWN ) {
				disassembled[i] = (Serializable) row[i];
			}
			else {
				disassembled[i] = types[i].disassemble( row[i], session, owner );
			}
		}
		return disassembled;
	}
