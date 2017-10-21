	private void releaseResources(StandardServiceRegistry serviceRegistry, SessionFactoryImplementor sessionFactory) {
		if ( sessionFactory != null ) {
			try {
				sessionFactory.close();
			}
			catch (Exception e) {
				fail( "Unable to release SessionFactory : " + e.getMessage() );
			}
		}

		if ( serviceRegistry != null ) {
			try {
				StandardServiceRegistryBuilder.destroy( serviceRegistry );
			}
			catch (Exception e) {
				fail( "Unable to release StandardServiceRegistry : " + e.getMessage() );
			}
		}
	}
