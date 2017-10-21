	@Test
	public void testConcreateSubclassDeterminationOnEmptyDynamicMap() {
		Session s = openSession();
		s.beginTransaction();
		s.persist( "Superclass", new HashMap() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.createQuery( "delete Superclass" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
