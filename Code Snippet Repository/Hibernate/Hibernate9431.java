	@Test
	public void testInsertionFailureWithParamChecking() {
		Session s = openSession();
		s.beginTransaction();
		ParamCheckingEntity e = new ParamCheckingEntity();
		e.setName( "dummy" );
		s.save( e );
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
