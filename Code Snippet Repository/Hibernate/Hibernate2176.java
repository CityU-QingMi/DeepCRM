	private JdbcCoordinatorImpl(
			LogicalConnectionImplementor logicalConnection,
			boolean isUserSuppliedConnection,
			JdbcSessionOwner owner) {
		this.logicalConnection = logicalConnection;
		this.isUserSuppliedConnection = isUserSuppliedConnection;
		this.owner = owner;
		this.exceptionHelper = owner.getJdbcSessionContext()
				.getServiceRegistry()
				.getService( JdbcServices.class )
				.getSqlExceptionHelper();
	}
