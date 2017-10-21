	@Override
	public UpdateHandler buildUpdateHandler(SessionFactoryImplementor factory, HqlSqlWalker walker) {
		final UpdateStatement updateStatement = (UpdateStatement) walker.getAST();

		final FromElement fromElement = updateStatement.getFromClause().getFromElement();
		final Queryable targetedPersister = fromElement.getQueryable();

		final IdTableInfoImpl tableInfo = getIdTableInfo( targetedPersister );

		return new TableBasedUpdateHandlerImpl( factory, walker, tableInfo ) {
			@Override
			protected void prepareForUse(Queryable persister, SharedSessionContractImplementor session) {
				Helper.INSTANCE.createTempTable(
						tableInfo,
						ddlTransactionHandling,
						session
				);
			}

			@Override
			protected void releaseFromUse(Queryable persister, SharedSessionContractImplementor session) {
				Helper.INSTANCE.releaseTempTable(
						tableInfo,
						afterUseAction,
						ddlTransactionHandling,
						session
				);
			}
		};
	}
