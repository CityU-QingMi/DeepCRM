	@Test
	public void testOptionalOneToOneRetrieval() {
		Session s = openSession();
		s.beginTransaction();
		Person me = new Person();
		me.name = "Steve";
		s.save( me );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		me = ( Person ) s.load( Person.class, me.name );
		assertNull( me.address );
		s.delete( me );
		s.getTransaction().commit();
		s.close();
	}
