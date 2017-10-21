	@Test
	public void testScrollOrderChildrenDesc() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Parent p0 = new Parent( "parent0" );
		s.save( p0 );
		t.commit();
		s.close();
		s = openSession();
		ScrollableResults results = s.createQuery( QUERY + " order by c.name desc" ).scroll();
		List list = new ArrayList();
		while ( results.next() ) {
			list.add( results.get( 0 ) );
		}
		try {
			assertResultFromAllUsers( list );
			fail( "should have failed because data is ordered incorrectly." );
		}
		catch ( AssertionError ex ) {
			// expected
		}
		finally {
			s.close();
		}
	}
