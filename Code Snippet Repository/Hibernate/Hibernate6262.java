	@Test
	public void testBasic() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Flight airFrance = new Flight();
		airFrance.setId( Long.valueOf( 747 ) );
		airFrance.setName( "Paris-Amsterdam" );
		airFrance.setDuration( null );
		try {
			s.persist( airFrance );
			tx.commit();
			fail( "Basic(optional=false) fails" );
		}
		catch (Exception e) {
			//success
			if ( tx != null ) {
				tx.rollback();
			}
		}
		finally {
			s.close();
		}
	}
