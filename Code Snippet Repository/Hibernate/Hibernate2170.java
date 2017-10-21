	public JdbcCoordinatorImpl(
			Connection userSuppliedConnection,
			JdbcSessionOwner owner) {
		this.isUserSuppliedConnection = userSuppliedConnection != null;

		final ResourceRegistry resourceRegistry = new ResourceRegistryStandardImpl(
				owner.getJdbcSessionContext().getObserver()
		);
		if ( isUserSuppliedConnection ) {
			this.logicalConnection = new LogicalConnectionProvidedImpl( userSuppliedConnection, resourceRegistry );
		}
		else {
			this.logicalConnection = new LogicalConnectionManagedImpl(
					owner.getJdbcConnectionAccess(),
					owner.getJdbcSessionContext(),
					resourceRegistry
			);
		}
		this.owner = owner;
		this.exceptionHelper = owner.getJdbcSessionContext()
				.getServiceRegistry()
				.getService( JdbcServices.class )
				.getSqlExceptionHelper();
	}
