	public static void resolveResultClasses(ResultClassesResolutionContext context, Class... resultClasses) {
		int i = 0;
		for ( Class resultClass : resultClasses ) {
			context.addQueryReturns(
					new NativeSQLQueryRootReturn( "alias" + (++i), resultClass.getName(), LockMode.READ )
			);
			try {
				final EntityPersister persister = context.getSessionFactory().getEntityPersister( resultClass.getName() );
				context.addQuerySpaces( (String[]) persister.getQuerySpaces() );
			}
			catch (Exception ignore) {
			}
		}
	}
