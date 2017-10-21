	public void createTempTable(
			IdTableInfoImpl idTableInfo,
			TempTableDdlTransactionHandling ddlTransactionHandling,
			SharedSessionContractImplementor session) {
		// Don't really know all the codes required to adequately decipher returned jdbc exceptions here.
		// simply allow the failure to be eaten and the subsequent insert-selects/deletes should fail
		TemporaryTableCreationWork work = new TemporaryTableCreationWork( idTableInfo, session.getFactory() );

		if ( ddlTransactionHandling == TempTableDdlTransactionHandling.NONE ) {
			final Connection connection = session.getJdbcCoordinator()
					.getLogicalConnection()
					.getPhysicalConnection();

			work.execute( connection );

			session.getJdbcCoordinator().afterStatementExecution();
		}
		else {
			session.getTransactionCoordinator()
					.createIsolationDelegate()
					.delegateWork( work, ddlTransactionHandling == TempTableDdlTransactionHandling.ISOLATE_AND_TRANSACT );
		}
	}
