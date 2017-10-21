	@Test
	public void testMultipleBagFetchesFail() {
		Session s = openSession();
		s.beginTransaction();
		try {
			s.createQuery( "from Human h join fetch h.friends f join fetch f.friends fof" ).list();
			fail( "failure expected" );
		}
		catch (IllegalArgumentException e) {
			assertTyping( MultipleBagFetchException.class, e.getCause() );
		}
		catch( HibernateException e ) {
			assertTrue( "unexpected failure reason : " + e, e.getMessage().indexOf( "multiple bags" ) > 0 );
		}
		s.getTransaction().commit();
		s.close();
	}
