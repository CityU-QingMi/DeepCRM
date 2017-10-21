		private JdbcContextImpl(
				JdbcConnectionAccess jdbcConnectionAccess,
				Dialect dialect,
				SqlStatementLogger sqlStatementLogger,
				SqlExceptionHelper sqlExceptionHelper,
				ServiceRegistry serviceRegistry) {
			this.jdbcConnectionAccess = jdbcConnectionAccess;
			this.dialect = dialect;
			this.sqlStatementLogger = sqlStatementLogger;
			this.sqlExceptionHelper = sqlExceptionHelper;
			this.serviceRegistry = serviceRegistry;
		}
