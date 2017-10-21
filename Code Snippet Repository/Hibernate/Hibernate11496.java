	protected SharedSessionContractImplementor mockedSession() {
		SessionMock session = mock(SessionMock.class);
		when(session.isClosed()).thenReturn(false);
		when(session.getTimestamp()).thenReturn(TIME_SERVICE.wallClockTime());
		if (jtaPlatform == BatchModeJtaPlatform.class) {
			BatchModeTransactionCoordinator txCoord = new BatchModeTransactionCoordinator();
			when(session.getTransactionCoordinator()).thenReturn(txCoord);
			when(session.beginTransaction()).then(invocation -> {
				Transaction tx = txCoord.newTransaction();
				tx.begin();
				return tx;
			});
		} else if (jtaPlatform == null) {
			Connection connection = mock(Connection.class);
			JdbcConnectionAccess jdbcConnectionAccess = mock(JdbcConnectionAccess.class);
			try {
				when(jdbcConnectionAccess.obtainConnection()).thenReturn(connection);
			} catch (SQLException e) {
				// never thrown from mock
			}
			JdbcSessionOwner jdbcSessionOwner = mock(JdbcSessionOwner.class);
			when(jdbcSessionOwner.getJdbcConnectionAccess()).thenReturn(jdbcConnectionAccess);
			SqlExceptionHelper sqlExceptionHelper = mock(SqlExceptionHelper.class);
			JdbcServices jdbcServices = mock(JdbcServices.class);
			when(jdbcServices.getSqlExceptionHelper()).thenReturn(sqlExceptionHelper);
			ServiceRegistry serviceRegistry = mock(ServiceRegistry.class);
			when(serviceRegistry.getService(JdbcServices.class)).thenReturn(jdbcServices);
			JdbcSessionContext jdbcSessionContext = mock(JdbcSessionContext.class);
			when(jdbcSessionContext.getServiceRegistry()).thenReturn(serviceRegistry);
			when(jdbcSessionOwner.getJdbcSessionContext()).thenReturn(jdbcSessionContext);
			NonJtaTransactionCoordinator txOwner = mock(NonJtaTransactionCoordinator.class);
			when(txOwner.getResourceLocalTransaction()).thenReturn(new JdbcResourceTransactionMock());
			when(txOwner.getJdbcSessionOwner()).thenReturn(jdbcSessionOwner);
			when(txOwner.isActive()).thenReturn(true);
			TransactionCoordinator txCoord = JdbcResourceLocalTransactionCoordinatorBuilderImpl.INSTANCE
							.buildTransactionCoordinator(txOwner, null);
			when(session.getTransactionCoordinator()).thenReturn(txCoord);
			when(session.beginTransaction()).then(invocation -> {
				Transaction tx = new TransactionImpl(txCoord, session.getExceptionConverter());
				tx.begin();
				return tx;
			});
		} else {
			throw new IllegalStateException("Unknown JtaPlatform: " + jtaPlatform);
		}
		return session;
	}
