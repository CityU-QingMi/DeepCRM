	@Test
	public void testUpdateFailureWithExceptionChecking() {
		Session s = openSession();
		s.beginTransaction();
		ExceptionCheckingEntity e = new ExceptionCheckingEntity();
		e.setId( Long.valueOf( 1 ) );
		e.setName( "dummy" );
		s.update( e );
		try {
			s.flush();
			fail( "expection flush failure!" );
		}
		catch( Exception ex ) {
			// these should specifically be JDBCExceptions...
		}
		s.clear();
		s.getTransaction().rollback();
		s.close();
	}
