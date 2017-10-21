	private boolean canDoNameParameterBinding() {
		final ExtractedDatabaseMetaData databaseMetaData = session()
				.getJdbcCoordinator()
				.getJdbcSessionOwner()
				.getJdbcSessionContext()
				.getServiceRegistry().getService( JdbcEnvironment.class )
				.getExtractedDatabaseMetaData();
		return
				databaseMetaData.supportsNamedParameters() &&
				ProcedureParameterNamedBinder.class.isInstance( hibernateType )
						&& ((ProcedureParameterNamedBinder) hibernateType).canDoSetting();
	}
