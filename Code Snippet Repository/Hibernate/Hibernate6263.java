	@Test
	public void testDefaultValues() {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Ferry f = new Ferry();
		f.setSize( 2 );
		f.setSea( "Channel" );
		s.persist( f );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		f = (Ferry) s.get( Ferry.class, f.getId() );
		assertNotNull( f );
		assertEquals( "Channel", f.getSea() );
		assertEquals( 2, f.getSize() );
		s.delete( f );
		tx.commit();
		s.close();
	}
