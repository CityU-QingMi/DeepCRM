	public TableBasedDeleteHandlerImpl(
			SessionFactoryImplementor factory,
			HqlSqlWalker walker,
			IdTableInfo idTableInfo) {
		super( factory, walker );

		DeleteStatement deleteStatement = ( DeleteStatement ) walker.getAST();
		FromElement fromElement = deleteStatement.getFromClause().getFromElement();

		this.targetedPersister = fromElement.getQueryable();
		final String bulkTargetAlias = fromElement.getTableAlias();

		final ProcessedWhereClause processedWhereClause = processWhereClause( deleteStatement.getWhereClause() );
		this.idSelectParameterSpecifications = processedWhereClause.getIdSelectParameterSpecifications();
		this.idInsertSelect = generateIdInsertSelect( bulkTargetAlias, idTableInfo, processedWhereClause );
		log.tracev( "Generated ID-INSERT-SELECT SQL (multi-table delete) : {0}", idInsertSelect );
		
		final String idSubselect = generateIdSubselect( targetedPersister, idTableInfo );
		deletes = new ArrayList<>();
		
		// If many-to-many, delete the FK row in the collection table.
		// This partially overlaps with DeleteExecutor, but it instead uses the temp table in the idSubselect.
		for ( Type type : targetedPersister.getPropertyTypes() ) {
			if ( type.isCollectionType() ) {
				CollectionType cType = (CollectionType) type;
				AbstractCollectionPersister cPersister = (AbstractCollectionPersister) factory.getMetamodel().collectionPersister( cType.getRole() );
				if ( cPersister.isManyToMany() ) {
					deletes.add( generateDelete( cPersister.getTableName(),
							cPersister.getKeyColumnNames(), idSubselect, "bulk delete - m2m join table cleanup"));
				}
			}
		}

		String[] tableNames = targetedPersister.getConstraintOrderedTableNameClosure();
		String[][] columnNames = targetedPersister.getContraintOrderedTableKeyColumnClosure();
		for ( int i = 0; i < tableNames.length; i++ ) {
			// TODO : an optimization here would be to consider cascade deletes and not gen those delete statements;
			//      the difficulty is the ordering of the tables here vs the cascade attributes on the persisters ->
			//          the table info gotten here should really be self-contained (i.e., a class representation
			//          defining all the needed attributes), then we could then get an array of those
			deletes.add( generateDelete( tableNames[i], columnNames[i], idSubselect, "bulk delete"));
		}
	}
