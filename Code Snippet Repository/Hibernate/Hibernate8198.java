	@Test
	public void testListOrderChildrenDesc() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Parent p0 = new Parent( "parent0" );
		s.save( p0 );
		t.commit();
		s.close();
		s = openSession();
		List results = s.createQuery( QUERY + " order by c.name desc" ).list();
		try {
			assertResultFromAllUsers( results );
			fail( "should have failed because data is ordered incorrectly." );
		}
		catch ( AssertionError ex ) {
			// expected
		}
		finally {
			s.close();
		}
	}
