	public static String expandBatchIdPlaceholder(
			String sql,
			Serializable[] ids,
			String alias,
			String[] keyColumnNames,
			Dialect dialect) {
		if ( keyColumnNames.length == 1 ) {
			// non-composite
			return StringHelper.replace( sql, BATCH_ID_PLACEHOLDER, repeat( "?", ids.length, "," ) );
		}
		else {
			// composite
			if ( dialect.supportsRowValueConstructorSyntaxInInList() ) {
				final String tuple = "(" + StringHelper.repeat( "?", keyColumnNames.length, "," ) + ")";
				return StringHelper.replace( sql, BATCH_ID_PLACEHOLDER, repeat( tuple, ids.length, "," ) );
			}
			else {
				final String keyCheck = "(" + joinWithQualifierAndSuffix(
						keyColumnNames,
						alias,
						" = ?",
						" and "
				) + ")";
				return replace( sql, BATCH_ID_PLACEHOLDER, repeat( keyCheck, ids.length, " or " ) );
			}
		}
	}
