	public JdbcSessionContextImpl(SharedSessionContractImplementor session, StatementInspector statementInspector) {
		this.sessionFactory = session.getFactory();
		this.statementInspector = statementInspector;
		this.connectionHandlingMode = settings().getPhysicalConnectionHandlingMode();
		this.serviceRegistry = sessionFactory.getServiceRegistry();
		this.jdbcObserver = new JdbcObserverImpl( session );

		if ( this.statementInspector == null ) {
			throw new IllegalArgumentException( "StatementInspector cannot be null" );
		}
	}
