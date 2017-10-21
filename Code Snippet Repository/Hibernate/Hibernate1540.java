	@SuppressWarnings( {""})
	public static void applyRelationalConstraints(
			ValidatorFactory factory,
			Collection<PersistentClass> persistentClasses,
			Map settings,
			Dialect dialect,
			ClassLoaderAccess classLoaderAccess) {
		Class<?>[] groupsArray = GroupsPerOperation.buildGroupsForOperation(
				GroupsPerOperation.Operation.DDL,
				settings,
				classLoaderAccess
		);
		Set<Class<?>> groups = new HashSet<Class<?>>( Arrays.asList( groupsArray ) );

		for ( PersistentClass persistentClass : persistentClasses ) {
			final String className = persistentClass.getClassName();

			if ( className == null || className.length() == 0 ) {
				continue;
			}
			Class<?> clazz;
			try {
				clazz = classLoaderAccess.classForName( className );
			}
			catch ( ClassLoadingException e ) {
				throw new AssertionFailure( "Entity class not found", e );
			}

			try {
				applyDDL( "", persistentClass, clazz, factory, groups, true, dialect );
			}
			catch (Exception e) {
				LOG.unableToApplyConstraints( className, e );
			}
		}
	}
