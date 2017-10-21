	@Test
	public void testUpdateFailureWithParamChecking() {
		Session s = openSession();
		s.beginTransaction();
		ParamCheckingEntity e = new ParamCheckingEntity();
		e.setId( Long.valueOf( 1 ) );
		e.setName( "dummy" );
		s.update( e );
		try {
			s.flush();
			fail( "expection flush failure!" );
		}
		catch( Exception ex ) {
			// these should specifically be HibernateExceptions...
		}
		s.clear();
		s.getTransaction().rollback();
		s.close();
	}
