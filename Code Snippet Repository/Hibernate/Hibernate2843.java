	public CteValuesListDeleteHandlerImpl(
			SessionFactoryImplementor factory,
			HqlSqlWalker walker,
			String catalog,
			String schema) {
		super( factory, walker, catalog, schema );

		final String idSubselect = generateIdSubselect( getTargetedQueryable() );

		for ( Type type : getTargetedQueryable().getPropertyTypes() ) {
			if ( type.isCollectionType() ) {
				CollectionType cType = (CollectionType) type;
				AbstractCollectionPersister cPersister = (AbstractCollectionPersister) factory.getMetamodel().collectionPersister( cType.getRole() );
				if ( cPersister.isManyToMany() ) {
					deletes.add( generateDelete(
							cPersister.getTableName(),
							cPersister.getKeyColumnNames(),
							idSubselect,
							"bulk delete - m2m join table cleanup"
					) );
				}
			}
		}

		String[] tableNames = getTargetedQueryable().getConstraintOrderedTableNameClosure();
		String[][] columnNames = getTargetedQueryable().getContraintOrderedTableKeyColumnClosure();
		for ( int i = 0; i < tableNames.length; i++ ) {
			// TODO : an optimization here would be to consider cascade deletes and not gen those delete statements;
			//      the difficulty is the ordering of the tables here vs the cascade attributes on the persisters ->
			//          the table info gotten here should really be self-contained (i.e., a class representation
			//          defining all the needed attributes), then we could then get an array of those
			deletes.add( generateDelete( tableNames[i], columnNames[i], idSubselect, "bulk delete" ) );
		}
	}
