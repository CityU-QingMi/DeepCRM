	public static int[] findDirty(
			final NonIdentifierAttribute[] properties,
			final Object[] currentState,
			final Object[] previousState,
			final boolean[][] includeColumns,
			final SharedSessionContractImplementor session) {
		int[] results = null;
		int count = 0;
		int span = properties.length;

		for ( int i = 0; i < span; i++ ) {
			final boolean dirty = currentState[i] != LazyPropertyInitializer.UNFETCHED_PROPERTY
					&& properties[i].isDirtyCheckable()
					&& properties[i].getType().isDirty( previousState[i], currentState[i], includeColumns[i], session );
			if ( dirty ) {
				if ( results == null ) {
					results = new int[span];
				}
				results[count++] = i;
			}
		}

		if ( count == 0 ) {
			return null;
		}
		else {
			int[] trimmed = new int[count];
			System.arraycopy( results, 0, trimmed, 0, count );
			return trimmed;
		}
	}
