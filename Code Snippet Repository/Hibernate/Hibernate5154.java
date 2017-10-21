	@Deprecated
	public static int[] findDirty(
			final NonIdentifierAttribute[] properties,
			final Object[] currentState,
			final Object[] previousState,
			final boolean[][] includeColumns,
			final boolean anyUninitializedProperties,
			final SharedSessionContractImplementor session) {
		return findDirty( properties, currentState, previousState, includeColumns, session );
	}
