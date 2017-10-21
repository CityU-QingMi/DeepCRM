	@Test
	public void simpleSelectTest() {
		final Session s = openSession();
		s.getTransaction().begin();

		s.createQuery( "from Polygon" ).list();

		s.getTransaction().commit();
		s.close();
	}
