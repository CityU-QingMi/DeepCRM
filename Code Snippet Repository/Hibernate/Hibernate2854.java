	@Override
	public DeleteHandler buildDeleteHandler(SessionFactoryImplementor factory, HqlSqlWalker walker) {
		final DeleteStatement updateStatement = (DeleteStatement) walker.getAST();

		final FromElement fromElement = updateStatement.getFromClause().getFromElement();
		final Queryable targetedPersister = fromElement.getQueryable();

		return new TableBasedDeleteHandlerImpl( factory, walker, getIdTableInfo( targetedPersister ) ) {
			@Override
			protected void releaseFromUse(Queryable persister, SharedSessionContractImplementor session) {
				if ( afterUseAction == AfterUseAction.NONE ) {
					return;
				}

				// clean up our id-table rows
				cleanUpRows( getIdTableInfo( persister ).getQualifiedIdTableName(), session );
			}
		};
	}
