	private String generateEntityIdByNaturalIdSql(boolean[] valueNullness) {
		EntityPersister rootPersister = getFactory().getEntityPersister( getRootEntityName() );
		if ( rootPersister != this ) {
			if ( rootPersister instanceof AbstractEntityPersister ) {
				return ( (AbstractEntityPersister) rootPersister ).generateEntityIdByNaturalIdSql( valueNullness );
			}
		}

		Select select = new Select( getFactory().getDialect() );
		if ( getFactory().getSessionFactoryOptions().isCommentsEnabled() ) {
			select.setComment( "get current natural-id->entity-id state " + getEntityName() );
		}

		final String rootAlias = getRootAlias();

		select.setSelectClause( identifierSelectFragment( rootAlias, "" ) );
		select.setFromClause( fromTableFragment( rootAlias ) + fromJoinFragment( rootAlias, true, false ) );

		final StringBuilder whereClause = new StringBuilder();
		final int[] propertyTableNumbers = getPropertyTableNumbers();
		final int[] naturalIdPropertyIndexes = this.getNaturalIdentifierProperties();
		int valuesIndex = -1;
		for ( int propIdx = 0; propIdx < naturalIdPropertyIndexes.length; propIdx++ ) {
			valuesIndex++;
			if ( propIdx > 0 ) {
				whereClause.append( " and " );
			}

			final int naturalIdIdx = naturalIdPropertyIndexes[propIdx];
			final String tableAlias = generateTableAlias( rootAlias, propertyTableNumbers[naturalIdIdx] );
			final String[] propertyColumnNames = getPropertyColumnNames( naturalIdIdx );
			final String[] aliasedPropertyColumns = StringHelper.qualify( tableAlias, propertyColumnNames );

			if ( valueNullness != null && valueNullness[valuesIndex] ) {
				whereClause.append( StringHelper.join( " is null and ", aliasedPropertyColumns ) ).append( " is null" );
			}
			else {
				whereClause.append( StringHelper.join( "=? and ", aliasedPropertyColumns ) ).append( "=?" );
			}
		}

		whereClause.append( whereJoinFragment( getRootAlias(), true, false ) );

		return select.setOuterJoins( "", "" ).setWhereClause( whereClause.toString() ).toStatementString();
	}
