	@After
	public void tearDown() throws Exception {
		if ( sessionImpl != null && !sessionImpl.isClosed() ) {
			((Session) sessionImpl).close();
		}
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
		if ( serviceRegistry != null ) {
			StandardServiceRegistryBuilder.destroy( serviceRegistry );
		}
	}
