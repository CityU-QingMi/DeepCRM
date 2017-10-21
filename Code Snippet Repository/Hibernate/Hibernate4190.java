	protected String generateSelectString(LockMode lockMode) {
		SimpleSelect select = new SimpleSelect( getFactory().getDialect() )
				.setLockMode( lockMode )
				.setTableName( getTableName() )
				.addColumns( getIdentifierColumnNames() )
				.addColumns(
						getSubclassColumnClosure(),
						getSubclassColumnAliasClosure(),
						getSubclassColumnLazyiness()
				)
				.addColumns(
						getSubclassFormulaClosure(),
						getSubclassFormulaAliasClosure(),
						getSubclassFormulaLazyiness()
				);
		//TODO: include the rowids!!!!
		if ( hasSubclasses() ) {
			if ( isDiscriminatorFormula() ) {
				select.addColumn( getDiscriminatorFormula(), getDiscriminatorAlias() );
			}
			else {
				select.addColumn( getDiscriminatorColumnName(), getDiscriminatorAlias() );
			}
		}
		if ( getFactory().getSettings().isCommentsEnabled() ) {
			select.setComment( "load " + getEntityName() );
		}
		return select.addCondition( getIdentifierColumnNames(), "=?" ).toStatementString();
	}
