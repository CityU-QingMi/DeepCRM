	@Test
	public void testXMLEntityHandled() throws Exception {
		Session s = openSession();
		s.getTransaction().begin();
		Lighter l = new Lighter();
		l.name = "Blue";
		l.power = "400F";
		s.persist( l );
		s.flush();
		s.getTransaction().rollback();
		s.close();
	}
