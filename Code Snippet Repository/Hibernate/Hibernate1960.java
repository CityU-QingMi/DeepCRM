	public static String[] getLHSColumnNames(
			AssociationType type,
			int property,
			int begin,
			OuterJoinLoadable lhsPersister,
			Mapping mapping) {
		if ( type.useLHSPrimaryKey() ) {
			//return lhsPersister.getSubclassPropertyColumnNames(property);
			return lhsPersister.getIdentifierColumnNames();
		}
		else {
			final String propertyName = type.getLHSPropertyName();
			if ( propertyName == null ) {
				//slice, to get the columns for this component
				//property
				return ArrayHelper.slice(
						property < 0
								? lhsPersister.getIdentifierColumnNames()
								: lhsPersister.getSubclassPropertyColumnNames( property ),
						begin,
						type.getColumnSpan( mapping )
				);
			}
			else {
				//property-refs for associations defined on a
				//component are not supported, so no need to slice
				return lhsPersister.getPropertyColumnNames( propertyName );
			}
		}
	}
