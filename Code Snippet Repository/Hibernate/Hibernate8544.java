	@Test
	public void testNoForeignKeyViolations() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Glarch g1 = new Glarch();
		Glarch g2 = new Glarch();
		g1.setNext(g2);
		g2.setNext(g1);
		s.save(g1);
		s.save(g2);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		List l = s.createQuery( "from Glarch g where g.next is not null" ).list();
		s.delete( l.get(0) );
		s.delete( l.get(1) );
		s.getTransaction().commit();
		s.close();
	}
