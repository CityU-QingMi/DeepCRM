	@After
	public void cleanupData() {
		Session s = openSession();
		s.getTransaction().begin();
		s.createQuery( "delete Entity1" ).executeUpdate();
		s.createQuery( "delete Entity2" ).executeUpdate();
		s.createQuery( "delete Entity3" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
