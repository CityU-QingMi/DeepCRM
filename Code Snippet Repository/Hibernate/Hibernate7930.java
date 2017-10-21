	@Test
	public void testInvalidFetchSemantics() {
		Session s = openSession();
		s.beginTransaction();

		try {
			s.createQuery( "select mother from Human a left join fetch a.mother mother" ).list();
			fail( "invalid fetch semantic allowed!" );
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch( QueryException e ) {
		}

		try {
			s.createQuery( "select mother from Human a left join fetch a.mother mother" ).list();
			fail( "invalid fetch semantic allowed!" );
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch( QueryException e ) {
		}

		s.getTransaction().commit();
		s.close();
	}
