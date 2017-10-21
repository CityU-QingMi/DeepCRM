	@After
	public void cleanUpData() {
		final Session s = openSession();
		s.getTransaction().begin();

		s.createQuery( "delete from Item" ).executeUpdate();

		s.getTransaction().commit();
		s.close();
	}
