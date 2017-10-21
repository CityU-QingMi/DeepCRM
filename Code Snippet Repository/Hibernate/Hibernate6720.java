	@Test
	public void testJoinedAbstractClass() throws Exception {
		Session s;
		s = openSession();
		s.getTransaction().begin();
		Sweater sw = new Sweater();
		sw.setColor( "Black" );
		sw.setSize( 2 );
		sw.setSweat( true );
		s.persist( sw );
		s.getTransaction().commit();
		s.clear();

		s = openSession();
		s.getTransaction().begin();
		sw = (Sweater) s.get( Sweater.class, sw.getId() );
		s.delete( sw );
		s.getTransaction().commit();
		s.close();
	}
