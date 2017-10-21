	@Test
	public void testUpdateLazyCollections() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Master m = new Master();
		s.save( m );
		Detail d1 = new Detail();
		Detail d2 = new Detail();
		d2.setX( 14 );
		d1.setMaster( m );
		d2.setMaster( m );
		s.save( d1 );
		s.save( d2 );
		m.addDetail( d1 );
		m.addDetail( d2 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		m = (Master) s.load( Master.class, m.getId() );
		s.getTransaction().commit();
		s.close();
		m.setName("New Name");

		s = openSession();
		s.beginTransaction();
		s.update( m );
		Iterator iter = m.getDetails().iterator();
		int i=0;
		while ( iter.hasNext() ) {
			assertTrue( iter.next()!=null );
			i++;
		}
		assertTrue(i==2);
		iter = m.getDetails().iterator();
		while ( iter.hasNext() ) s.delete( iter.next() );
		s.delete( m );
		s.getTransaction().commit();
		s.close();
	}
