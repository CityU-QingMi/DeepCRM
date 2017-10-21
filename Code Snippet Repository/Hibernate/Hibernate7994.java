	@Test
	public void testIncorrectSyntax() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		try {
			s.createQuery( "update Human set Human.description = 'xyz' where Human.id = 1 and Human.description is null" );
			fail( "expected failure" );
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch( QueryException expected ) {
			// ignore : expected behavior
		}
		t.commit();
		s.close();
	}
