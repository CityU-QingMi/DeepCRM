	private Connection spy(Connection connection) {
		if ( MockUtil.isMock( connection ) ) {
			return connection;
		}
		Connection connectionSpy = Mockito.spy( connection );
		try {
			Mockito.doAnswer( invocation -> {
				PreparedStatement statement = (PreparedStatement) invocation.callRealMethod();
				PreparedStatement statementSpy = Mockito.spy( statement );
				String sql = (String) invocation.getArguments()[0];
				preparedStatementMap.put( statementSpy, sql );
				return statementSpy;
			} ).when( connectionSpy ).prepareStatement( ArgumentMatchers.anyString() );

			Mockito.doAnswer( invocation -> {
				Statement statement = (Statement) invocation.callRealMethod();
				Statement statementSpy = Mockito.spy( statement );
				Mockito.doAnswer( statementInvocation -> {
					String sql = (String) statementInvocation.getArguments()[0];
					executeStatements.add( sql );
					return statementInvocation.callRealMethod();
				} ).when( statementSpy ).execute( ArgumentMatchers.anyString() );
				Mockito.doAnswer( statementInvocation -> {
					String sql = (String) statementInvocation.getArguments()[0];
					executeUpdateStatements.add( sql );
					return statementInvocation.callRealMethod();
				} ).when( statementSpy ).executeUpdate( ArgumentMatchers.anyString() );
				return statementSpy;
			} ).when( connectionSpy ).createStatement();
		}
		catch ( SQLException e ) {
			throw new IllegalArgumentException( e );
		}
		return connectionSpy;
	}
