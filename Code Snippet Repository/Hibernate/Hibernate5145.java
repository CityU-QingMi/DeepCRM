	@Deprecated
	public static int[] findModified(
			final NonIdentifierAttribute[] properties,
			final Object[] currentState,
			final Object[] previousState,
			final boolean[][] includeColumns,
			final boolean[] includeProperties,
			final boolean anyUninitializedProperties,
			final SharedSessionContractImplementor session) {
		return findModified( properties, currentState, previousState, includeColumns, includeProperties, session );
	}
