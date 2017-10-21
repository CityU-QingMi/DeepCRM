	protected void releaseTempTable(
			IdTableInfoImpl idTableInfo,
			AfterUseAction afterUseAction,
			TempTableDdlTransactionHandling ddlTransactionHandling,
			SharedSessionContractImplementor session) {
		if ( afterUseAction == AfterUseAction.NONE ) {
			return;
		}

		if ( afterUseAction == AfterUseAction.DROP ) {
			TemporaryTableDropWork work = new TemporaryTableDropWork( idTableInfo, session.getFactory() );
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

		if ( afterUseAction == AfterUseAction.CLEAN ) {
			PreparedStatement ps = null;
			try {
				final String sql = "delete from " + idTableInfo.getQualifiedIdTableName();
				ps = session.getJdbcCoordinator().getStatementPreparer().prepareStatement( sql, false );
				session.getJdbcCoordinator().getResultSetReturn().executeUpdate( ps );
			}
			catch( Throwable t ) {
				log.unableToCleanupTemporaryIdTable(t);
			}
			finally {
				if ( ps != null ) {
					try {
						session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( ps );
					}
					catch( Throwable ignore ) {
						// ignore
					}
				}
			}
		}
	}
