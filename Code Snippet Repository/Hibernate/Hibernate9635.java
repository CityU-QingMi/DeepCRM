	public static JdbcContext createJdbcContext(
			JdbcConnectionAccess jdbcConnectionAccess,
			ServiceRegistry serviceRegistry) {
		return new JdbcContext() {
			final JdbcServices jdbcServices = serviceRegistry.getService( JdbcServices.class );

			@Override
			public JdbcConnectionAccess getJdbcConnectionAccess() {
				return jdbcConnectionAccess;
			}

			@Override
			public Dialect getDialect() {
				return jdbcServices.getJdbcEnvironment().getDialect();
			}

			@Override
			public SqlStatementLogger getSqlStatementLogger() {
				return jdbcServices.getSqlStatementLogger();
			}

			@Override
			public SqlExceptionHelper getSqlExceptionHelper() {
				return jdbcServices.getSqlExceptionHelper();
			}

			@Override
			public ServiceRegistry getServiceRegistry() {
				return serviceRegistry;
			}
		};
	}
