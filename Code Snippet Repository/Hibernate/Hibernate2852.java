	@Override
	public UpdateHandler buildUpdateHandler(SessionFactoryImplementor factory, HqlSqlWalker walker) {
		final UpdateStatement updateStatement = (UpdateStatement) walker.getAST();

		final FromElement fromElement = updateStatement.getFromClause().getFromElement();
		final Queryable targetedPersister = fromElement.getQueryable();

		return new TableBasedUpdateHandlerImpl( factory, walker, getIdTableInfo( targetedPersister ) ) {
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
