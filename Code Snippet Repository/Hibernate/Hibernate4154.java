	private String determinePkByNaturalIdQuery(boolean[] valueNullness) {
		if ( !hasNaturalIdentifier() ) {
			throw new HibernateException(
					"Attempt to build natural-id -> PK resolution query for entity that does not define natural id"
			);
		}

		// performance shortcut for cases where the natural-id is defined as completely non-nullable
		if ( isNaturalIdNonNullable() ) {
			if ( valueNullness != null && !ArrayHelper.isAllFalse( valueNullness ) ) {
				throw new HibernateException( "Null value(s) passed to lookup by non-nullable natural-id" );
			}
			if ( cachedPkByNonNullableNaturalIdQuery == null ) {
				cachedPkByNonNullableNaturalIdQuery = generateEntityIdByNaturalIdSql( null );
			}
			return cachedPkByNonNullableNaturalIdQuery;
		}

		// Otherwise, regenerate it each time
		return generateEntityIdByNaturalIdSql( valueNullness );
	}
