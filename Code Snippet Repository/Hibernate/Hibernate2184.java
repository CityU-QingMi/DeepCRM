	@Override
	public void configure(Map configValues) {
		this.jdbcEnvironment = serviceRegistry.getService( JdbcEnvironment.class );
		assert jdbcEnvironment != null : "JdbcEnvironment was not found!";

		this.multiTenancyStrategy = MultiTenancyStrategy.determineMultiTenancyStrategy( configValues );

		final boolean showSQL = ConfigurationHelper.getBoolean( Environment.SHOW_SQL, configValues, false );
		final boolean formatSQL = ConfigurationHelper.getBoolean( Environment.FORMAT_SQL, configValues, false );

		this.sqlStatementLogger =  new SqlStatementLogger( showSQL, formatSQL );

		resultSetWrapper = new ResultSetWrapperImpl( serviceRegistry );
	}
