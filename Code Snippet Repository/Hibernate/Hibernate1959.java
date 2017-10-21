	private static String[] toColumns(OuterJoinLoadable persister, String columnQualifier, int propertyIndex) {
		if ( propertyIndex >= 0 ) {
			return persister.toColumns( columnQualifier, propertyIndex );
		}
		else {
			final String[] cols = persister.getIdentifierColumnNames();
			final String[] result = new String[cols.length];

			for ( int j = 0; j < cols.length; j++ ) {
				result[j] = StringHelper.qualify( columnQualifier, cols[j] );
			}

			return result;
		}
	}
