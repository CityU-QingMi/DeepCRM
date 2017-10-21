	protected void releaseResources() {
		if ( sessionFactory != null ) {
			try {
				sessionFactory.close();
			}
			catch (Exception e) {
				System.err.println( "Unable to release SessionFactory : " + e.getMessage() );
				e.printStackTrace();
			}
		}
		sessionFactory = null;

		if ( serviceRegistry != null ) {
			try {
				StandardServiceRegistryBuilder.destroy( serviceRegistry );
			}
			catch (Exception e) {
				System.err.println( "Unable to release StandardServiceRegistry : " + e.getMessage() );
				e.printStackTrace();
			}
		}
		serviceRegistry=null;
	}
