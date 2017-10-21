	@Override
	public void checkPermission(PermissionCheckEntityInformation entityInformation, PermissibleAction action) {
		if ( action == PermissibleAction.ANY ) {
			throw new HibernateException( "ANY action (*) is not legal for permission check, only for configuration" );
		}

		final String originalContextId = AccessController.doPrivileged( new ContextIdSetAction( contextId ) );
		try {
			doPermissionCheckInContext( entityInformation, action );
		}
		finally {
			AccessController.doPrivileged( new ContextIdSetAction( originalContextId ) );
		}
	}
