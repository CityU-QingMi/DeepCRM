	public static int[] findModified(
			final NonIdentifierAttribute[] properties,
			final Object[] currentState,
			final Object[] previousState,
			final boolean[][] includeColumns,
			final boolean[] includeProperties,
			final SharedSessionContractImplementor session) {
		int[] results = null;
		int count = 0;
		int span = properties.length;

		for ( int i = 0; i < span; i++ ) {
			final boolean modified = currentState[ i ] != LazyPropertyInitializer.UNFETCHED_PROPERTY
					&& includeProperties[ i ]
					&& properties[ i ].isDirtyCheckable()
					&& properties[ i ].getType().isModified( previousState[ i ], currentState[ i ], includeColumns[ i ], session );
			if ( modified ) {
				if ( results == null ) {
					results = new int[ span ];
				}
				results[ count++ ] = i;
			}
		}

		if ( count == 0 ) {
			return null;
		}
		else {
			int[] trimmed = new int[ count ];
			System.arraycopy( results, 0, trimmed, 0, count );
			return trimmed;
		}
	}
