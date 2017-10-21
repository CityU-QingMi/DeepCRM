	public static String[] getAliasedLHSColumnNames(
			AssociationType associationType,
			String columnQualifier,
			int propertyIndex,
			int begin,
			OuterJoinLoadable lhsPersister,
			Mapping mapping) {
		if ( associationType.useLHSPrimaryKey() ) {
			return StringHelper.qualify( columnQualifier, lhsPersister.getIdentifierColumnNames() );
		}
		else {
			final String propertyName = associationType.getLHSPropertyName();
			if ( propertyName == null ) {
				return ArrayHelper.slice(
						toColumns( lhsPersister, columnQualifier, propertyIndex ),
						begin,
						associationType.getColumnSpan( mapping )
				);
			}
			else {
				//bad cast
				return ( (PropertyMapping) lhsPersister ).toColumns( columnQualifier, propertyName );
			}
		}
	}
